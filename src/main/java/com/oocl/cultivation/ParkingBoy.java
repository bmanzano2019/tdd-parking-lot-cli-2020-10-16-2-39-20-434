package com.oocl.cultivation;

import com.oocl.cultivation.exception.ParkingException;
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

    public ParkingTicket park(Car car) {
        ParkingLot targetParkingLot = findAvailableParkingLot();
        if (targetParkingLot != null) {
            return targetParkingLot.addCar(car);
        }
        throw new ParkingException(ParkingExceptionMessage.FULL_PARKING_CAPACITY_MESSAGE);
    }

    // TODO: refactor the parkingLot searching to another method
    // TODO: and then have the subclasses override this function instead
    public Car fetchCar(ParkingTicket ticket) {
        // TODO try to refactor to lambda function later
        if (ticket == null) {
            throw new ParkingException(ParkingExceptionMessage.NULL_PARKING_TICKET_MESSAGE);
        }

        for (ParkingLot parkingLot : groupParkingLots) {
            Car fetchedCar = parkingLot.removeCar(ticket);
            if (fetchedCar != null) {
                return fetchedCar;
            }
        }

        throw new ParkingException(ParkingExceptionMessage.UNRECOGNIZED_PARKING_TICKET_MESSAGE);
    }

    ParkingLot findAvailableParkingLot() {
        return groupParkingLots.stream().filter(parkingLot -> !parkingLot.isFullCapacity()).findFirst().orElse(null);
    }

}
