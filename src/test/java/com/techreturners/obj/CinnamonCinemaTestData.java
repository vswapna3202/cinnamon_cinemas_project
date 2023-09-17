package com.techreturners.obj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CinnamonCinemaTestData {
    public static String customerName = "Harry Potter";
    public static String customerAddress = "4 Privet Drive, Surrey, UK";
    public static String customerContactNo = "+44-7654321234";
    public static String customerEmail = "harrypotter@hogwarts.com";
    public static Customer customer = new Customer(customerName,
                                    customerAddress,
                                    customerContactNo,
                                    customerEmail);
    public static int noOfSeats = 3;
    public static String invalidNoOfSeats = "a";
    public static int invalidNoOfSeatsOutOfRange = 6;
    public static String outputString = "Seats have been allocated to " +
            customerName+ "[ " +customerAddress + ", "+ customerContactNo+" ], "+
            customerEmail;
    public static SeatNumber seatNumberOne = new SeatNumber(Row.A,Seat.ONE);
    public static SeatNumber seatNumberTwo = new SeatNumber(Row.A,Seat.TWO);
    public static SeatNumber seatNumberThree = new SeatNumber(Row.A,Seat.THREE);
    public static SeatNumber seatNumberFour = new SeatNumber(Row.A,Seat.FOUR);
    public static SeatNumber seatNumberFive = new SeatNumber(Row.A,Seat.FIVE);

    public static ArrayList<SeatNumber> newSeatsExpected =
            new ArrayList<>(Arrays.asList(seatNumberThree,seatNumberFour,
                    seatNumberFive));

    public static ArrayList<SeatNumber> availableListExpected =
            new ArrayList<>(Arrays.asList(seatNumberOne, seatNumberTwo));

    public static ArrayList<String> allocatedSeatsExpected =
            new ArrayList<>(Arrays.asList("A3","A4","A5"));
    public static ArrayList<String> allocatedSeatsExpectedEnd =
            new ArrayList<>(Arrays.asList("C3","C4","C5"));
    public static ArrayList<String> allocatedSeatsWithRowSwap =
            new ArrayList<>(Arrays.asList("A4","A5","B1"));
    public static final int TOTAL_SEATS = 15;
    public static final int TOTAL_FILLED_SEATS = 13;
    public static final HashMap allSeatMap = new HashMap();

    public static HashMap<Integer, String> initialiseAllSeatMap(){
        allSeatMap.put(1,"A1");
        allSeatMap.put(2,"A2");
        allSeatMap.put(3,"A3");
        allSeatMap.put(4,"A4");
        allSeatMap.put(5,"A5");
        allSeatMap.put(6,"B1");
        allSeatMap.put(7,"B2");
        allSeatMap.put(8,"B3");
        allSeatMap.put(9,"B4");
        allSeatMap.put(10,"B5");
        allSeatMap.put(11,"C1");
        allSeatMap.put(12,"C2");
        allSeatMap.put(13,"C3");
        allSeatMap.put(14,"C4");
        allSeatMap.put(15,"C5");
        return allSeatMap;
    }

    public static ArrayList<SeatNumber> initialiseAllOccupiedSeats(int n){
        ArrayList<SeatNumber> availableSeatsFull = new ArrayList<>();
        int currentSeat = 0;
        for(int i = 1; i <= n; i++){
            if (i >= 1 && i <= 5){
                currentSeat = i;
                availableSeatsFull.add(new SeatNumber(Row.A, Seat.fromInt(currentSeat)));
            }else if (i >= 6 && i <= 10){
                currentSeat = i%5;
                if (currentSeat == 0) currentSeat = 5;
                availableSeatsFull.add(new SeatNumber(Row.B, Seat.fromInt(currentSeat)));
            }else {
                currentSeat = i%5;
                if (currentSeat == 0) currentSeat = 5;
                availableSeatsFull.add(new SeatNumber(Row.C, Seat.fromInt(currentSeat)));
            }
        }
        return availableSeatsFull;
    }
}
