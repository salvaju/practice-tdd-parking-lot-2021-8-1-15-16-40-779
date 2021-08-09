package com.parkinglot;

import java.util.List;

public abstract class ParkingBoy {

    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLots;

    public abstract ParkingTicket park(Car car);

    public boolean isParkingLotsNull() {
        return parkingLots == null;
    }

}
