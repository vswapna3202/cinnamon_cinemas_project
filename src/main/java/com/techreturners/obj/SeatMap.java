package com.techreturners.obj;

import java.util.HashMap;

public class SeatMap {
    HashMap<Integer, String> seatMap = new HashMap<>();
    private static final int SEAT_MAP_BEGIN = 1;
    private static final int SEAT_MAP_END = 15;
    private static final int SEAT_MAP_SEATS_PER_ROW = 5;

    public void initialiseSeatMap() {
        for (int i = SEAT_MAP_BEGIN; i <= SEAT_MAP_END; i++) {
            int seatNumber = (i - 1) % SEAT_MAP_SEATS_PER_ROW + 1;
            Row row = Row.values()[(i - 1) / SEAT_MAP_SEATS_PER_ROW];
            seatMap.put(i, row + String.valueOf(seatNumber));
        }
    }

    public HashMap<Integer, String> getSeatMap() {
        return seatMap;
    }
}
