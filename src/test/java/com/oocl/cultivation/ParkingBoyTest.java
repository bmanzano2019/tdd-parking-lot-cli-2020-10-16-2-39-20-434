package com.oocl.cultivation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingBoyTest {

    @Test
    void should_return_parkingticket_when_parking_given_car_to_parkingboy() {
        // given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        // when
        ParkingTicket ticket = parkingBoy.park(car);

        // then
        Assertions.assertNotNull(ticket);
    }

    @Test
    void should_return_correctcar_when_fetchcar_given_parkingticket_to_parkingboy() {
        // given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket ticket = parkingBoy.park(car);

        // when
        // then
        Assertions.assertSame(car, parkingBoy.fetchCar(ticket));
    }

}
