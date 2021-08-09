package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    public void should_return_a_parking_ticket_when_park_given_a_parking_lot_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingLot.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_the_correct_car_when_fetch_given_a_parking_lot_and_a_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        //when
        Car actualCar = parkingLot.fetch(parkingTicket);


        //then
        assertEquals(car, actualCar);
    }

    @Test
    public void should_return_the_correct_cars_when_fetch_the_car_twice_given_a_parking_lot_and_two_parking_tickets() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car cocoCar = new Car();
        Car guraCar = new Car();
        ParkingTicket cocoParkingTicket = parkingLot.park(cocoCar);
        ParkingTicket guraParkingTicket = parkingLot.park(guraCar);

        //when
        Car actualCocoCar = parkingLot.fetch(cocoParkingTicket);
        Car actualGuraCar = parkingLot.fetch(guraParkingTicket);


        //then
        assertEquals(cocoCar, actualCocoCar);
        assertEquals(guraCar, actualGuraCar);
    }

    @Test
    public void should_throw_error_message_Unrecognized_parking_ticket_when_fetch_the_car_given_a_wrong_parking_ticket_and_a_parking_lot() {

        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket wrongParkingTicket = new ParkingTicket();


        //when & then
        Exception exception = assertThrows(Exception.class, () -> parkingLot.fetch(wrongParkingTicket));
        assertTrue(exception.getMessage().contains("Unrecognized parking ticket"));

    }

    @Test
    public void should_throw_error_message_Unrecognized_parking_ticket_when_fetch_the_car_given_a_parking_lot_and_used_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car bossKanenCar = new Car();
        ParkingTicket bossKanenTicket = parkingLot.park(bossKanenCar);
        parkingLot.fetch(bossKanenTicket);


        //when & then
        Exception exception = assertThrows(Exception.class, () -> parkingLot.fetch(bossKanenTicket));
        assertTrue(exception.getMessage().contains("Unrecognized parking ticket"));
    }

    @Test
    public void should_throw_error_message_No_available_position_when_park_the_car_given_a_parking_lot_and_a_car() {

        //given
        ParkingLot parkingLot = new ParkingLot();

        for (int i = 0; i < 10; i++) {
            parkingLot.park(new Car());
        }

        //when & then
        Exception exception = assertThrows(Exception.class, () -> parkingLot.park(new Car()));
        assertTrue(exception.getMessage().contains("No available position"));
    }

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
