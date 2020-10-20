package com.oocl.cultivation;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot... groupParkingLots) {
        super(groupParkingLots);
    }

    @Override
    public ParkingLot findAvailableParkingLot() {
        return groupParkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFullCapacity())
                .max(Comparator.comparing(ParkingLot::getCurrentParkingRatio))
                .orElse(null);
    }
}
