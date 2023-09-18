package com.techreturners.app;

import com.techreturners.obj.Booking;
import com.techreturners.obj.SeatNumber;

import java.io.IOException;
import java.util.ArrayList;

public class TerminateBooking extends Thread {
    private final BookingApp bookingApp;
    public TerminateBooking(BookingApp bookingApp){
        this.bookingApp = bookingApp;
    }

    @Override
    public void run(){

        while(true){
            try {
                Booking booking = new Booking();
                ArrayList<SeatNumber> seatNumbers = bookingApp.checkAvailableSeats(booking);
                if (seatNumbers.size() >= 15){
                    bookingApp.terminateBooking();
                    break;
                }
            }catch(IOException ioe){
                System.out.println("Error in reading file seatMapping.txt"
                        +ioe.getMessage());
            }
            try{
                Thread.sleep(10000);
            }catch(InterruptedException ie){
                System.err.println("TerminateBooking thread was interrupted"+
                        ie.getMessage());
            }
        }
    }
}
