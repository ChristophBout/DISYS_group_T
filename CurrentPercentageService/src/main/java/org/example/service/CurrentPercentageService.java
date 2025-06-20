package org.example.service;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class CurrentPercentageService extends CurrentBaseService {

    private static final String DB_CONNECTION =
            "jdbc:postgresql://localhost:5432/postgres?user=disysuser&password=disyspw";

    private final String id;

    public CurrentPercentageService(String inDestination, String outDestination, String brokerUrl) {
        super(inDestination, outDestination, brokerUrl);
        this.id = UUID.randomUUID().toString();
        System.out.println("CurrentPercentageService Worker (" + id + ") started...");
    }

    @Override
    protected String executeInternal(String input) {
        System.out.println(">>> PercentageService: Nachricht angekommen: " + input);

        try (Connection conn = connect()) {
            // Expected format: just a signal/message like "update,2025-06-20T15:00:00"
            String[] parts = input.split(",");
            if (parts.length != 2) {
                System.err.println("Invalid message format: " + input);
                return "error";
            }

            LocalDateTime datetime = LocalDateTime.parse(parts[1].trim());
            Timestamp hour = Timestamp.valueOf(datetime.truncatedTo(ChronoUnit.HOURS));

            PreparedStatement stmt = conn.prepareStatement("""
                SELECT community_produced, community_used, grid_used
                FROM energy_usage
                WHERE hour = ?
            """);
            stmt.setTimestamp(1, hour);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                System.err.println("No energy_usage entry found for hour: " + hour);
                return "error";
            }

            double produced = rs.getDouble("community_produced");
            double used = rs.getDouble("community_used");
            double grid = rs.getDouble("grid_used");

            double communityDepleted = (produced == 0) ? 100.0 : 100.0 * (used / produced);
            double gridPortion = (used + grid == 0) ? 0.0 : 100.0 * (grid / (used + grid));

            PreparedStatement insert = conn.prepareStatement("""
                INSERT INTO current_percentage (hour, community_depleted, grid_portion)
                VALUES (?, ?, ?)
                ON CONFLICT (hour) DO UPDATE SET
                    community_depleted = EXCLUDED.community_depleted,
                    grid_portion = EXCLUDED.grid_portion
            """);
            insert.setTimestamp(1, hour);
            insert.setDouble(2, communityDepleted);
            insert.setDouble(3, gridPortion);
            int updated = insert.executeUpdate();

            System.out.println("Inserted/Updated percentage row: " + updated);
            return "ok";

        } catch (Exception e) {
            System.err.println("PercentageService failed to process: " + input);
            e.printStackTrace();
            return "error";
        }
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION);
    }
}