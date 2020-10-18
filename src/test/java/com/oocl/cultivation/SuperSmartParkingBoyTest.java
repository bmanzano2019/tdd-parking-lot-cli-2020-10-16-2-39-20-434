package com.oocl.cultivation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SuperSmartParkingBoyTest {

    @Test
    void should_park_car_to_second_parking_lot_when_parking_car_given_two_parking_lots_and_second_lot_has_bigger_parking_ratio() {
        // given
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> groupParkingLots = new ArrayList<>(0);
        groupParkingLots.add(firstParkingLot);
        groupParkingLots.add(secondParkingLot);
        SuperSmartParkingBoy superParkingBoy = new SuperSmartParkingBoy(groupParkingLots);
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
}
