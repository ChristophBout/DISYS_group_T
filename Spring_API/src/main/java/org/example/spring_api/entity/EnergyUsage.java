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
    public Timestamp getHour() { return hour; }
    public void setHour(Timestamp hour) { this.hour = hour; }

    public double getCommunityProduced() { return communityProduced; }
    public void setCommunityProduced(double communityProduced) { this.communityProduced = communityProduced; }

    public double getCommunityUsed() { return communityUsed; }
    public void setCommunityUsed(double communityUsed) { this.communityUsed = communityUsed; }

    public double getGridUsed() { return gridUsed; }
    public void setGridUsed(double gridUsed) { this.gridUsed = gridUsed; }
}