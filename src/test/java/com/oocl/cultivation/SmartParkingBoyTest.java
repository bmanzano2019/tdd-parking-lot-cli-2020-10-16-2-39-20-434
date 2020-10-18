package com.oocl.cultivation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SmartParkingBoyTest {
    private static final String TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE = "Unrecognized Parking Ticket.";
    private static final String TEST_NULL_PARKING_TICKET_MESSAGE = "Please provide your parking ticket.";
    private static final String TEST_FULL_PARKING_CAPACITY_MESSAGE = "Not enough position.";

    @Test
    void should_park_car_to_second_parking_lot_when_parking_car_given_two_parking_lots_and_second_lot_is_bigger() {
        // given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot(20);
        List<ParkingLot> groupParkingLots = new ArrayList<>(0);
        groupParkingLots.add(firstParkingLot);
        groupParkingLots.add(secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(groupParkingLots);
        Car firstCar = new Car();

        // when
        ParkingTicket firstTicket = parkingBoy.park(firstCar);

        // then
        Assertions.assertTrue(secondParkingLot.checkIfCarInParkingLotByTicket(firstTicket));
        Assertions.assertSame(firstCar, parkingBoy.fetchCar(firstTicket));
    }
}
