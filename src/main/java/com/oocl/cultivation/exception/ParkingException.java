package com.oocl.cultivation.exception;

import com.oocl.cultivation.utils.ParkingExceptionMessage;

public class ParkingException extends RuntimeException {
    public ParkingException(ParkingExceptionMessage fullParkingCapacityMessage) {
        super(fullParkingCapacityMessage.getErrorMessage());
    }
}
