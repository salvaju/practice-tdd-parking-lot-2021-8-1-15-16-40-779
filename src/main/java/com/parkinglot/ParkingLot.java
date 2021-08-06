package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int FULL_CAPACITY = 10;
    private final Map<ParkingTicket, Car> parkedCars = new HashMap<>();

    public ParkingTicket park(Car car) {

        if (FULL_CAPACITY == parkedCars.size()) {
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
