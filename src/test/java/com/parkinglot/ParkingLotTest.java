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
    public void should_return_nothing_when_fetch_the_car_given_a_wrong_parking_ticket_and_a_parking_lot() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket ParkingTicket = parkingLot.park(car);

        //when
        Car fakeCar = parkingLot.fetch(new ParkingTicket());


        //then
        assertNull(fakeCar);
    }

    @Test
    public void should_return_nothing_when_park_the_car_given_a_parkinglot_and_used_parking_ticket_and_a_parking_lot() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket firstParkingTicket = parkingLot.park(car);
        Car firstCar = parkingLot.fetch(firstParkingTicket);

        //when
        Car secondCar = parkingLot.fetch(firstParkingTicket);


        //then
        assertNull(secondCar);
    }

    @Test
    public void should_return_nothing_when_fetch_the_car_given_a_full_parkingLot_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();

        Car car = new Car();
        for (int i = 0; i < 10; i++) {
            ParkingTicket firstParkingTicket = parkingLot.park(car);
        }

        Car cocoCar = new Car();

        //when
        ParkingTicket CocoParkingTicket = parkingLot.park(cocoCar);


        //then
        assertNull(CocoParkingTicket);

    }

    @Test
    public void should_return_nothing_and_error_message_Unrecognized_Parking_ticket_when_fetch_the_car_given_a_wrong_parking_ticket_and_a_parking_lot() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            //given
            ParkingLot parkingLot = new ParkingLot();
            Car car = new Car();
            ParkingTicket ParkingTicket = parkingLot.park(car);

            //when
            Car fakeCar = parkingLot.fetch(new ParkingTicket());

            //then
            assertNull(fakeCar);
        });

        assertTrue(exception.getMessage().contains("Unrecognized parking ticket"));

    }

}
