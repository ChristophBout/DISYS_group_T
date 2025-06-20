package org.example.spring_api.repository;

import org.example.spring_api.entity.EnergyCurrent;
import org.example.spring_api.entity.EnergyHistorical;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;

@Repository
public class EnergyRepository {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres?user=disysuser&password=disyspw";

    public EnergyCurrent getCurrentEnergyPercentage() throws SQLException {
        String sql = """
            SELECT community_depleted, grid_portion
            FROM current_percentage
            ORDER BY hour DESC
            LIMIT 1
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return new EnergyCurrent(
                        rs.getDouble("community_depleted"),
                        rs.getDouble("grid_portion")
                );
            }
            return null;
        }
    }

    public EnergyHistorical getHistoricalEnergyUsage(LocalDateTime start, LocalDateTime end) throws SQLException {
        String sql = """
            SELECT 
              SUM(community_produced) AS total_produced,
              SUM(community_used) AS total_used,
              SUM(grid_used) AS total_grid
            FROM energy_usage
            WHERE hour BETWEEN ? AND ?
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(start));
            stmt.setTimestamp(2, Timestamp.valueOf(end));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new EnergyHistorical(
                        rs.getDouble("total_produced"),
                        rs.getDouble("total_used"),
                        rs.getDouble("total_grid")
                );
            }
            return null;
        }
    }
}