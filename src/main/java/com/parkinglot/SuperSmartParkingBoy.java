package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends StandardParkingBoy {

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    private static double getLargerCapacity(ParkingLot parkingLot) {
        return ((double) parkingLot.getParkedCars().size() / (double) parkingLot.getCapacity());
    }

    @Override
    public ParkingTicket park(Car car) {
        if (!isParkingLotsNull()) {

            return getParkingLots().stream()
                    .filter(parkingLot -> !parkingLot.isFullCapacity())
                    .max(Comparator.comparing(SuperSmartParkingBoy::getLargerCapacity))
                    .map(parkingLot -> parkingLot.park(car))
                    .orElseThrow(NoAvailablePositionException::new);

        }
        return getParkingLot().park(car);
    }

}
