package com.parkinglot;

import com.parkinglot.exceptions.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StandardParkingBoyTest {

    @Test
    void should_park_to_first_parking_lot_when_park_given_a_standard_parking_boy_and_two_parking_lots_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = standardParkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }
    @Test
    void should_return_parked_car_when_fetch_given_a_parking_lot_a_standard_parking_boy_and_a_ticket(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(parkingLot));
        Car car = new Car();
        ParkingTicket parkingTicket = standardParkingBoy.park(car);

        //when
        Car fetchedCar = standardParkingBoy.fetch(parkingTicket);

        //then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_the_right_car_when_fetch_given_parking_lot_with_two_cars_and_two_parking_tickets_and_standard_parking_boy(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(parkingLot));
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket parkingTicket1 = standardParkingBoy.park(firstCar);
        ParkingTicket parkingTicket2 = standardParkingBoy.park(secondCar);

        //when
        Car fetchedFirstCar = standardParkingBoy.fetch(parkingTicket1);
        Car fetchedSecondCar = standardParkingBoy.fetch(parkingTicket2);

        //then
        assertEquals(firstCar, fetchedFirstCar);
        assertEquals(secondCar, fetchedSecondCar);
    }

    @Test
    void should_return_unrecognized_parking_ticket_when_fetch_given_a_parking_lot_a_standard_parking_boy_and_wrong_ticket(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(parkingLot));
        Car car = new Car();
        standardParkingBoy.park(car);
        ParkingTicket wrongParkingTicket = new ParkingTicket();

        //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetch(wrongParkingTicket);
        });

        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }
}
