package com.techreturners.obj;

import java.util.ArrayList;
import java.util.Arrays;

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

    public static ArrayList<SeatNumber> availableListExpected =
            new ArrayList<>(Arrays.asList(seatNumberOne, seatNumberTwo));
    public static final int TOTAL_SEATS = 15;
    public static final int TOTAL_FILLED_SEATS = 13;

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
