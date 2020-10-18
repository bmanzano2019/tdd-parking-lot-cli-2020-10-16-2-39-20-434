package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy {
    private List<ParkingBoy> parkerList;

    public ParkingLotServiceManager(ParkingLot parkingLot) {
        super(parkingLot);
        parkerList = new ArrayList<>();
    }

    public ParkingLotServiceManager(List<ParkingLot> groupParkingLots) {
        super(groupParkingLots);
    }

    public void addBoyToParkerList(ParkingBoy parkingBoy) {
        parkerList.add(parkingBoy);
    }

    public ParkingTicket park(ParkingBoy parkingBoy, Car car) {
        return parkingBoy.park(car);
    }

    public Car fetchCar(ParkingBoy parkingBoy, ParkingTicket ticket) {
        return parkingBoy.fetchCar(ticket);
    }
}
