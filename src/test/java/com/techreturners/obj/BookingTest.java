package com.techreturners.obj;

import com.techreturners.exception.CustomCinnamonCinemaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {
    Booking booking;

    @BeforeEach
    public void setUp(){
        booking = new Booking();
    }

    @Test
    public void testCanAllocateSeatsValid(){

        assertTrue(booking.canAllocateSeats(CinnamonCinemaTestData.availableListExpected,
                CinnamonCinemaTestData.noOfSeats));

    }
    @Test
    public void testCannotAllocateSeats(){
        assertFalse(booking.canAllocateSeats(
                CinnamonCinemaTestData.initialiseAllOccupiedSeats(
                        CinnamonCinemaTestData.TOTAL_SEATS),
                CinnamonCinemaTestData.noOfSeats));

    }

    @Test
    public void testAllocateAndSaveSeats() throws CustomCinnamonCinemaException {
        assertTrue(booking.allocateAndSaveSeats(
                CinnamonCinemaTestData.availableListExpected));
    }

    @Test
    public void testCheckAvailableSeats() throws IOException {
        assertEquals(CinnamonCinemaTestData.availableListExpected.size(),
                booking.checkAvailableSeats().size());
    }

    @Test
    @Disabled
    //seatMapping.txt should be empty to test this condition
    public void testAllocateAndSaveSeatsException() throws CustomCinnamonCinemaException{
        booking.allocateAndSaveSeats(CinnamonCinemaTestData.initialiseAllOccupiedSeats(15));
        assertThrows(CustomCinnamonCinemaException.class, () ->
                booking.allocateAndSaveSeats(
                        CinnamonCinemaTestData.availableListExpected));
    }

    @Test
    @Disabled
    //Test this after testAllocateAndSaveSeatsException is tested
    //or if seatMapping.txt has 15 rows
    public void testCheckAvailableSeatsWhenAllBooked() throws IOException{
        assertEquals(CinnamonCinemaTestData.TOTAL_SEATS,
                booking.checkAvailableSeats().size());
    }

}
