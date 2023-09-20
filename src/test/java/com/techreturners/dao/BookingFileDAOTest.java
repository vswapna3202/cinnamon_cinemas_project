package com.techreturners.dao;

import com.techreturners.exception.CustomCinnamonCinemaException;
import com.techreturners.obj.CinnamonCinemaTestData;
import com.techreturners.obj.SeatNumber;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingFileDAOTest {
    BookingDAO bookingDAO;
    @BeforeEach
    public void setUp(){
        bookingDAO = new BookingFileDAO();
    }

    @Test
    @Order(1)
    public void testPersistDAO()
            throws CustomCinnamonCinemaException {
        CinnamonCinemaTestData.testEmptySeatMappingFile();
        assertTrue(bookingDAO.persistDAO(CinnamonCinemaTestData.availableListExpected));
    }

    @Test
    @Order(2)
    public void testFetchDAOValid() throws IOException {
        ArrayList<SeatNumber> fetchedResult = bookingDAO.fetchDAO();
        assertEquals(CinnamonCinemaTestData.availableListExpected,
                fetchedResult);
    }

    @Test
    @Order(3)
    public void testFetchEmptyDAO() throws IOException {
        CinnamonCinemaTestData.testEmptySeatMappingFile();
        ArrayList<SeatNumber> fetchedResult = bookingDAO.fetchDAO();
        assertEquals(0, fetchedResult.size());
    }

    @Test
    @Order(4)
    public void testFetchInvalidDAO() {
        CinnamonCinemaTestData.testInvalidSeatMappingFile();
        assertThrows(NumberFormatException.class, () -> bookingDAO.fetchDAO());
    }
}
