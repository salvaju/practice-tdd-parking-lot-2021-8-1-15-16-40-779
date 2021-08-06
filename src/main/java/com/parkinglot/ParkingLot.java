package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkedCars = new HashMap<>();

    public ParkingTicket park(Car car) {

        if (parkedCars.size() == 10) {
            return null;
        }

        ParkingTicket parkingTicket = new ParkingTicket();
        parkedCars.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car parkedCar = parkedCars.get(parkingTicket);
        parkedCars.remove(parkingTicket);
        return parkedCar;
    }
}
