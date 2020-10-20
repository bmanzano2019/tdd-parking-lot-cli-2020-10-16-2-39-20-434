package com.oocl.cultivation;

import com.oocl.cultivation.exception.ParkingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingLotServiceManagerTest {
    private static final String TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE = "Unrecognized Parking Ticket.";
    private static final String TEST_NULL_PARKING_TICKET_MESSAGE = "Please provide your parking ticket.";
    private static final String TEST_FULL_PARKING_CAPACITY_MESSAGE = "Not enough position.";

    @Test
    void should_two_parking_boys_have_parked_one_car_when_park_two_cars_given_parking_manager_has_two_boys_with_parking_lot() {
        // given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy firstParkingBoy = new ParkingBoy(firstParkingLot);
        ParkingBoy secondParkingBoy = new ParkingBoy(secondParkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingLotServiceManager parkingManager = new ParkingLotServiceManager(firstParkingBoy, secondParkingBoy);

        // when
        ParkingTicket firstTicket = parkingManager.park(firstCar);
        ParkingTicket secondTicket = parkingManager.park(secondCar);

        // then
        Assertions.assertTrue(firstParkingLot.checkIfCarInParkingLotByTicket(firstTicket));
        Assertions.assertTrue(secondParkingLot.checkIfCarInParkingLotByTicket(secondTicket));
    }

    @Test
    void should_fetch_two_correct_cars_when_fetch_two_cars_given_parking_manager_has_two_boys_with_parked_cars_each() {
        // given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy firstParkingBoy = new ParkingBoy(firstParkingLot);
        ParkingBoy secondParkingBoy = new ParkingBoy(secondParkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingLotServiceManager parkingManager = new ParkingLotServiceManager(firstParkingBoy, secondParkingBoy);

        ParkingTicket firstTicket = parkingManager.park(firstCar);
        ParkingTicket secondTicket = parkingManager.park(secondCar);

        // when
        // then
        Assertions.assertSame(firstCar, parkingManager.fetchCar(firstTicket));
        Assertions.assertSame(secondCar, parkingManager.fetchCar(secondTicket));
    }

    @Test
    void should_park_car_fail_and_return_no_ticket_when_park_car_given_parking_manager_with_one_parking_boy_and_parking_lot_is_full() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
        ParkingLotServiceManager parkingManager = new ParkingLotServiceManager(parkingBoy);

        parkingManager.park(new Car());

        // when
        // then
        Exception thrownException = Assertions
                .assertThrows(ParkingException.class,
                        () -> parkingManager.park(new Car()));
        Assertions.assertEquals(TEST_FULL_PARKING_CAPACITY_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_return_no_car_when_fetch_car_given_no_ticket_to_parking_manager_passed_to_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingLotServiceManager parkingManager = new ParkingLotServiceManager(parkingBoy);

        parkingManager.park(new Car());

        // when
        // then
        Exception thrownException = Assertions
                .assertThrows(ParkingException.class,
                        () -> parkingManager.fetchCar(null));
        Assertions.assertEquals(TEST_NULL_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_return_no_car_when_fetch_car_given_wrong_ticket_to_parking_manager_passed_to_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingLotServiceManager parkingManager = new ParkingLotServiceManager(parkingBoy);

        ParkingTicket correctTicket = parkingManager.park(new Car());
        ParkingTicket wrongTicket = new ParkingTicket();

        // when
        // then
        Assertions.assertNotSame(correctTicket, wrongTicket);
        Exception thrownException = Assertions
                .assertThrows(ParkingException.class,
                        () -> parkingManager.fetchCar(wrongTicket));
        Assertions.assertEquals(TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_return_no_car_when_fetch_car_given_already_used_ticket_to_parking_manager_passed_to_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingLotServiceManager parkingManager = new ParkingLotServiceManager(parkingBoy);

        ParkingTicket ticket = parkingManager.park(new Car());
        parkingManager.fetchCar(ticket);

        // when
        // then
        Exception thrownException = Assertions
                .assertThrows(ParkingException.class,
                        () -> parkingManager.fetchCar(ticket));
        Assertions.assertEquals(TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }

}
