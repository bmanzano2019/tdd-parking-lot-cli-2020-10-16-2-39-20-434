package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int parkingCapacity;
    private static final int DEFAULT_PARKING_CAPACITY = 10;
    private final Map<ParkingTicket, Car> carTicketMapper = new HashMap<>();

    public ParkingLot() {
        this.parkingCapacity = DEFAULT_PARKING_CAPACITY;
    }

    public ParkingLot(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
    }

    public boolean checkIfCarInParkingLotByTicket(ParkingTicket ticket) {
        return carTicketMapper.containsKey(ticket);
    }

    public boolean isFullCapacity() {
        return carTicketMapper.size() >= parkingCapacity;
    }

    ParkingTicket addCar(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        carTicketMapper.put(ticket, car);
        return ticket;
    }

    Car removeCar(ParkingTicket ticket) {
        Car returnCar = carTicketMapper.remove(ticket);
        return returnCar;
    }

    int getCurrentParkingCapacity() {
        return parkingCapacity - carTicketMapper.size();
    }

    int getMaxParkingCapacity() {
        return parkingCapacity;
    }
}
