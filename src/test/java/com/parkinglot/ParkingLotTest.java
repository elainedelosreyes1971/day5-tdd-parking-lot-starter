package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void should_return_ticket_when_park_given_parking_lot_a_car(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingLot.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_the_car_when_fetch_given_parking_lot_and_a_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        //when
        Car fetchedCar = parkingLot.fetch(parkingTicket);

        //then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_car_with_each_ticket_when_fetch_the_car_twice_given_parking_lot_with_two_cars_and_two_tickets() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        Car car1 = new Car();
        ParkingTicket parkingTicket1 = parkingLot.park(car1);

        //when
        Car fetchedCar = parkingLot.fetch(parkingTicket);
        Car fetchedCar1 = parkingLot.fetch(parkingTicket1);

        //then
        assertEquals(car, fetchedCar);
        assertEquals(car1, fetchedCar1);
    }

    @Test
    void should_not_return_car_when_fetch_given_parking_lot_and_wrong_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        Car car1 = new Car();
        ParkingTicket parkingTicket1 = parkingLot.park(car1);

        //when
        Car fetchedCar = parkingLot.fetch(parkingTicket1);
        Car fetchedCar1 = parkingLot.fetch(parkingTicket);

        //then
        assertNotEquals(car, fetchedCar);
        assertNotEquals(car1, fetchedCar1);
    }
}
