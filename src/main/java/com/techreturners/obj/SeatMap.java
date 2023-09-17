package com.techreturners.obj;

import java.util.HashMap;

public class SeatMap {
    HashMap<Integer, String> seatMap = new HashMap<>();

    public HashMap initialiseSeatMap() {
        for (int i = 1; i <= 15; i++) {
            if (i >= 1 && i <= 5) {
                seatMap.put(i, String.valueOf(Row.A) + String.valueOf(i));
            }else if (i >= 6 && i <=10) {
                seatMap.put(i, String.valueOf(Row.B) + String.valueOf(i-5));
            }else{
                seatMap.put(i, String.valueOf(Row.C) + String.valueOf(i-10));
            }
        }
        return seatMap;
    }
}
