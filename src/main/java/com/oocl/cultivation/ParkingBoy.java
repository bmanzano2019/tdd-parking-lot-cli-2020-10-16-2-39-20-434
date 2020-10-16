package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingBoy {
    private final Map<ParkingTicket, Car> carTicketMapper = new HashMap<>();

    public ParkingBoy(ParkingLot parkingLot) {

    }

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        carTicketMapper.put(ticket, car);

        return ticket;
    }

    public Car fetchCar(ParkingTicket ticket) {
        return carTicketMapper.remove(ticket);
    }
}
