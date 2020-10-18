package com.oocl.cultivation;

import com.oocl.cultivation.exception.FullParkingCapacityException;
import com.oocl.cultivation.exception.NullParkingTicketException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingLotServiceManagerTest {
    private static final String TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE = "Unrecognized Parking Ticket.";
    private static final String TEST_NULL_PARKING_TICKET_MESSAGE = "Please provide your parking ticket.";
    private static final String TEST_FULL_PARKING_CAPACITY_MESSAGE = "Not enough position.";

    @Test
    void should_two_parking_boys_have_parked_one_car_when_park_two_cars_given_parking_manager_has_two_boys_with_parking_lot() {
        // given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy firstParkingBoy = new ParkingBoy(firstParkingLot);
        ParkingBoy secondParkingBoy = new ParkingBoy(secondParkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingLotServiceManager parkingManager = new ParkingLotServiceManager(new ParkingLot());
        parkingManager.addBoyToParkerList(firstParkingBoy);
        parkingManager.addBoyToParkerList(secondParkingBoy);

        // when
        ParkingTicket firstTicket = parkingManager.park(firstParkingBoy, firstCar);
        ParkingTicket secondTicket = parkingManager.park(secondParkingBoy, secondCar);

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

        ParkingLotServiceManager parkingManager = new ParkingLotServiceManager(new ParkingLot());
        parkingManager.addBoyToParkerList(firstParkingBoy);
        parkingManager.addBoyToParkerList(secondParkingBoy);

        ParkingTicket firstTicket = parkingManager.park(firstParkingBoy, firstCar);
        ParkingTicket secondTicket = parkingManager.park(secondParkingBoy, secondCar);

        // when
        // then
        Assertions.assertSame(firstCar, parkingManager.fetchCar(firstParkingBoy, firstTicket));
        Assertions.assertSame(secondCar, parkingManager.fetchCar(secondParkingBoy, secondTicket));
    }

    @Test
    void should_park_car_fail_and_return_no_ticket_when_park_car_given_parking_manager_with_one_parking_boy_and_parking_lot_is_full() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(1));
        ParkingLotServiceManager parkingManager = new ParkingLotServiceManager(new ParkingLot());

        parkingManager.addBoyToParkerList(parkingBoy);
        parkingManager.park(parkingBoy, new Car());

        // when
        // then
        Exception thrownException = Assertions
                .assertThrows(FullParkingCapacityException.class,
                        () -> parkingManager.park(parkingBoy, new Car()));
        Assertions.assertEquals(TEST_FULL_PARKING_CAPACITY_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_return_no_car_when_fetch_car_given_no_ticket_to_parking_manager_passed_to_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingLotServiceManager parkingManager = new ParkingLotServiceManager(new ParkingLot());

        parkingManager.addBoyToParkerList(parkingBoy);
        parkingManager.park(parkingBoy, new Car());

        // when
        // then
        Exception thrownException = Assertions
                .assertThrows(NullParkingTicketException.class,
                        () -> parkingManager.fetchCar(parkingBoy, null));
        Assertions.assertEquals(TEST_NULL_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_return_no_car_when_fetch_car_given_wrong_ticket_to_parking_manager_passed_to_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingLotServiceManager parkingManager = new ParkingLotServiceManager(new ParkingLot());

        parkingManager.addBoyToParkerList(parkingBoy);
        ParkingTicket correctTicket = parkingManager.park(parkingBoy, new Car());
        ParkingTicket wrongTicket = new ParkingTicket();

        // when
        // then
        Assertions.assertNotSame(correctTicket, wrongTicket);
        Exception thrownException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class,
                        () -> parkingManager.fetchCar(parkingBoy, wrongTicket));
        Assertions.assertEquals(TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_return_no_car_when_fetch_car_given_already_used_ticket_to_parking_manager_passed_to_parking_boy() {
        // given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingLotServiceManager parkingManager = new ParkingLotServiceManager(new ParkingLot());

        parkingManager.addBoyToParkerList(parkingBoy);
        ParkingTicket ticket = parkingManager.park(parkingBoy, new Car());
        parkingManager.fetchCar(parkingBoy, ticket);

        // when
        // then
        Exception thrownException = Assertions
                .assertThrows(UnrecognizedParkingTicketException.class,
                        () -> parkingManager.fetchCar(parkingBoy, ticket));
        Assertions.assertEquals(TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }
}
