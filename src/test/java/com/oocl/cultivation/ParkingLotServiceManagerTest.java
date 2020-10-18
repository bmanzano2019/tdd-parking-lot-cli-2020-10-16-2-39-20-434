package com.oocl.cultivation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingLotServiceManagerTest {

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
}
