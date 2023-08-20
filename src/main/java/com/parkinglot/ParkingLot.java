package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;
import com.parkinglot.exceptions.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    public static final int DEFAULT_CAPACITY = 10;
    private Car car;
    private Map<ParkingTicket, Car> parkedCars = new HashMap<>();
    private int capacity;

    public ParkingLot() {
        this(DEFAULT_CAPACITY);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot(int capacity, Map<ParkingTicket, Car> parkedCars) {
        this.capacity = capacity;
        this.parkedCars = parkedCars;
    }


    public ParkingTicket park(Car car) {
        if (!hasAvailableCapacity()) {
            throw new NoAvailablePositionException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        this.car = car;
        parkedCars.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (!hasParkedCar(parkingTicket)) {
            throw new UnrecognizedTicketException();
        }
        Car fetchedCar = parkedCars.get(parkingTicket);
        parkedCars.remove(parkingTicket);
        return fetchedCar;
    }

    public int getAvailableCapacity() {
        return capacity - parkedCars.size();
    }

    public boolean hasAvailableCapacity() {
        return !(parkedCars.size() == capacity);
    }

    public boolean hasParkedCar(ParkingTicket parkingTicket) {
        return parkedCars.containsKey(parkingTicket);
    }

    public double getHighestVacancyRate() {
        return ((double) parkedCars.size() / capacity) * 100;
    }
}
