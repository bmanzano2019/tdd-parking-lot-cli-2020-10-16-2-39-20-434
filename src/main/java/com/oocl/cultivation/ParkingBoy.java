package com.oocl.cultivation;

public class ParkingBoy {
    public ParkingBoy(ParkingLot parkingLot) {
    }

    public ParkingTicket park(Car car) {
        return new ParkingTicket();
    }

    public Car fetchCar(ParkingTicket ticket) {
        return new Car();
    }
}
