package com.parkinglot;

import org.junit.jupiter.api.Test;

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
    public void should_return_a_parking_ticket_when_parking_boy_parks_given_a_parking_lot_and_a_car() {
        //given
        ParkingBoy parkingboy = new ParkingBoy(new ParkingLot());
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingboy.park(car);

        //then
        assertNotNull(parkingTicket);
    }

}
