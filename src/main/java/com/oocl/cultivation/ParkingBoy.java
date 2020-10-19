package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingCapacityException;
import com.oocl.cultivation.exception.NullParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {
    List<ParkingLot> groupParkingLots;

    public ParkingBoy(ParkingLot parkingLot) {
        groupParkingLots = Arrays.asList(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> groupParkingLots) {
        // TODO clone this if necessary since lists are passed by reference
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

        // all parking lots handled are full
        throw new FullParkingCapacityException();
    }

    // TODO: refactor the parkingLot searching to another method
    // TODO: and then have the subclasses override this function instead
    public Car fetchCar(ParkingTicket ticket) {
        // TODO try to refactor to lambda function later
        Car fetchedCar;

        if (ticket == null) {
            throw new NullParkingTicketException();
        }

        for (ParkingLot parkingLot : groupParkingLots) {
            fetchedCar = parkingLot.removeCar(ticket);
            if (fetchedCar != null) {
                return fetchedCar;
            }
        }

        // no car is fetched in any of the parking lots
        throw new UnrecognizedParkingTicketException();
    }
}
