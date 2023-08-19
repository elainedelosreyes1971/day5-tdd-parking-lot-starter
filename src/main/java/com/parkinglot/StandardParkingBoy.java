package com.parkinglot;

import com.parkinglot.exceptions.NoAvailablePositionException;

import java.util.List;

public class StandardParkingBoy {
    private final List<ParkingLot> parkingLots;

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }
}
