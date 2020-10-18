package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingCapacityException;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(List<ParkingLot> groupParkingLots) {
        super(groupParkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        // find parking lot with largest parking ratio
        ParkingLot targetParkingLot = null;
        float largestParkingRatio = 0;
        for (ParkingLot parkingLot : groupParkingLots) {
            float parkingRatio = (float) parkingLot.getCurrentParkingCapacity() / parkingLot.getMaxParkingCapacity();
            if (parkingRatio > largestParkingRatio) {
                targetParkingLot = parkingLot;
                largestParkingRatio = parkingRatio;
            }
        }
        if (targetParkingLot != null) {
            return targetParkingLot.addCar(car);
        }

        // all parking lots handled are full
        throw new FullParkingCapacityException();
    }
}
