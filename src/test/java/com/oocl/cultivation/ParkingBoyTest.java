package com.oocl.cultivation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingBoyTest {

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

}
