package com.oocl.cultivation;

import com.oocl.cultivation.exception.ParkingException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.utils.ParkingExceptionMessage;

import java.util.Arrays;
import java.util.List;

public class ParkingBoy {
    List<ParkingLot> groupParkingLots;

    public ParkingBoy(ParkingLot parkingLot) {
        groupParkingLots = Arrays.asList(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> groupParkingLots) {
        this.groupParkingLots = groupParkingLots;
    }

    // TODO: refactor the parkingLot searching to another method
    // TODO: and then have the subclasses override this function instead
    // TODO: delete comment
    public ParkingTicket park(Car car) {
        // TODO try to refactor to lambda function later
        for (ParkingLot parkingLot : groupParkingLots) {
            if (!parkingLot.isFullCapacity()) {
                return parkingLot.addCar(car);
            }
        }

        throw new ParkingException(ParkingExceptionMessage.FULL_PARKING_CAPACITY_MESSAGE);
    }

    // TODO: refactor the parkingLot searching to another method
    // TODO: and then have the subclasses override this function instead
    public Car fetchCar(ParkingTicket ticket) {
        // TODO try to refactor to lambda function later
        Car fetchedCar;

        if (ticket == null) {
            throw new ParkingException(ParkingExceptionMessage.NULL_PARKING_TICKET_MESSAGE);
        }

        for (ParkingLot parkingLot : groupParkingLots) {
            fetchedCar = parkingLot.removeCar(ticket);
            if (fetchedCar != null) {
                return fetchedCar;
            }
        }

        throw new UnrecognizedParkingTicketException();
    }
}
