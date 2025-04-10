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
        return communityProduced;
    }

    public void setCommunityProduced(double communityProduced) {
        this.communityProduced = communityProduced;
    }

    public double getCommunityUsed() {
        return communityUsed;
    }

    public void setCommunityUsed(double communityUsed) {
        this.communityUsed = communityUsed;
    }

    public double getGridUsed() {
        return gridUsed;
    }

    public void setGridUsed(double gridUsed) {
        this.gridUsed = gridUsed;
    }
}
