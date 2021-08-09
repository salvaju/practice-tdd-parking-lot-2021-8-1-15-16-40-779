package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StandardParkingBoyTest {

    @Test
    public void should_return_a_parking_ticket_when_standard_parking_boy_parks_given_standard_parking_boy_and_a_parking_lot_and_a_car() {
        //given
        StandardParkingBoy parkingboy = new StandardParkingBoy(new ParkingLot());
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingboy.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_the_correct_car_when_standard_parking_boy_fetches_car_given_standard_parking_boy_and_a_parking_lot_and_a_parking_ticket() {
        //given
        StandardParkingBoy parkingboy = new StandardParkingBoy(new ParkingLot());
        Car car = new Car();
        ParkingTicket parkingTicket = parkingboy.park(car);

        //when
        Car actualCar = parkingboy.fetch(parkingTicket);

        //then
        assertEquals(car, actualCar);
    }

    @Test
    public void should_return_the_correct_cars_when_standard_parking_boy_fetches_the_car_twice_given_standard_parking_boy_and_a_parking_lot_and_two_parking_tickets() {
        //given
        StandardParkingBoy parkingboy = new StandardParkingBoy(new ParkingLot());
        Car cocoCar = new Car();
        Car guraCar = new Car();
        ParkingTicket cocoParkingTicket = parkingboy.park(cocoCar);
        ParkingTicket guraParkingTicket = parkingboy.park(guraCar);

        //when
        Car actualCocoCar = parkingboy.fetch(cocoParkingTicket);
        Car actualGuraCar = parkingboy.fetch(guraParkingTicket);

        //then
        assertEquals(cocoCar, actualCocoCar);
        assertEquals(guraCar, actualGuraCar);
    }

    @Test
    public void should_throw_error_message_Unrecognized_parking_ticket_when_standard_parking_boy_fetches_the_car_given_standard_parking_boy_and_a_wrong_parking_ticket_and_a_parking_lot() {
        //given
        StandardParkingBoy parkingboy = new StandardParkingBoy(new ParkingLot());
        ParkingTicket wrongParkingTicket = new ParkingTicket();

        //when & then
        Exception exception = assertThrows(Exception.class, () -> parkingboy.fetch(wrongParkingTicket));
        assertTrue(exception.getMessage().contains("Unrecognized parking ticket"));
    }

    @Test
    public void should_throw_error_message_Unrecognized_parking_ticket_when_standard_parking_boy_fetches_the_car_given_standard_parking_boy_and_a_parking_lot_and_used_parking_ticket() {
        //given
        StandardParkingBoy parkingboy = new StandardParkingBoy(new ParkingLot());
        Car bossKanenCar = new Car();

        ParkingTicket bossKanenParkingTicket = parkingboy.park(bossKanenCar);
        parkingboy.fetch(bossKanenParkingTicket);


        //when & then
        Exception exception = assertThrows(Exception.class, () -> parkingboy.fetch(bossKanenParkingTicket));
        assertTrue(exception.getMessage().contains("Unrecognized parking ticket"));
    }

    @Test
    public void should_throw_error_message_No_available_position_when_standard_parking_boy_parks_the_car_given_standard_parking_boy_and_a_full_parking_lot_and_a_car() {

        //given
        StandardParkingBoy parkingboy = new StandardParkingBoy(new ParkingLot(1));
        parkingboy.park(new Car());

        //when & then
        Exception exception = assertThrows(Exception.class, () -> parkingboy.park(new Car()));
        assertTrue(exception.getMessage().contains("No available position"));
    }

    @Test
    public void should_car_park_to_the_first_parking_lot_when_standard_parking_boy_parks_the_car_given_a_standard_parking_boy_with_two_parking_lots_with_available_position_and_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());

        StandardParkingBoy parkingboy = new StandardParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingboy.park(car);

        //then
        assertTrue(parkingboy.isParkedOnParkingLotNumber(parkingTicket, 1));
        assertFalse(parkingboy.isParkedOnParkingLotNumber(parkingTicket, 2));
    }

    @Test
    public void should_car_park_to_the_second_parking_lot_when_standard_parking_boy_parks_the_car_given_a_standard_parking_boy_with_two_parking_lots_with_but_the_first_is_full_and_the_second_is_available_and_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot());

        StandardParkingBoy parkingboy = new StandardParkingBoy(parkingLots);
        parkingboy.park(new Car());

        Car secondCar = new Car();

        //when
        ParkingTicket secondParkingTicket = parkingboy.park(secondCar);

        //then
        assertFalse(parkingboy.isParkedOnParkingLotNumber(secondParkingTicket, 1));
        assertTrue(parkingboy.isParkedOnParkingLotNumber(secondParkingTicket, 2));
    }

    @Test
    public void should_return_the_right_car_with_correct_ticket_when_standard_parking_boy_fetches_the_car_twice_given_a_standard_parking_boy_with_two_parking_lots_with_two_parked_cars_on_each_parking_lot_and_two_parking_tickets() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot());

        StandardParkingBoy parkingboy = new StandardParkingBoy(parkingLots);

        Car cocoCar = new Car();
        Car guraCar = new Car();

        ParkingTicket cocoParkingTicket = parkingboy.park(cocoCar);
        ParkingTicket guraParkingTicket = parkingboy.park(guraCar);


        //when
        Car cocoActualCar = parkingboy.fetch(cocoParkingTicket);
        Car guraActualCar = parkingboy.fetch(guraParkingTicket);

        //then
        assertEquals(cocoCar, cocoActualCar);
        assertEquals(guraCar, guraActualCar);
    }

    @Test
    public void should_throw_exception_message_unrecognized_parking_ticket_when_standard_parking_boy_fetches_the_car_given_a_standard_parking_boy_with_two_parking_lots_and_an_unrecognized_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());

        StandardParkingBoy parkingboy = new StandardParkingBoy(parkingLots);
        ParkingTicket unrecognizedTicket = new ParkingTicket();

        //when & then
        Exception exception = assertThrows(Exception.class, () -> parkingboy.fetch(unrecognizedTicket));
        assertTrue(exception.getMessage().contains("Unrecognized parking ticket"));
    }

    @Test
    public void should_throw_exception_message_unrecognized_parking_ticket_when_standard_parking_boy_fetches_the_car_given_a_standard_parking_boy_with_two_parking_lots_and_a_used_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());

        StandardParkingBoy parkingboy = new StandardParkingBoy(parkingLots);
        ParkingTicket usedTicket = parkingboy.park(new Car());
        parkingboy.fetch(usedTicket);

        //when & then
        Exception exception = assertThrows(Exception.class, () -> parkingboy.fetch(usedTicket));
        assertTrue(exception.getMessage().contains("Unrecognized parking ticket"));
    }

    @Test
    public void should_throw_exception_message_no_available_position_when_standard_parking_boy_parks_the_car_given_a_standard_parking_boy_with_two_full_parking_lots_and_a_parking_ticket() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));

        StandardParkingBoy parkingboy = new StandardParkingBoy(parkingLots);
        parkingboy.park(new Car());
        parkingboy.park(new Car());

        //when & then
        Exception exception = assertThrows(Exception.class, () -> parkingboy.park(new Car()));
        assertTrue(exception.getMessage().contains("No available position"));
    }

}
