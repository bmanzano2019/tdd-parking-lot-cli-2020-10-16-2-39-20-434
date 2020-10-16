package com.oocl.cultivation.exception;

public class FullParkingCapacityException extends RuntimeException {
    private static final String FULL_PARKING_CAPACITY_MESSAGE = "Not enough position.";

    public FullParkingCapacityException() {
        super(FULL_PARKING_CAPACITY_MESSAGE);
    }
}
