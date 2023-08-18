package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;
import com.parkinglot.exceptions.UnrecognizedTicketException;
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

        //when
        parkingLot.fetch(parkingTicket);

        //then
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetch(parkingTicket);
        });
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_null_when_fetch_given_used_parking_lot_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket usedTicket = parkingLot.park(car);

        //when
        parkingLot.fetch(usedTicket);

        //then
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetch(usedTicket);
        });
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_null_when_fetch_given_parking_lot_without_capacity() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();

        //when
        parkingLot.park(car);

        //then
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> {
            parkingLot.park(car);
        });
        assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
}
