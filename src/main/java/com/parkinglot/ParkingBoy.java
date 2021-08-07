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

        if (parkingLots != null){

            if (parkingLots.get(0).isFullCapacity()){
                return parkingLots.get(0).park(car);
            } else {
                return parkingLots.get(1).park(car);
            }
        }

        return parkingLot.park(car);

    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLot.fetch(parkingTicket);
    }

    public boolean isParkedOnFirstParkingLot(ParkingTicket parkingTicket) {
        return parkingLots.get(0).isRecognizedParkingTicket(parkingTicket);
    }

    public boolean isParkedOnSecondParkingLot(ParkingTicket parkingTicket) {
        return parkingLots.get(1).isRecognizedParkingTicket(parkingTicket);
    }
}
