package com.techreturners.app;

import com.techreturners.exception.CustomCinnamonCinemaException;
import com.techreturners.obj.Booking;
import com.techreturners.obj.CinnamonCinemaTestData;
import com.techreturners.obj.SeatNumber;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingAppTest {
    BookingApp bookingApp;
    Booking booking;

    @BeforeEach
    public void setUp(){
        bookingApp = new BookingApp();
        booking = new Booking();
    }

    @Test
    @Order(1)
    public void testCheckAvailableSeatsValid() throws IOException,
            NumberFormatException, CustomCinnamonCinemaException{
        CinnamonCinemaTestData.testEmptySeatMappingFile();
        bookingApp.allocateAndSaveSeats(
                    CinnamonCinemaTestData.availableListExpected,
                    booking);
        ArrayList<SeatNumber>  availableSeatsActual =
                    bookingApp.checkAvailableSeats(booking);

        Assertions.assertEquals(CinnamonCinemaTestData.availableListExpected.get(0).row(),
                availableSeatsActual.get(0).row());
        assertEquals(CinnamonCinemaTestData.availableListExpected.get(0).seat(),
                availableSeatsActual.get(0).seat());
        assertEquals(CinnamonCinemaTestData.availableListExpected.get(1).row(),
                availableSeatsActual.get(1).row());
        assertEquals(CinnamonCinemaTestData.availableListExpected.get(1).seat(),
                availableSeatsActual.get(1).seat());
    }

    @Test
    @Order(2)
    public void testCheckAvailableSeatsEmpty() throws IOException,
            NumberFormatException{
        CinnamonCinemaTestData.testEmptySeatMappingFile();
        ArrayList<SeatNumber> availableSeatsActual =
                bookingApp.checkAvailableSeats(booking);

        assertEquals(0,
                availableSeatsActual.size());
    }

    @Test
    @Order(3)
    public void testCanAllocateSeatsTrue() {
        //Test when only two seats are allocated and customer wants to book 3 seats
        assertTrue(bookingApp.canAllocateSeats(CinnamonCinemaTestData.availableListExpected,
                CinnamonCinemaTestData.noOfSeats,
                booking));
    }

    @Test
    @Order(4)
    public void testCanAllocateSeatsFalseAllSeatsFull() {
        //Test when all seats are allocated
        boolean isSeatAvailable = bookingApp.canAllocateSeats(
                CinnamonCinemaTestData.initialiseAllOccupiedSeats(CinnamonCinemaTestData.TOTAL_SEATS),
                CinnamonCinemaTestData.noOfSeats,
                booking);
        assertFalse(isSeatAvailable);
    }

    @Test
    @Order(5)
    public void testCanAllocateSeatsFalseWhenNoOfSeatsMoreThanAvailableSeats() {
        //Test when all seats are allocated
        boolean isSeatAvailable = bookingApp.canAllocateSeats(
                CinnamonCinemaTestData.initialiseAllOccupiedSeats(CinnamonCinemaTestData.TOTAL_FILLED_SEATS),
                CinnamonCinemaTestData.noOfSeats,
                booking);
        assertFalse(isSeatAvailable);
    }

    @Test
    @Order(6)
    public void testGenerateSeatNumbersWithTwoSeatsAllocatedAlready(){
        ArrayList<SeatNumber> allocatedSeatsActual =
                bookingApp.generateSeatNumbers(CinnamonCinemaTestData.availableListExpected,
                        CinnamonCinemaTestData.noOfSeats);
        for(int i = 0;i < CinnamonCinemaTestData.newSeatsExpected.size(); i++) {
            assertEquals(CinnamonCinemaTestData.newSeatsExpected.get(i).row(),
                    allocatedSeatsActual.get(i).row());
            assertEquals(CinnamonCinemaTestData.newSeatsExpected.get(i).seat(),
                    allocatedSeatsActual.get(i).seat());
        }
    }

    @Test
    @Order(7)
    public void testGenerateSeatNumbersWithThreeRemainingSeats(){
        ArrayList<SeatNumber> availableListExpected =
                CinnamonCinemaTestData.initialiseAllOccupiedSeats(12);
        ArrayList<SeatNumber> allocatedSeatsActual =
                bookingApp.generateSeatNumbers(availableListExpected,
                        CinnamonCinemaTestData.noOfSeats);
        for(int i = 0; i < CinnamonCinemaTestData.allocatedSeatsExpectedEnd.size();i++) {
            assertEquals(CinnamonCinemaTestData.allocatedSeatsExpectedEnd.get(i).row(),
                    allocatedSeatsActual.get(i).row());
            assertEquals(CinnamonCinemaTestData.allocatedSeatsExpectedEnd.get(i).seat(),
                    allocatedSeatsActual.get(i).seat());
        }
    }

    @Test
    @Order(8)
    public void testGenerateSeatNumbersWithChangeBetweenRows(){
        ArrayList<SeatNumber> availableListExpected =
                CinnamonCinemaTestData.initialiseAllOccupiedSeats(3);
        ArrayList<SeatNumber> allocatedSeatsActual =
                bookingApp.generateSeatNumbers(availableListExpected,
                        CinnamonCinemaTestData.noOfSeats);
        for (int i = 0; i < CinnamonCinemaTestData.allocatedSeatsWithRowSwap.size();i++) {
            assertEquals(CinnamonCinemaTestData.allocatedSeatsWithRowSwap.get(i).row(),
                    allocatedSeatsActual.get(i).row());
            assertEquals(CinnamonCinemaTestData.allocatedSeatsWithRowSwap.get(i).seat(),
                    allocatedSeatsActual.get(i).seat());
        }
    }

    @Test
    @Order(9)
    public void testAllocateAndSaveDataValid()
            throws CustomCinnamonCinemaException {
        assertTrue(bookingApp.allocateAndSaveSeats(CinnamonCinemaTestData.newSeatsExpected,
                                    booking));
    }

}
