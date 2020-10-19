package com.oocl.cultivation;

import com.oocl.cultivation.exception.ParkingException;
import com.oocl.cultivation.utils.ParkingExceptionMessage;

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
        parkerList = new ArrayList<>();
    }

    public void addBoyToParkerList(ParkingBoy parkingBoy) {
        parkerList.add(parkingBoy);
    }

    public ParkingTicket park(ParkingBoy parkingBoy, Car car) {
        if (parkerList.contains(parkingBoy)) {
            return parkingBoy.park(car);
        }
        throw new ParkingException(ParkingExceptionMessage.UNLISTED_PARKING_BOY_MESSAGE);
    }

    public Car fetchCar(ParkingBoy parkingBoy, ParkingTicket ticket) {
        if (parkerList.contains(parkingBoy)) {
            return parkingBoy.fetchCar(ticket);
        }
        throw new ParkingException(ParkingExceptionMessage.UNLISTED_PARKING_BOY_MESSAGE);
    }
}
