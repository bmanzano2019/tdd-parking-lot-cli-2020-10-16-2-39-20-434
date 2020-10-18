package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingCapacityException;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> groupParkingLots) {
        super(groupParkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        // find parking lot with largest parking capacity
        ParkingLot targetParkingLot = null;
        int largestParkingCapacity = 0;
        for (ParkingLot parkingLot : groupParkingLots) {
            int parkingCapacity = parkingLot.getCurrentParkingCapacity();
            if (parkingCapacity > largestParkingCapacity) {
                targetParkingLot = parkingLot;
                largestParkingCapacity = parkingCapacity;
            }
        }
        if (targetParkingLot != null) {
            return targetParkingLot.addCar(car);
        }

        // all parking lots handled are full
        throw new FullParkingCapacityException();
    }
}
