package com.oocl.cultivation;

import com.oocl.cultivation.exception.UnlistedParkingBoyException;

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
        if (parkerList.contains(parkingBoy)) {
            return parkingBoy.park(car);
        }
        throw new UnlistedParkingBoyException();
    }

    public Car fetchCar(ParkingBoy parkingBoy, ParkingTicket ticket) {
        if (parkerList.contains(parkingBoy)) {
            return parkingBoy.fetchCar(ticket);
        }
        throw new UnlistedParkingBoyException();
    }
}
