package com.techreturners.obj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeatMapTest {
    SeatMap seatMapClass;

    @BeforeEach
    public void setUp(){
        seatMapClass = new SeatMap();
    }
    @Test
    public void testInitialiseSeatMap(){
        HashMap seatMapActual = seatMapClass.initialiseSeatMap();
        HashMap allSeatMapExpected = CinnamonCinemaTestData.initialiseAllSeatMap();
        assertTrue(seatMapActual.equals(allSeatMapExpected));
    }
}
