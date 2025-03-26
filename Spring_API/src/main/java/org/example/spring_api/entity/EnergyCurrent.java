package org.example.spring_api.entity;

public class EnergyCurrent {
    private double communityPoolUsed; // in percent
    private double gridPortion;       // in percent

    public EnergyCurrent(double communityPoolUsed, double gridPortion) {
        this.communityPoolUsed = communityPoolUsed;
        this.gridPortion = gridPortion;
    }

    public double getCommunityPoolUsed() {
        return communityPoolUsed;
    }

    public void setCommunityPoolUsed(double communityPoolUsed) {
        this.communityPoolUsed = communityPoolUsed;
    }

    public double getGridPortion() {
        return gridPortion;
    }

    public void setGridPortion(double gridPortion) {
        this.gridPortion = gridPortion;
    }
}