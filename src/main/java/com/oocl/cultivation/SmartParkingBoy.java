package com.oocl.cultivation;

import com.oocl.cultivation.exception.ParkingException;
import com.oocl.cultivation.utils.ParkingExceptionMessage;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> groupParkingLots) {
        super(groupParkingLots);
    }

    @Override
    ParkingLot findAvailableParkingLot() {
        return groupParkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFullCapacity())
                .max(Comparator.comparing(ParkingLot::getCurrentParkingCapacity))
                .orElse(null);
    }
}
