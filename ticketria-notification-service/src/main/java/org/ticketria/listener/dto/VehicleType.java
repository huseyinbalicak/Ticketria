package org.ticketria.listener.dto;

public enum VehicleType {
    AIRPLANE(189),
    BUS(45);

    private final int capacity;

    VehicleType(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
