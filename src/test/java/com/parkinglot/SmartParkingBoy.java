package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        if (!isParkingLotsNull()) {

            return getParkingLots().stream()
                    .filter(parkingLot -> !parkingLot.isFullCapacity())
                    .min(Comparator.comparing(parkingLot -> parkingLot.getParkedCars().size()))
                    .map(parkingLot -> parkingLot.park(car))
                    .orElse(null);

        }
        return getParkingLot().park(car);
    }
}
