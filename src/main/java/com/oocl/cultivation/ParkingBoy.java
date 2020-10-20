package com.oocl.cultivation;

import com.oocl.cultivation.exception.ParkingException;
import com.oocl.cultivation.utils.ParkingExceptionMessage;

import java.util.Arrays;
import java.util.List;

public class ParkingBoy implements Parkable {
    List<ParkingLot> groupParkingLots;

    public ParkingBoy(ParkingLot... groupParkingLots) {
        this.groupParkingLots = Arrays.asList(groupParkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot targetParkingLot = findAvailableParkingLot();
        if (targetParkingLot != null) {
            return targetParkingLot.addCar(car);
        }
        throw new ParkingException(ParkingExceptionMessage.FULL_PARKING_CAPACITY_MESSAGE);
    }

    @Override
    public Car fetchCar(ParkingTicket ticket) {
        if (ticket == null) {
            throw new ParkingException(ParkingExceptionMessage.NULL_PARKING_TICKET_MESSAGE);
        }
        ParkingLot targetParkingLot = findParkingLotWhereCarIsParked(ticket);
        if (targetParkingLot != null) {
            return targetParkingLot.removeCar(ticket);
        }
        throw new ParkingException(ParkingExceptionMessage.UNRECOGNIZED_PARKING_TICKET_MESSAGE);
    }

    @Override
    public ParkingLot findParkingLotWhereCarIsParked(ParkingTicket ticket) {
        return groupParkingLots.stream()
                .filter(parkingLot -> parkingLot.checkIfCarInParkingLotByTicket(ticket))
                .findFirst()
                .orElse(null);
    }

    @Override
    public ParkingLot findAvailableParkingLot() {
        return groupParkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFullCapacity())
                .findFirst()
                .orElse(null);
    }

}
