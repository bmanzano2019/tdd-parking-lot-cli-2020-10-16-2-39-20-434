package com.oocl.cultivation.exception;

public class NullParkingTicketException extends RuntimeException {
    private static final String NULL_PARKING_TICKET_MESSAGE = "Please provide your parking ticket.";

    public NullParkingTicketException() {
        super(NULL_PARKING_TICKET_MESSAGE);
    }
}