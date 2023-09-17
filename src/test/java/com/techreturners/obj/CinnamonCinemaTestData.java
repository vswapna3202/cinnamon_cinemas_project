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
}
