package org.example.spring_api.entity;

public class EnergyHistorical {
    private double communityProduced; // in kWh
    private double communityUsed;     // in kWh
    private double gridUsed;          // in kWh

    public EnergyHistorical(double communityProduced, double communityUsed, double gridUsed) {
        this.communityProduced = communityProduced;
        this.communityUsed = communityUsed;
        this.gridUsed = gridUsed;
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