package org.example.service;

import com.rabbitmq.client.ConnectionFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class UsageService extends BaseService {

    private static final String DB_CONNECTION =
            "jdbc:postgresql://localhost:5432/postgres?user=disysuser&password=disyspw";

    private final String id;

    public UsageService(String inDestination, String outDestination, String brokerUrl) {
        super(inDestination, outDestination, brokerUrl);
        this.id = UUID.randomUUID().toString();
        System.out.println("Usage Worker (" + id + ") started...");
    }

    @Override
    protected String executeInternal(String input) {

        try (Connection conn = connect()) {
            // Expected input format: TYPE,ASSOCIATION,KWH,DATETIME
            String[] parts = input.split(",");
            if (parts.length != 4) {
                System.err.println("Invalid message format: " + input);
                return "error";
            }

            String type = parts[0].trim();
            String association = parts[1].trim();
            double kwh = Double.parseDouble(parts[2].trim());
            LocalDateTime datetime = LocalDateTime.parse(parts[3].trim());
            Timestamp hour = Timestamp.valueOf(datetime.truncatedTo(ChronoUnit.HOURS));

            // Insert initial row if not exists
            PreparedStatement insert = conn.prepareStatement("""
                INSERT INTO energy_usage (hour, community_produced, community_used, grid_used)
                VALUES (?, 0, 0, 0)
                ON CONFLICT (hour) DO NOTHING
            """);
            insert.setTimestamp(1, hour);
            int inserts = insert.executeUpdate();
            System.out.println("Inserted new row: " + inserts);

            if ("PRODUCER".equalsIgnoreCase(type)) {
                PreparedStatement update = conn.prepareStatement("""
                    UPDATE energy_usage
                    SET community_produced = community_produced + ?
                    WHERE hour = ?
                """);
                update.setDouble(1, kwh);
                update.setTimestamp(2, hour);
                int rows = update.executeUpdate();
                System.out.println("Updated producer rows: " + rows);

            } else if ("USER".equalsIgnoreCase(type)) {
                double produced = getValue(conn, "community_produced", hour);
                double used = getValue(conn, "community_used", hour);

                double fromCommunity = Math.min(kwh, Math.max(0, produced - used));
                double fromGrid = Math.max(0, kwh - fromCommunity);

                System.out.printf("[DEBUG] hour: %s | kwh: %.3f | produced: %.3f | used: %.3f | fromCommunity: %.3f | fromGrid: %.3f%n",
                        hour.toLocalDateTime(), kwh, produced, used, fromCommunity, fromGrid);

                if (fromCommunity > 0) {
                    PreparedStatement updateUsed = conn.prepareStatement("""
                        UPDATE energy_usage
                        SET community_used = community_used + ?
                        WHERE hour = ?
                    """);
                    updateUsed.setDouble(1, fromCommunity);
                    updateUsed.setTimestamp(2, hour);
                    int rowsUsed = updateUsed.executeUpdate();
                    System.out.println("Updated community_used rows: " + rowsUsed);
                }

                if (fromGrid > 0.0001) {
                    PreparedStatement updateGrid = conn.prepareStatement("""
                        UPDATE energy_usage
                        SET grid_used = grid_used + ?
                        WHERE hour = ?
                    """);
                    updateGrid.setDouble(1, fromGrid);
                    updateGrid.setTimestamp(2, hour);
                    int rowsGrid = updateGrid.executeUpdate();
                    System.out.println("Updated grid_used rows: " + rowsGrid);
                }
            }

            System.out.println("Processed successfully: " + input);

            try {
                ConnectionFactory factory = new ConnectionFactory();
                factory.setHost("localhost");
                try (com.rabbitmq.client.Connection rmqConnection = factory.newConnection();
                     com.rabbitmq.client.Channel channel = rmqConnection.createChannel()) {

                    String updateMessage = "update," + hour.toLocalDateTime();
                    channel.basicPublish("", "update message", null, updateMessage.getBytes());
                    System.out.println("Sent update message: " + updateMessage);
                }
            } catch (Exception e) {
                System.err.println("Failed to send update message");
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.err.println("Failed to process: " + input);
            e.printStackTrace();
            return "error";
        }

        return "ok";
    }

    private double getValue(Connection conn, String column, Timestamp hour) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT " + column + " FROM energy_usage WHERE hour = ?"
        );
        stmt.setTimestamp(1, hour);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getDouble(1);
        }
        return 0.0;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION);
    }
}