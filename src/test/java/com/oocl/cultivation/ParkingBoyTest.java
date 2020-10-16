package com.oocl.cultivation;

import com.oocl.cultivation.exception.NullParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingBoyTest {
    private static final String TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE = "Unrecognized Parking Ticket.";
    private static final String TEST_NULL_PARKING_TICKET_MESSAGE = "Please provide your parking ticket.";

    @Test
    void should_return_parking_ticket_when_parking_given_car_to_parking_boy() {
        // given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        // when
        ParkingTicket ticket = parkingBoy.park(car);

        // then
        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_return_correct_car_when_fetch_car_given_parking_ticket_to_parking_boy() {
        // given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket ticket = parkingBoy.park(car);

        // when
        // then
        Assertions.assertSame(car, parkingBoy.fetchCar(ticket));
    }
    
    @Test
    void should_return_two_correct_cars_when_fetch_car_given_two_parking_tickets_to_parking_boy() {
        // given
        Car car = new Car();
        Car otherCar = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket ticket = parkingBoy.park(car);
        ParkingTicket otherTicket = parkingBoy.park(otherCar);
        
        // when
        // then
        Assertions.assertSame(car, parkingBoy.fetchCar(ticket));
        Assertions.assertSame(otherCar, parkingBoy.fetchCar(otherTicket));
    }

    @Test
    void should_return_no_car_when_fetch_car_given_wrong_ticket_to_parking_boy() {
        // given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket correctTicket = parkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        // when
        // then
        Assertions.assertNotSame(correctTicket, wrongTicket);
        Exception thrownException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class,
                        () -> parkingBoy.fetchCar(wrongTicket));
        Assertions.assertEquals(TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }
    
    @Test
    void should_return_no_car_when_fetch_car_given_no_ticket_to_parking_boy() {
        // given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        parkingBoy.park(car);

        // when
        // then
        Exception thrownException = Assertions
                .assertThrows(NullParkingTicketException.class,
                        () -> parkingBoy.fetchCar(null));
        Assertions.assertEquals(TEST_NULL_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }
    
    @Test
    void should_return_no_car_when_fetch_car_given_already_used_ticket_to_parking_boy() {
        // given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetchCar(ticket);

        // when
        // then
        Exception thrownException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class,
                        () -> parkingBoy.fetchCar(ticket));
        Assertions.assertEquals(TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }
    
    @Test
    void should_park_car_fail_and_return_no_ticket_when_park_car_given_parking_lot_capacity_one_and_car_already_parked() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
        parkingBoy.park(new Car());

        // when
        // then
        Assertions.assertNull(parkingBoy.park(new Car()));
    }

}
