package com.oocl.cultivation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingBoyTest {

    @Test
    void should_return_parking_ticket_when_parking_given_car_to_parkingboy() {
        // given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        // when
        ParkingTicket ticket = parkingBoy.park(car);

        // then
        Assertions.assertNotNull(ticket);
    }

}
