package com.techreturners.obj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeatMapTest {
    SeatMap seatMapClass;

    @BeforeEach
    public void setUp(){
        seatMapClass = new SeatMap();
    }
    @Test
    public void testInitialiseSeatMap(){
        seatMapClass.initialiseSeatMap();
        HashMap<Integer, String> allSeatMapExpected = CinnamonCinemaTestData.initialiseAllSeatMap();
        assertEquals(seatMapClass.getSeatMap(), allSeatMapExpected);
    }
}
