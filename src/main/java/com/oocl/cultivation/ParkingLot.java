package com.oocl.cultivation;

import com.oocl.cultivation.exception.NullParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int parkingCapacity;
    private int currentCarCount = 0;
    private final static int DEFAULT_PARKING_CAPACITY = 10;
    private final Map<ParkingTicket, Car> carTicketMapper = new HashMap<>();

    public ParkingLot() {
        this.parkingCapacity = DEFAULT_PARKING_CAPACITY;
    }

    public ParkingLot(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
    }

    public ParkingTicket addCar(Car car) {
        if (currentCarCount >= parkingCapacity) {
            return null;
        }
        ParkingTicket ticket = new ParkingTicket();
        carTicketMapper.put(ticket, car);
        currentCarCount++;

        return ticket;
    }

    public Car removeCar(ParkingTicket ticket) {
        if (ticket == null) {
            throw new NullParkingTicketException();
        }

        Car returnCar = carTicketMapper.remove(ticket);

        if (returnCar == null) {
            throw new UnrecognizedParkingTicketException();
        }
        currentCarCount--;
        return returnCar;
    }
}
