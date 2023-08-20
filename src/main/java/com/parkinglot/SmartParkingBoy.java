package com.parkinglot;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends StandardParkingBoy{
    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.parkingLots = parkingLots;
    }

    public ParkingLot getHighestParkingSlot(){
        return parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getAvailableCapacity))
                .orElse(null);
    }
}
