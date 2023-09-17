package com.techreturners.obj;

import com.techreturners.app.BookingApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BookingAppTest {
    BookingApp bookingApp;
    Booking booking;
    @BeforeEach
    public void setUp(){
        bookingApp = new BookingApp();
        booking = new Booking();
    }

    @Test
    public void testCheckAvailableSeatsValid() throws IOException,
            NumberFormatException{
        ArrayList<SeatNumber> availableSeatsActual =
                bookingApp.checkAvailableSeats(CinnamonCinemaTestData.noOfSeats,
                        booking);

        assertEquals(CinnamonCinemaTestData.availableListExpected.get(0).getRow(),
                availableSeatsActual.get(0).getRow());
        assertEquals(CinnamonCinemaTestData.availableListExpected.get(0).getSeat(),
                availableSeatsActual.get(0).getSeat());
        assertEquals(CinnamonCinemaTestData.availableListExpected.get(1).getRow(),
                availableSeatsActual.get(1).getRow());
        assertEquals(CinnamonCinemaTestData.availableListExpected.get(1).getSeat(),
                availableSeatsActual.get(1).getSeat());
    }

    @Test
    @Disabled
    public void testCheckAvailableSeatsEmpty() throws IOException,
            NumberFormatException{
        ArrayList<SeatNumber> availableSeatsActual =
                bookingApp.checkAvailableSeats(CinnamonCinemaTestData.noOfSeats,
                        booking);

        assertEquals(0,
                availableSeatsActual.size());
    }

    @Test
    public void testCanAllocatSeatsTrue() {
        //Test when only two seats are allocated and customer wants to book 3 seats
        assertTrue(bookingApp.canAllocateSeats(CinnamonCinemaTestData.availableListExpected,
                CinnamonCinemaTestData.noOfSeats,
                booking));
    }

    @Test
    public void testCanAllocatSeatsFalseAllSeatsFull() {
        ArrayList<SeatNumber> availableListFull = new ArrayList<>();
        //Test when all seats are allocated
        boolean isSeatAvailable = bookingApp.canAllocateSeats(
                CinnamonCinemaTestData.initialiseAllOccupiedSeats(CinnamonCinemaTestData.TOTAL_SEATS),
                CinnamonCinemaTestData.noOfSeats,
                booking);
        assertFalse(isSeatAvailable);
    }

    @Test
    public void testCanAllocatSeatsFalseWhenNoOfSeatsMoreThanAvailableSeats() {
        //Test when two seats are available to book but user has requested 3 seats
        ArrayList<SeatNumber> availableListFull = new ArrayList<>();
        //Test when all seats are allocated
        boolean isSeatAvailable = bookingApp.canAllocateSeats(
                CinnamonCinemaTestData.initialiseAllOccupiedSeats(CinnamonCinemaTestData.TOTAL_FILLED_SEATS),
                CinnamonCinemaTestData.noOfSeats,
                booking);
        assertFalse(isSeatAvailable);
    }

    @Test
    public void testGenerateSeatNumbersWithTwoSeatsAllocatedAlready(){
        ArrayList<String> allocatedSeatsActual =
                bookingApp.generateSeatNumbers(CinnamonCinemaTestData.availableListExpected,
                        CinnamonCinemaTestData.noOfSeats);
        assertEquals(CinnamonCinemaTestData.allocatedSeatsExpected.get(0),
                allocatedSeatsActual.get(0));
        assertEquals(CinnamonCinemaTestData.allocatedSeatsExpected.get(1),
                allocatedSeatsActual.get(1));
        assertEquals(CinnamonCinemaTestData.allocatedSeatsExpected.get(2),
                allocatedSeatsActual.get(2));
    }

    @Test
    public void testGenerateSeatNumbersWithThreeRemainingSeats(){
        ArrayList<SeatNumber> availableListExpected =
                CinnamonCinemaTestData.initialiseAllOccupiedSeats(12);
        ArrayList<String> allocatedSeatsActual =
                bookingApp.generateSeatNumbers(availableListExpected,
                        CinnamonCinemaTestData.noOfSeats);
        assertEquals(CinnamonCinemaTestData.allocatedSeatsExpectedEnd,
                allocatedSeatsActual);
    }

    @Test
    public void testGenerateSeatNumbersWithChangeBetweenRows(){
        ArrayList<SeatNumber> availableListExpected =
                CinnamonCinemaTestData.initialiseAllOccupiedSeats(3);
        ArrayList<String> allocatedSeatsActual =
                bookingApp.generateSeatNumbers(availableListExpected,
                        CinnamonCinemaTestData.noOfSeats);
        assertEquals(CinnamonCinemaTestData.allocatedSeatsWithRowSwap,
                allocatedSeatsActual);
    }

}
