package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SuperParkingBoyTest {

    @Test
    void should_return_parking_lot_with_highest_vacancy_rate_when_park_given_a_super_parking_boy_parking_lot_and_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(11);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Car firstCar = new Car();
        Car secondCar = new Car();

        //when
        superParkingBoy.park(firstCar);
        superParkingBoy.park(secondCar);

        //then
        assertEquals(firstParkingLot, superParkingBoy.getHighestVacancyRate());
    }
}