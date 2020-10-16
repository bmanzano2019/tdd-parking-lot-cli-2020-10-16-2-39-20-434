package com.oocl.cultivation;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        if (parkingLot.isBelowParkingCapacity()) {
            return parkingLot.addCar(car);
        }
        return null;
    }

    public Car fetchCar(ParkingTicket ticket) {
        return parkingLot.removeCar(ticket);
    }
}
