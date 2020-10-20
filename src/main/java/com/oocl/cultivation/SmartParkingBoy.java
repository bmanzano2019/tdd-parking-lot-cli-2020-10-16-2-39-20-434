package com.oocl.cultivation;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot... groupParkingLots) {
        super(groupParkingLots);
    }

    @Override
    public ParkingLot findAvailableParkingLot() {
        return groupParkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFullCapacity())
                .max(Comparator.comparing(ParkingLot::getCurrentParkingCapacity))
                .orElse(null);
    }
}
