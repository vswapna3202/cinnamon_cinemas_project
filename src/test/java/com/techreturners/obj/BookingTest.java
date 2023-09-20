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

    /* Testing method when only two seats have been allocated and remaining
       13 seats are available to book.
    */
    @Test
    @Order(1)
    public void testCanAllocateSeatsValid(){
        assertTrue(booking.canAllocateSeats(
                CinnamonCinemaTestData.availableListExpected,
                CinnamonCinemaTestData.noOfSeats));
    }

    /* Testing method when all seats have been allocated and no seats are
       available for booking
     */
    @Test
    @Order(2)
    public void testCannotAllocateSeats(){
        assertFalse(booking.canAllocateSeats(
                CinnamonCinemaTestData.initialiseAllOccupiedSeats(
                        CinnamonCinemaTestData.TOTAL_SEATS),
                CinnamonCinemaTestData.noOfSeats));
    }

    /* Testing method and saving first two seats to seatMapping.txt file
       Clearing seatMapping.txt file as if record already exists it will not
       be saved.
     */
    @Test
    @Order(3)
    public void testAllocateAndSaveSeats()
            throws CustomCinnamonCinemaException {
        CinnamonCinemaTestData.testEmptySeatMappingFile();
        assertTrue(booking.saveSeats(
                CinnamonCinemaTestData.availableListExpected));
    }

    /* Testing method which checks if seats are available to book
     */
    @Test
    @Order(4)
    public void testCheckAvailableSeats() throws IOException {
        assertEquals(CinnamonCinemaTestData.availableListExpected.size(),
                booking.checkAvailableSeats().size());
    }

    /* Testing exception condition when saving seats. */
    @Test
    @Order(5)
    public void testAllocateAndSaveSeatsException()
            throws CustomCinnamonCinemaException{
        CinnamonCinemaTestData.testEmptySeatMappingFile();
        booking.saveSeats(CinnamonCinemaTestData.initialiseAllOccupiedSeats(15));
        assertThrows(CustomCinnamonCinemaException.class, () ->
                booking.saveSeats(
                        CinnamonCinemaTestData.availableListExpected));
    }

    /* Testing method when all seats are booked */
    @Test
    @Order(6)
    public void testCheckAvailableSeatsWhenAllBooked() throws IOException{
        assertEquals(CinnamonCinemaTestData.TOTAL_SEATS,
                booking.checkAvailableSeats().size());
    }
}
