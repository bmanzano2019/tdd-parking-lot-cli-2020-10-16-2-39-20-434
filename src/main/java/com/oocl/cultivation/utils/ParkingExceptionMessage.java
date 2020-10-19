package com.oocl.cultivation.utils;

public enum ParkingExceptionMessage {
    FULL_PARKING_CAPACITY_MESSAGE("Not enough position.");

    private String errorMessage;

    ParkingExceptionMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
