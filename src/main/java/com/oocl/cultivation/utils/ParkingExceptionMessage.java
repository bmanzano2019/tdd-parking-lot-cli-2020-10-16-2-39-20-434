package com.oocl.cultivation.utils;

public enum ParkingExceptionMessage {
    FULL_PARKING_CAPACITY_MESSAGE("Not enough position."),
    NULL_PARKING_TICKET_MESSAGE("Please provide your parking ticket."),
    UNLISTED_PARKING_BOY_MESSAGE("Parking Boy Not in list."),
    UNRECOGNIZED_PARKING_TICKET_MESSAGE("Unrecognized Parking Ticket.");

    private String errorMessage;

    ParkingExceptionMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
