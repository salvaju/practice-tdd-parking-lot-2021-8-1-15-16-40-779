package com.parkinglot;

import java.util.List;

public class StandardParkingBoy {

    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLots;

    public StandardParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        if (!isParkingLotsNull()) {
            return parkingLots.stream()
                    .filter(parkingLot -> !parkingLot.isFullCapacity())
                    .map(parkingLot -> parkingLot.park(car))
                    .findAny()
                    .orElseThrow(NoAvailablePositionException::new);
        }

        return parkingLot.park(car);
    }

    public boolean isParkingLotsNull() {
        return parkingLots == null;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (!isParkingLotsNull()) {
            return parkingLots.stream()
                    .filter(parkingLot -> parkingLot.isRecognizedParkingTicket(parkingTicket))
                    .map(parkingLot -> parkingLot.fetch(parkingTicket))
                    .findAny()
                    .orElseThrow(UnrecognizedParkingTicketException::new);
        }

        return parkingLot.fetch(parkingTicket);
    }

    public boolean isParkedOnParkingLotNumber(ParkingTicket parkingTicket, int parkingLotNumber) {
        return parkingLots.get(parkingLotNumber - 1).isRecognizedParkingTicket(parkingTicket);
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
