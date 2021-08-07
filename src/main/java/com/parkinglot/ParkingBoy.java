package com.parkinglot;

import java.util.List;

public class ParkingBoy {

    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        if (!isParkingLotsNull()) {
            return parkingLots.stream()
                    .filter(parkingLot -> !parkingLot.isFullCapacity())
                    .map(parkingLot -> parkingLot.park(car))
                    .findAny()
                    .orElse(null);
        }

        return parkingLot.park(car);
    }

    private boolean isParkingLotsNull() {
        return parkingLots == null;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (!isParkingLotsNull()) {
            return parkingLots.stream()
                    .filter(parkingLot -> parkingLot.isRecognizedParkingTicket(parkingTicket))
                    .map(parkingLot -> parkingLot.fetch(parkingTicket))
                    .findAny()
                    .orElse(null);
        }

        return parkingLot.fetch(parkingTicket);
    }

    public boolean isParkedOnParkingLotNumber(ParkingTicket parkingTicket, int parkingLotNumber) {
        return parkingLots.get(parkingLotNumber - 1).isRecognizedParkingTicket(parkingTicket);
    }
}
