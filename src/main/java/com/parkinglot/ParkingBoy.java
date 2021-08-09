package com.parkinglot;

import java.util.List;

public abstract class ParkingBoy {

    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLots;

    public abstract ParkingTicket park(Car car);

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

}
