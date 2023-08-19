package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;
import com.parkinglot.exceptions.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    public static final int DEFAULT_CAPACITY = 10;
    private Car car;
    private final Map<ParkingTicket, Car> parkedCars = new HashMap<>();
    private int capacity;

    public ParkingLot() {
        this(DEFAULT_CAPACITY);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        if(isFull()) {
            throw new NoAvailablePositionException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        this.car = car;
        parkedCars.put(parkingTicket, car);
        capacity--;
        return parkingTicket;
    }

    private boolean isFull() {
        return parkedCars.size() == capacity;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (isValidTicket(parkingTicket)){
            throw new UnrecognizedTicketException();
        }
        Car fetchedCar = parkedCars.get(parkingTicket);
        parkedCars.remove(parkingTicket);
        capacity++;
        return fetchedCar;
    }

    private boolean isValidTicket(ParkingTicket parkingTicket) {
        return parkedCars.get(parkingTicket) == null || !parkedCars.containsKey(parkingTicket);
    }

    public int getAvailableCapacity() {
        return capacity;
    }

    public boolean hasAvailableCapacity() {
        return !isFull();
    }

    public boolean hasParkedCar(ParkingTicket parkingTicket) {
        return parkedCars.containsKey(parkingTicket);
    }
}
