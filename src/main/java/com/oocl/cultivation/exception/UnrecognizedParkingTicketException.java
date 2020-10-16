package com.oocl.cultivation.exception;

public class UnrecognizedParkingTicketException extends RuntimeException {
    public static final String UNRECOGNIZED_PARKING_TICKET_MESSAGE = "Unrecognized Parking Ticket";

    public UnrecognizedParkingTicketException() {
        super(UNRECOGNIZED_PARKING_TICKET_MESSAGE);
    }
}
