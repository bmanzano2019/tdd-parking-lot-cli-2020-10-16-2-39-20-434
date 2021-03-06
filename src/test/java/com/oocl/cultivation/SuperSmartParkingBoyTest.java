package com.oocl.cultivation;

import com.oocl.cultivation.exception.ParkingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SuperSmartParkingBoyTest {
    private static final String TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE = "Unrecognized Parking Ticket.";
    private static final String TEST_NULL_PARKING_TICKET_MESSAGE = "Please provide your parking ticket.";
    private static final String TEST_FULL_PARKING_CAPACITY_MESSAGE = "Not enough position.";

    @Test
    void should_park_car_to_second_parking_lot_when_parking_car_given_two_parking_lots_and_second_lot_has_bigger_parking_ratio() {
        // given
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(1);
        SuperSmartParkingBoy superParkingBoy = new SuperSmartParkingBoy(firstParkingLot, secondParkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstTicket = superParkingBoy.park(firstCar);

        // when
        ParkingTicket secondTicket = superParkingBoy.park(secondCar);

        // then
        Assertions.assertTrue(secondParkingLot.checkIfCarInParkingLotByTicket(secondTicket));
        Assertions.assertSame(secondCar, superParkingBoy.fetchCar(secondTicket));
        Assertions.assertSame(firstCar, superParkingBoy.fetchCar(firstTicket));
    }

    @Test
    void should_return_parking_ticket_when_parking_given_car_to_super_smart_parking_boy() {
        // given
        Car car = new Car();
        SuperSmartParkingBoy superParkingBoy = new SuperSmartParkingBoy(new ParkingLot());

        // when
        ParkingTicket ticket = superParkingBoy.park(car);

        // then
        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_return_correct_car_when_fetch_car_given_parking_ticket_to_super_smart_parking_boy() {
        // given
        Car car = new Car();
        SuperSmartParkingBoy superParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        ParkingTicket ticket = superParkingBoy.park(car);

        // when
        // then
        Assertions.assertSame(car, superParkingBoy.fetchCar(ticket));
    }

    @Test
    void should_return_two_correct_cars_when_fetch_car_given_two_parking_tickets_to_super_smart_parking_boy() {
        // given
        Car car = new Car();
        Car otherCar = new Car();
        SuperSmartParkingBoy superParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        ParkingTicket ticket = superParkingBoy.park(car);
        ParkingTicket otherTicket = superParkingBoy.park(otherCar);

        // when
        // then
        Assertions.assertSame(car, superParkingBoy.fetchCar(ticket));
        Assertions.assertSame(otherCar, superParkingBoy.fetchCar(otherTicket));
    }

    @Test
    void should_return_no_car_when_fetch_car_given_wrong_ticket_to_super_smart_parking_boy() {
        // given
        Car car = new Car();
        SuperSmartParkingBoy superParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        ParkingTicket correctTicket = superParkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        // when
        // then
        Assertions.assertNotSame(correctTicket, wrongTicket);
        Exception thrownException = Assertions
                .assertThrows(ParkingException.class,
                        () -> superParkingBoy.fetchCar(wrongTicket));
        Assertions.assertEquals(TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_return_no_car_when_fetch_car_given_no_ticket_to_super_smart_parking_boy() {
        // given
        Car car = new Car();
        SuperSmartParkingBoy superParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        superParkingBoy.park(car);

        // when
        // then
        Exception thrownException = Assertions
                .assertThrows(ParkingException.class,
                        () -> superParkingBoy.fetchCar(null));
        Assertions.assertEquals(TEST_NULL_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_return_no_car_when_fetch_car_given_already_used_ticket_to_super_smart_parking_boy() {
        // given
        Car car = new Car();
        SuperSmartParkingBoy superParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        ParkingTicket ticket = superParkingBoy.park(car);
        superParkingBoy.fetchCar(ticket);

        // when
        // then
        Exception thrownException = Assertions
                .assertThrows(ParkingException.class,
                        () -> superParkingBoy.fetchCar(ticket));
        Assertions.assertEquals(TEST_UNRECOGNIZED_PARKING_TICKET_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_park_car_fail_and_return_no_ticket_when_park_car_given_parking_lot_capacity_one_and_car_already_parked() {
        // given
        SuperSmartParkingBoy superParkingBoy = new SuperSmartParkingBoy(new ParkingLot(1));
        superParkingBoy.park(new Car());

        // when
        // then
        Exception thrownException = Assertions
                .assertThrows(ParkingException.class,
                        () -> superParkingBoy.park(new Car()));
        Assertions.assertEquals(TEST_FULL_PARKING_CAPACITY_MESSAGE, thrownException.getMessage());
    }

    @Test
    void should_park_car_at_second_lot_when_park_car_given_first_parking_lot_is_full() {
        // given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        SuperSmartParkingBoy superParkingBoy = new SuperSmartParkingBoy(firstParkingLot, secondParkingLot);

        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstTicket = superParkingBoy.park(firstCar);

        // when
        ParkingTicket secondTicket = superParkingBoy.park(secondCar);

        // then
        Assertions.assertTrue(secondParkingLot.checkIfCarInParkingLotByTicket(secondTicket));
        Assertions.assertSame(firstCar, superParkingBoy.fetchCar(firstTicket));
        Assertions.assertSame(secondCar, superParkingBoy.fetchCar(secondTicket));
    }

    @Test
    void should_park_three_cars_in_order_of_lots_first_second_third_when_park_three_cars_given_four_parking_lots_with_different_max_capacity() {
        // given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        ParkingLot thirdParkingLot = new ParkingLot(3);
        ParkingLot fourthParkingLot = new ParkingLot(4);
        SuperSmartParkingBoy superParkingBoy = new SuperSmartParkingBoy(firstParkingLot, secondParkingLot, thirdParkingLot, fourthParkingLot);

        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thirdCar = new Car();

        // when
        ParkingTicket firstTicket = superParkingBoy.park(firstCar);
        ParkingTicket secondTicket = superParkingBoy.park(secondCar);
        ParkingTicket thirdTicket = superParkingBoy.park(thirdCar);

        // then
        Assertions.assertTrue(firstParkingLot.isFullCapacity());

        Assertions.assertFalse(secondParkingLot.isFullCapacity());
        Assertions.assertEquals(1, secondParkingLot.getCurrentParkingCapacity());

        Assertions.assertFalse(thirdParkingLot.isFullCapacity());
        Assertions.assertEquals(2, thirdParkingLot.getCurrentParkingCapacity());

        Assertions.assertFalse(fourthParkingLot.isFullCapacity());
        Assertions.assertEquals(4, fourthParkingLot.getCurrentParkingCapacity());

        Assertions.assertTrue(firstParkingLot.checkIfCarInParkingLotByTicket(firstTicket));
        Assertions.assertTrue(secondParkingLot.checkIfCarInParkingLotByTicket(secondTicket));
        Assertions.assertTrue(thirdParkingLot.checkIfCarInParkingLotByTicket(thirdTicket));

        Assertions.assertSame(thirdCar, superParkingBoy.fetchCar(thirdTicket));
        Assertions.assertSame(firstCar, superParkingBoy.fetchCar(firstTicket));
        Assertions.assertSame(secondCar, superParkingBoy.fetchCar(secondTicket));
    }

}
