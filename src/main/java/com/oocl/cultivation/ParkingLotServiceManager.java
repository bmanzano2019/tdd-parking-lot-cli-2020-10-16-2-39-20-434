package com.oocl.cultivation;

import com.oocl.cultivation.exception.ParkingException;
import com.oocl.cultivation.utils.ParkingExceptionMessage;

import java.util.Arrays;
import java.util.List;

public class ParkingLotServiceManager {
    private List<Parkable> parkableList;

    public ParkingLotServiceManager(Parkable... parkables) {
        parkableList = Arrays.asList(parkables);
    }

    public ParkingTicket park(Car car) {
        return parkableList.stream()
                .filter(parkable -> parkable.findAvailableParkingLot() != null)
                .findFirst()
                .orElseThrow(() -> new ParkingException(ParkingExceptionMessage.FULL_PARKING_CAPACITY_MESSAGE))
                .park(car);
    }

    public Car fetchCar(ParkingTicket ticket) {
        if (ticket == null) {
            throw new ParkingException(ParkingExceptionMessage.NULL_PARKING_TICKET_MESSAGE);
        }

        return parkableList.stream()
                .filter(parkable -> parkable.findParkingLotWhereCarIsParked(ticket) != null)
                .findFirst()
                .orElseThrow(() -> new ParkingException(ParkingExceptionMessage.UNRECOGNIZED_PARKING_TICKET_MESSAGE))
                .fetchCar(ticket);
    }
}
