package com.oocl.cultivation;

public interface Parkable {
    ParkingTicket park(Car car);

    Car fetchCar(ParkingTicket ticket);

    ParkingLot findAvailableParkingLot();

    ParkingLot findParkingLotWhereCarIsParked(ParkingTicket ticket);
}
