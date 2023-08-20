package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy extends StandardParkingBoy {

    private final List<ParkingLot> parkingLots;

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.parkingLots = parkingLots;
    }

    public ParkingLot getHighestVacancyRate() {
        return parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getHighestVacancyRate))
                .orElse(null);
    }
}
