package com.oocl.cultivation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private int maxParkingCapacity;
    private static final int DEFAULT_PARKING_CAPACITY = 10;
    private final Map<ParkingTicket, Car> carTicketMapper = new ConcurrentHashMap<>();

    public ParkingLot() {
        this.maxParkingCapacity = DEFAULT_PARKING_CAPACITY;
    }

    public ParkingLot(int maxParkingCapacity) {
        this.maxParkingCapacity = maxParkingCapacity;
    }

    public boolean checkIfCarInParkingLotByTicket(ParkingTicket ticket) {
        return carTicketMapper.containsKey(ticket);
    }

    public boolean isFullCapacity() {
        return carTicketMapper.size() >= maxParkingCapacity;
    }

    ParkingTicket addCar(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        carTicketMapper.put(ticket, car);
        return ticket;
    }

    Car removeCar(ParkingTicket ticket) {
        return carTicketMapper.remove(ticket);
    }

    int getCurrentParkingCapacity() {
        return maxParkingCapacity - carTicketMapper.size();
    }

    int getMaxParkingCapacity() {
        return maxParkingCapacity;
    }
}
