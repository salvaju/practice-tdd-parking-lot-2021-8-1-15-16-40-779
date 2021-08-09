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
                    .max(Comparator.comparing(this::getHighestParkingLotSpace))
                    .map(parkingLot -> parkingLot.park(car))
                    .orElseThrow(NoAvailablePositionException::new);

        }
        return getParkingLot().park(car);
    }

    public int getHighestParkingLotSpace(ParkingLot parkingLot) {
        return parkingLot.getCapacity() - parkingLot.getParkedCars().size();
    }
}
