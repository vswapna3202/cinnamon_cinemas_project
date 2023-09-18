package com.techreturners.obj;

import com.techreturners.exception.CustomCinnamonCinemaException;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingTest {
    Booking booking;

    @BeforeEach
    public void setUp(){
        booking = new Booking();
    }

    @Test
    @Order(1)
    public void testCanAllocateSeatsValid(){
        assertTrue(booking.canAllocateSeats(CinnamonCinemaTestData.availableListExpected,
                CinnamonCinemaTestData.noOfSeats));
    }
    @Test
    @Order(2)
    public void testCannotAllocateSeats(){
        assertFalse(booking.canAllocateSeats(
                CinnamonCinemaTestData.initialiseAllOccupiedSeats(
                        CinnamonCinemaTestData.TOTAL_SEATS),
                CinnamonCinemaTestData.noOfSeats));

    }

    @Test
    @Order(3)
    public void testAllocateAndSaveSeats()
            throws CustomCinnamonCinemaException,IOException {
        CinnamonCinemaTestData.testEmptySeatMappingFile();
        assertTrue(booking.allocateAndSaveSeats(
                CinnamonCinemaTestData.availableListExpected));
    }

    @Test
    @Order(4)
    public void testCheckAvailableSeats() throws IOException {
        assertEquals(CinnamonCinemaTestData.availableListExpected.size(),
                booking.checkAvailableSeats().size());
    }

    @Test
    @Order(5)
    public void testAllocateAndSaveSeatsException()
            throws CustomCinnamonCinemaException, IOException{
        CinnamonCinemaTestData.testEmptySeatMappingFile();
        booking.allocateAndSaveSeats(CinnamonCinemaTestData.initialiseAllOccupiedSeats(15));
        assertThrows(CustomCinnamonCinemaException.class, () ->
                booking.allocateAndSaveSeats(
                        CinnamonCinemaTestData.availableListExpected));
    }

    @Test
    @Order(6)
    public void testCheckAvailableSeatsWhenAllBooked() throws IOException{
        assertEquals(CinnamonCinemaTestData.TOTAL_SEATS,
                booking.checkAvailableSeats().size());
    }

}
