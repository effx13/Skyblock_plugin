package com.effx.skyblock.Data;

import java.util.UUID;

public class User {
    private UUID uuid;
    private double x;
    private double y;
    private double z;
    private int order;
    private boolean hasIsland = false;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isHasIsland() {
        return hasIsland;
    }

    public void setHasIsland(boolean hasIsland) {
        this.hasIsland = hasIsland;
    }


    public User(UUID uuid, double x, double y, double z, int order, boolean hasIsland) {
        this.uuid = uuid;
        this.x = x;
        this.y = y;
        this.z = z;
        this.order = order;
        this.hasIsland = hasIsland;
    }
}
