package com.techreturners.app;

import com.techreturners.obj.Booking;
import com.techreturners.obj.SeatNumber;

import java.io.IOException;
import java.util.ArrayList;

public class TerminateBooking extends Thread {
    private final BookingApp bookingApp;
    private static final int TOTAL_SEATS = 15;
    public TerminateBooking(BookingApp bookingApp){
        this.bookingApp = bookingApp;
    }

    @Override
    public void run(){

        while(true){
            try {
                //Check availableSeats on booking object if all seats have been booked
                // If true invoke terminateBooking method on bookingApp which terminates
                // the app
                Booking booking = new Booking();
                ArrayList<SeatNumber> seatNumbers = bookingApp.checkAvailableSeats(booking);
                if (seatNumbers.size() >= TOTAL_SEATS){
                    bookingApp.terminateBooking();
                    break;
                }
            }catch(IOException ioe){
                System.out.println("Error in reading file seatMapping.txt"
                        +ioe.getMessage());
            }
            // Thread sleeps for 10 s and checks again if all seats have been filled
            // and app needs to terminate
            try{
                Thread.sleep(10000);
            }catch(InterruptedException ie){
                System.err.println("TerminateBooking thread was interrupted"+
                        ie.getMessage());
            }
        }
    }
}
