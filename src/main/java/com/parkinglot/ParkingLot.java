package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int DEFAULT_CAPACITY = 10;
    private final Map<ParkingTicket, Car> parkedCars;
    private final int capacity;

    public ParkingLot() {
        this(DEFAULT_CAPACITY);
    }

    public ParkingLot(int capacity) {
        parkedCars = new HashMap<>();
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {

        if (isFullCapacity()) {
            throw new NoAvailablePositionException();
        }

        ParkingTicket parkingTicket = new ParkingTicket();
        parkedCars.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {

        if (!isRecognizedParkingTicket(parkingTicket)) {
            throw new UnrecognizedParkingTicketException();
        }

        Car parkedCar = parkedCars.get(parkingTicket);
        parkedCars.remove(parkingTicket);
        return parkedCar;
    }

    private boolean isFullCapacity() {
        return capacity == parkedCars.size();
    }

    private boolean isRecognizedParkingTicket(ParkingTicket parkingTicket) {
        return parkedCars.containsKey(parkingTicket);
    }
}
