package org.example.spring_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "energy_usage")
public class EnergyUsage {
    @Id
    private Timestamp hour;

    private double communityProduced;
    private double communityUsed;
    private double gridUsed;

    // Getter & Setter
    public Timestamp getHour() {
        return hour;
    }

    public void setHour(Timestamp hour) {
        this.hour = hour;
    }

    public double getCommunityProduced() {
        return Math.round(communityProduced * 1000.0) / 1000.0;
    }

    public void setCommunityProduced(double communityProduced) {
        this.communityProduced = communityProduced;
    }

    public double getCommunityUsed() {
        return Math.round(communityUsed * 1000.0) / 1000.0;
    }

    public void setCommunityUsed(double communityUsed) {
        this.communityUsed = communityUsed;
    }

    public double getGridUsed() {
        return Math.round(gridUsed * 1000.0) / 1000.0;
    }

    public void setGridUsed(double gridUsed) {
        this.gridUsed = gridUsed;
    }
}