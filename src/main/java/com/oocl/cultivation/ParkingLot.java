package com.oocl.cultivation;

public class ParkingLot {
    private int parkingCapacity;
    private final static int DEFAULT_PARKING_CAPACITY = 10;

    public ParkingLot() {
        this.parkingCapacity = DEFAULT_PARKING_CAPACITY;
    }

    public ParkingLot(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
    }
}
