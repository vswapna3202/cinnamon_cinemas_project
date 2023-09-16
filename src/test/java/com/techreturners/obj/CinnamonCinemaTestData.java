package com.techreturners.obj;

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
    public static String outputString = "Seats have been allocated to " +
            customerName+ "[ " +customerAddress + ", "+ customerContactNo+" ], "+
            customerEmail;
}
