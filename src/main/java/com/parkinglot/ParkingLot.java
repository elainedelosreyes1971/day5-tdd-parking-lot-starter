package com.parkinglot;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingLot {

    private Car car;
    private final Map<ParkingTicket, Car> parkedCars = new HashMap<>();

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        this.car = car;
        parkedCars.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchedCar = parkedCars.get(parkingTicket);
        parkedCars.remove(parkingTicket);
        return fetchedCar;
    }
}
