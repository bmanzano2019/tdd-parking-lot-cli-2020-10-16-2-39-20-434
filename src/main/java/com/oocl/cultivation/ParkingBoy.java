package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> groupParkingLots;

    public ParkingBoy(ParkingLot parkingLot) {
        groupParkingLots = new ArrayList<>();
        groupParkingLots.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> groupParkingLots) {
        // clone this if necessary since lists are passed by reference
        this.groupParkingLots = groupParkingLots;
    }

    public ParkingTicket park(Car car) {
        // try to refactor to lambda function later
        RuntimeException caughtException = null;
        for (ParkingLot parkingLot : groupParkingLots) {
            try {
                return parkingLot.addCar(car);
            } catch (RuntimeException parkingException) {
                // do nothing, scan all parking lots first
                caughtException = parkingException;
            }
        }

        // handle scenario if the parking lot list is NULL
        throw caughtException;
    }

    public Car fetchCar(ParkingTicket ticket) {
        // try to refactor to lambda function later
        RuntimeException caughtException = null;
        for (ParkingLot parkingLot : groupParkingLots) {
            try {
                return parkingLot.removeCar(ticket);
            } catch (RuntimeException fetchingException) {
                // do nothing, scan all parking lots first
                caughtException = fetchingException;
            }
        }

        // handle scenario if the parking lot list is NULL
        throw caughtException;
    }
}
