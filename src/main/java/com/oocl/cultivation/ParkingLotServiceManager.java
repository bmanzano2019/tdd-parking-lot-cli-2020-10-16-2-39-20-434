package com.oocl.cultivation;

import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy {
    public ParkingLotServiceManager(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public ParkingLotServiceManager(List<ParkingLot> groupParkingLots) {
        super(groupParkingLots);
    }

    public ParkingTicket park(ParkingBoy parkingBoy, Car car) {
        return new ParkingTicket();
    }

    public void addBoyToParkerList(ParkingBoy parkingBoy) {
        // to be implemented
    }
}
