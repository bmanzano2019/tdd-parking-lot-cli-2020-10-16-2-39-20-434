package com.oocl.cultivation;

import com.oocl.cultivation.exception.ParkingException;
import com.oocl.cultivation.utils.ParkingExceptionMessage;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(List<ParkingLot> groupParkingLots) {
        super(groupParkingLots);
    }

    // TODO: consider moving the computing code to a function inside ParkingLot to be lambda-able
    @Override
    public ParkingTicket park(Car car) {
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

        throw new ParkingException(ParkingExceptionMessage.FULL_PARKING_CAPACITY_MESSAGE);
    }
}
