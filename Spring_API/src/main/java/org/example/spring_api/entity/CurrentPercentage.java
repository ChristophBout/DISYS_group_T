package org.example.spring_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "current_percentage")
public class CurrentPercentage {
    @Id
    private Timestamp hour;

    private double communityDepleted;
    private double gridPortion;

    // Getter & Setter
    public Timestamp getHour() { return hour; }
    public void setHour(Timestamp hour) { this.hour = hour; }

    public double getCommunityDepleted() {
        return Math.round(communityDepleted * 1000.0) / 1000.0;
    }

    public void setCommunityDepleted(double communityDepleted) { this.communityDepleted = communityDepleted; }

    public double getGridPortion() {
        return Math.round(gridPortion * 1000.0) / 1000.0;
    }

    public void setGridPortion(double gridPortion) { this.gridPortion = gridPortion; }
}