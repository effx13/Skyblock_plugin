package com.effx.skyblock.Data;

import java.util.UUID;

public class User {
    private final UUID uuid;
    private final double x;
    private final double y;
    private final double z;
    private final int order;

    public UUID getUuid() {
        return uuid;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public int getOrder() {
        return order;
    }

    public User(UUID uuid, double x, double y, double z, int order) {
        this.uuid = uuid;
        this.x = x;
        this.y = y;
        this.z = z;
        this.order = order;
    }
}
