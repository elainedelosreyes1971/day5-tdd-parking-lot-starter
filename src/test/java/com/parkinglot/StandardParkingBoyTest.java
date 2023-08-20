package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;
import com.parkinglot.exceptions.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            standardParkingBoy.fetch(wrongParkingTicket);
        });

        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }
    
    @Test
    void should_return_unrecognized_parking_ticket_when_fetch_given_a_parking_lot_a_standard_parking_boy_and_a_used_ticket(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(parkingLot));
        Car car = new Car();
        ParkingTicket usedTicket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(usedTicket);
        Car newCar = new Car();
        standardParkingBoy.park(newCar);

        //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            standardParkingBoy.fetch(usedTicket);
        });
        
        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_no_available_position_when_park_given_parking_lot_with_no_space_a_standard_parking_boy_and_a_car(){
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(parkingLot));
        Car car = new Car();
        Car secondCar = new Car();

        //when
        standardParkingBoy.park(car);

        //then
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> {
            standardParkingBoy.park(secondCar);
        });
        assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
    
    @Test
    void should_park_to_second_parking_lot_when_park_given_a_standard_parking_boy_two_parking_lots_and_a_car(){
        //given
        Map<ParkingTicket, Car> initialParkedCars = new HashMap<>();
        initialParkedCars.put(new ParkingTicket(), new Car());
        initialParkedCars.put(new ParkingTicket(), new Car());
        ParkingLot firstParkingLot = new ParkingLot(2, initialParkedCars);
        ParkingLot secondParkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Car car = new Car();

        //when
        standardParkingBoy.park(car);
        
        //then
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(9, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_right_car_when_fetch_given_a_standard_parking_boy_two_parking_lots_with_parked_car_and_two_parking_ticket(){
        //given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstCarTicket = standardParkingBoy.park(firstCar);
        ParkingTicket secondCarTicket = standardParkingBoy.park(secondCar);

        //when
        Car fetchedFirstCar = standardParkingBoy.fetch(firstCarTicket);
        Car fetchedSecondCar = standardParkingBoy.fetch(secondCarTicket);

        //then
        assertEquals(firstCar, fetchedFirstCar);
        assertEquals(secondCar, fetchedSecondCar);
    }
    @Test
    void should_return_unrecognized_parking_ticket_when_fetch_given_a_standard_parking_boy_two_parking_lots_an_unrecognized_ticket(){
        //given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Car firstCar = new Car();
        ParkingTicket unusedTicket = new ParkingTicket();

        standardParkingBoy.park(firstCar);

        //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            standardParkingBoy.fetch(unusedTicket);
        });

        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }
}
