package com.techreturners.obj;

import com.techreturners.app.BookingApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingAppTest {
    BookingApp bookingApp;
    @BeforeEach
    public void setUp(){
        bookingApp = new BookingApp();
    }

    @Test
    public void testCheckAvailableSeatsValid() throws IOException,
            NumberFormatException{
        ArrayList<SeatNumber> availableSeatsActual =
                bookingApp.checkAvailableSeats(CinnamonCinemaTestData.noOfSeats);

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
                bookingApp.checkAvailableSeats(CinnamonCinemaTestData.noOfSeats);

        assertEquals(0,
                availableSeatsActual.size());
    }

    public void testCanAllocatSeats() {

    }

}
