package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {

    @Test
    public void should_car_park_to_the_first_parking_lot_when_smart_parking_boy_parks_the_car_given_a_smart_parking_boy_with_two_parking_lots_with_available_position_and_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //then
        assertTrue(smartParkingBoy.isParkedOnParkingLotNumber(parkingTicket, 1));
        assertFalse(smartParkingBoy.isParkedOnParkingLotNumber(parkingTicket, 2));
    }

    @Test
    public void should_car_park_to_the_second_parking_lot_when_parking_boy_parks_the_car_given_a_parking_boy_with_two_parking_lots_but_the_first_has_less_available_position_than_the_second_and_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.park(new Car());

        Car secondCar = new Car();

        //when
        ParkingTicket secondParkingTicket = smartParkingBoy.park(secondCar);

        //then
        assertFalse(smartParkingBoy.isParkedOnParkingLotNumber(secondParkingTicket, 1));
        assertTrue(smartParkingBoy.isParkedOnParkingLotNumber(secondParkingTicket, 2));
    }

    @Test
    public void should_car_park_to_the_first_parking_lot_when_parking_boy_parks_the_car_given_a_parking_boy_with_two_parking_lots_but_the_second_has_less_available_position_than_the_first_and_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(2));
        parkingLots.add(new ParkingLot(2));

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());

        Car secondCar = new Car();

        //when
        ParkingTicket secondParkingTicket = smartParkingBoy.park(secondCar);

        //then
        assertTrue(smartParkingBoy.isParkedOnParkingLotNumber(secondParkingTicket, 1));
        assertFalse(smartParkingBoy.isParkedOnParkingLotNumber(secondParkingTicket, 2));
    }

    @Test
    public void should_return_the_right_car_with_correct_ticket_when_smart_parking_boy_fetches_the_car_twice_given_a_smart_parking_boy_with_two_parking_lots_with_two_parked_cars_on_each_parking_lot_and_two_parking_tickets() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car cocoCar = new Car();
        Car guraCar = new Car();

        ParkingTicket cocoParkingTicket = smartParkingBoy.park(cocoCar);
        ParkingTicket guraParkingTicket = smartParkingBoy.park(guraCar);


        //when
        Car cocoActualCar = smartParkingBoy.fetch(cocoParkingTicket);
        Car guraActualCar = smartParkingBoy.fetch(guraParkingTicket);

        //then
        assertEquals(cocoCar, cocoActualCar);
        assertEquals(guraCar, guraActualCar);
    }

    @Test
    public void should_throw_exception_message_unrecognized_parking_ticket_when_smart_parking_boy_fetches_the_car_given_a_parking_boy_with_two_parking_lots_and_an_unrecognized_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket unrecognizedTicket = new ParkingTicket();

        //when & then
        Exception exception = assertThrows(Exception.class, () -> smartParkingBoy.fetch(unrecognizedTicket));
        assertTrue(exception.getMessage().contains("Unrecognized parking ticket"));
    }

    @Test
    public void should_throw_exception_message_unrecognized_parking_ticket_when_smart_parking_boy_fetches_the_car_given_a_smart_parking_boy_with_two_parking_lots_and_a_used_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket usedTicket = smartParkingBoy.park(new Car());
        smartParkingBoy.fetch(usedTicket);

        //when & then
        Exception exception = assertThrows(Exception.class, () -> smartParkingBoy.fetch(usedTicket));
        assertTrue(exception.getMessage().contains("Unrecognized parking ticket"));
    }

    @Test
    public void should_throw_exception_message_no_available_position_when_smart_parking_boy_parks_the_car_given_a_smart_parking_boy_with_two_full_parking_lots_and_a_parking_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());

        //when & then
        Exception exception = assertThrows(Exception.class, () -> smartParkingBoy.park(new Car()));
        assertTrue(exception.getMessage().contains("No available position"));
    }

}
