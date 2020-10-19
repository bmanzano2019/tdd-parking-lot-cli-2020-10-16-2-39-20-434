package com.oocl.cultivation;

import com.oocl.cultivation.exception.ParkingException;
import com.oocl.cultivation.utils.ParkingExceptionMessage;

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

        throw new ParkingException(ParkingExceptionMessage.FULL_PARKING_CAPACITY_MESSAGE);
    }
}
