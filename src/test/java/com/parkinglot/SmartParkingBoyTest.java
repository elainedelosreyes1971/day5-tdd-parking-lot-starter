package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmartParkingBoyTest {

    @Test
    void should_return_parking_lot_with_highest_empty_slot_when_park_given_a_smart_parking_boy_parking_lot_and_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(15);
        ParkingLot secondParkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Car firstCar = new Car();
        Car secondCar = new Car();

        //when
        smartParkingBoy.park(firstCar);
        smartParkingBoy.park(secondCar);

        //then
        assertEquals(firstParkingLot, smartParkingBoy.getHighestParkingSlot());
    }

}