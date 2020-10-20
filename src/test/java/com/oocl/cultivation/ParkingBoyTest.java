package com.oocl.cultivation;

import com.oocl.cultivation.exception.ParkingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingBoyTest {
    private static final String TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE = "Unrecognized Parking Ticket.";
    private static final String TEST_NULL_PARKING_TICKET_MESSAGE = "Please provide your parking ticket.";
    private static final String TEST_FULL_PARKING_CAPACITY_MESSAGE = "Not enough position.";

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
                .assertThrows(ParkingException.class,
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
                .assertThrows(ParkingException.class,
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
                .assertThrows(ParkingException.class,
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
        Exception thrownException = Assertions
                .assertThrows(ParkingException.class,
                        () -> parkingBoy.park(new Car()));
        Assertions.assertEquals(TEST_FULL_PARKING_CAPACITY_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_park_car_at_second_lot_when_park_car_given_first_parking_lot_is_full() {
        // given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);

        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstTicket = parkingBoy.park(firstCar);

        // when
        ParkingTicket secondTicket = parkingBoy.park(secondCar);
        
        // then
        Assertions.assertTrue(secondParkingLot.checkIfCarInParkingLotByTicket(secondTicket));
        Assertions.assertSame(firstCar, parkingBoy.fetchCar(firstTicket));
        Assertions.assertSame(secondCar, parkingBoy.fetchCar(secondTicket));
    }

    @Test
    void should_park_car_at_third_lot_when_park_car_given_four_parking_lots_and_first_second_parking_lots_are_full() {
        // given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingLot thirdParkingLot = new ParkingLot();
        ParkingLot fourthParkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot, thirdParkingLot, fourthParkingLot);

        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thirdCar = new Car();
        ParkingTicket firstTicket = parkingBoy.park(firstCar);
        ParkingTicket secondTicket = parkingBoy.park(secondCar);

        // when
        ParkingTicket thirdTicket = parkingBoy.park(thirdCar);

        // then
        Assertions.assertTrue(thirdParkingLot.checkIfCarInParkingLotByTicket(thirdTicket));
        Assertions.assertSame(thirdCar, parkingBoy.fetchCar(thirdTicket));
        Assertions.assertSame(firstCar, parkingBoy.fetchCar(firstTicket));
        Assertions.assertSame(secondCar, parkingBoy.fetchCar(secondTicket));
    }
    
}
