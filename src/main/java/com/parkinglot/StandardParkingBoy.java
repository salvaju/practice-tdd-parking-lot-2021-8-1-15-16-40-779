package com.parkinglot;

import java.util.List;

public class StandardParkingBoy extends ParkingBoy{

    public StandardParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        if (!isParkingLotsNull()) {
            return getParkingLots().stream()
                    .filter(parkingLot -> !parkingLot.isFullCapacity())
                    .map(parkingLot -> parkingLot.park(car))
                    .findAny()
                    .orElseThrow(NoAvailablePositionException::new);
        }

        return getParkingLot().park(car);
    }
}
