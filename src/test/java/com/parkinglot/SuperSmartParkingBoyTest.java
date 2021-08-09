package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SuperSmartParkingBoyTest {

    @Test
    public void should_car_park_to_the_first_parking_lot_when_super_smart_parking_boy_parks_the_car_given_a_super_smart_parking_boy_with_two_parking_lots_with_available_position_and_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);

        //then
        assertTrue(superSmartParkingBoy.isParkedOnParkingLotNumber(parkingTicket, 1));
        assertFalse(superSmartParkingBoy.isParkedOnParkingLotNumber(parkingTicket, 2));
    }

    @Test
    public void should_car_park_to_the_second_parking_lot_when_super_smart_parking_boy_parks_the_car_given_a_parking_boy_with_super_smart_two_parking_lots_but_the_second_has_larger_available_position_rate_than_the_first_and_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(20);
        ParkingLot secondParkingLot2 = new ParkingLot(10);

        for (int i = 0; i < 3; i++) {
            firstParkingLot.park(new Car());
        }

        for (int i = 0; i < 3; i++) {
            secondParkingLot2.park(new Car());
        }

        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot2);

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        superSmartParkingBoy.park(new Car());


        Car secondCar = new Car();

        //when
        ParkingTicket secondParkingTicket = superSmartParkingBoy.park(secondCar);

        //then
        assertFalse(superSmartParkingBoy.isParkedOnParkingLotNumber(secondParkingTicket, 1));
        assertTrue(superSmartParkingBoy.isParkedOnParkingLotNumber(secondParkingTicket, 2));
    }

    @Test
    public void should_car_park_to_the_first_parking_lot_when_super_smart_parking_boy_parks_the_car_given_a_super_smart_parking_boy_with_two_parking_lots_but_the_first_has_larger_available_position_rate_than_the_second_and_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot2 = new ParkingLot(20);

        for (int i = 0; i < 3; i++) {
            firstParkingLot.park(new Car());
        }

        for (int i = 0; i < 3; i++) {
            secondParkingLot2.park(new Car());
        }

        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot2);

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        superSmartParkingBoy.park(new Car());


        Car secondCar = new Car();

        //when
        ParkingTicket secondParkingTicket = superSmartParkingBoy.park(secondCar);

        //then
        assertTrue(superSmartParkingBoy.isParkedOnParkingLotNumber(secondParkingTicket, 1));
        assertFalse(superSmartParkingBoy.isParkedOnParkingLotNumber(secondParkingTicket, 2));
    }

    @Test
    public void should_throw_exception_message_unrecognized_parking_ticket_when_super_smart_parking_boy_fetches_the_car_given_a_super_parking_boy_with_two_parking_lots_and_an_unrecognized_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(16));

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        ParkingTicket unrecognizedTicket = new ParkingTicket();

        //when & then
        Exception exception = assertThrows(Exception.class, () -> superSmartParkingBoy.fetch(unrecognizedTicket));
        assertTrue(exception.getMessage().contains("Unrecognized parking ticket"));
    }

    @Test
    public void should_throw_exception_message_unrecognized_parking_ticket_when_super_smart_parking_boy_fetches_the_car_given_a_super_smart_parking_boy_with_two_parking_lots_and_a_used_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        ParkingTicket usedTicket = superSmartParkingBoy.park(new Car());
        superSmartParkingBoy.fetch(usedTicket);

        //when & then
        Exception exception = assertThrows(Exception.class, () -> superSmartParkingBoy.fetch(usedTicket));
        assertTrue(exception.getMessage().contains("Unrecognized parking ticket"));
    }

    @Test
    public void should_throw_exception_message_no_available_position_when_super_smart_parking_boy_parks_the_car_given_a_super_smart_parking_boy_with_two_full_parking_lots_and_a_parking_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        superSmartParkingBoy.park(new Car());
        superSmartParkingBoy.park(new Car());

        //when & then
        Exception exception = assertThrows(Exception.class, () -> superSmartParkingBoy.park(new Car()));
        assertTrue(exception.getMessage().contains("No available position"));
    }

}
