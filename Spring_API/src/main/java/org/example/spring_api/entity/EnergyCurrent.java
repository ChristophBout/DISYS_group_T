package org.example.spring_api.entity;

public class EnergyCurrent {

    private double communityPool; // Prozent der Community
    private double gridPortion;   // Prozent vom Grid

    public EnergyCurrent(double communityPool, double gridPortion) {
        this.communityPool = communityPool;
        this.gridPortion = gridPortion;
    }

    public double getCommunityPool() {
        return communityPool;
    }

    public void setCommunityPool(double communityPool) {
        this.communityPool = communityPool;
    }

    public double getGridPortion() {
        return gridPortion;
    }

    public void setGridPortion(double gridPortion) {
        this.gridPortion = gridPortion;
    }
}
