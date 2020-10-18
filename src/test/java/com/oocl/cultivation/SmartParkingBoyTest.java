package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingCapacityException;
import com.oocl.cultivation.exception.NullParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
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
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(groupParkingLots);
        Car car = new Car();

        // when
        ParkingTicket ticket = smartParkingBoy.park(car);

        // then
        Assertions.assertTrue(secondParkingLot.checkIfCarInParkingLotByTicket(ticket));
        Assertions.assertSame(car, smartParkingBoy.fetchCar(ticket));
    }

    @Test
    void should_return_parking_ticket_when_parking_given_car_to_smart_parking_boy() {
        // given
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());

        // when
        ParkingTicket ticket = smartParkingBoy.park(car);

        // then
        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_return_correct_car_when_fetch_car_given_parking_ticket_to_smart_parking_boy() {
        // given
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingTicket ticket = smartParkingBoy.park(car);

        // when
        // then
        Assertions.assertSame(car, smartParkingBoy.fetchCar(ticket));
    }

    @Test
    void should_return_two_correct_cars_when_fetch_car_given_two_parking_tickets_to_smart_parking_boy() {
        // given
        Car car = new Car();
        Car otherCar = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingTicket ticket = smartParkingBoy.park(car);
        ParkingTicket otherTicket = smartParkingBoy.park(otherCar);

        // when
        // then
        Assertions.assertSame(car, smartParkingBoy.fetchCar(ticket));
        Assertions.assertSame(otherCar, smartParkingBoy.fetchCar(otherTicket));
    }

    @Test
    void should_return_no_car_when_fetch_car_given_wrong_ticket_to_smart_parking_boy() {
        // given
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingTicket correctTicket = smartParkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        // when
        // then
        Assertions.assertNotSame(correctTicket, wrongTicket);
        Exception thrownException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class,
                        () -> smartParkingBoy.fetchCar(wrongTicket));
        Assertions.assertEquals(TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_return_no_car_when_fetch_car_given_no_ticket_to_smart_parking_boy() {
        // given
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        smartParkingBoy.park(car);

        // when
        // then
        Exception thrownException = Assertions
                .assertThrows(NullParkingTicketException.class,
                        () -> smartParkingBoy.fetchCar(null));
        Assertions.assertEquals(TEST_NULL_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_return_no_car_when_fetch_car_given_already_used_ticket_to_smart_parking_boy() {
        // given
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingTicket ticket = smartParkingBoy.park(car);
        smartParkingBoy.fetchCar(ticket);

        // when
        // then
        Exception thrownException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class,
                        () -> smartParkingBoy.fetchCar(ticket));
        Assertions.assertEquals(TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_park_car_fail_and_return_no_ticket_when_park_car_given_parking_lot_capacity_one_and_car_already_parked() {
        // given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(1));
        smartParkingBoy.park(new Car());

        // when
        // then
        Exception thrownException = Assertions
                .assertThrows(FullParkingCapacityException.class,
                        () -> smartParkingBoy.park(new Car()));
        Assertions.assertEquals(TEST_FULL_PARKING_CAPACITY_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_park_car_at_second_lot_when_park_car_given_first_parking_lot_is_full() {
        // given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> groupParkingLots = new ArrayList<>(0);
        groupParkingLots.add(firstParkingLot);
        groupParkingLots.add(secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(groupParkingLots);

        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstTicket = smartParkingBoy.park(firstCar);

        // when
        ParkingTicket secondTicket = smartParkingBoy.park(secondCar);

        // then
        Assertions.assertTrue(secondParkingLot.checkIfCarInParkingLotByTicket(secondTicket));
        Assertions.assertSame(firstCar, smartParkingBoy.fetchCar(firstTicket));
        Assertions.assertSame(secondCar, smartParkingBoy.fetchCar(secondTicket));
    }

    @Test
    void should_park_car_at_third_lot_when_park_car_given_four_parking_lots_and_first_second_parking_lots_are_full() {
        // given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingLot thirdParkingLot = new ParkingLot();
        ParkingLot fourthParkingLot = new ParkingLot();
        List<ParkingLot> groupParkingLots = new ArrayList<>(0);
        groupParkingLots.add(firstParkingLot);
        groupParkingLots.add(secondParkingLot);
        groupParkingLots.add(thirdParkingLot);
        groupParkingLots.add(fourthParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(groupParkingLots);

        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thirdCar = new Car();
        ParkingTicket firstTicket = smartParkingBoy.park(firstCar);
        ParkingTicket secondTicket = smartParkingBoy.park(secondCar);

        // when
        ParkingTicket thirdTicket = smartParkingBoy.park(thirdCar);

        // then
        Assertions.assertTrue(thirdParkingLot.checkIfCarInParkingLotByTicket(thirdTicket));
        Assertions.assertSame(thirdCar, smartParkingBoy.fetchCar(thirdTicket));
        Assertions.assertSame(firstCar, smartParkingBoy.fetchCar(firstTicket));
        Assertions.assertSame(secondCar, smartParkingBoy.fetchCar(secondTicket));
    }
}
