package com.oocl.cultivation.exception;

public class UnlistedParkingBoyException extends RuntimeException {
    private static final String UNLISTED_PARKING_BOY_MESSAGE = "Parking Boy Not in list.";

    public UnlistedParkingBoyException() {
        super(UNLISTED_PARKING_BOY_MESSAGE);
    }
}