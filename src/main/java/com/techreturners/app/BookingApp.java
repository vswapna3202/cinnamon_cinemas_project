package com.techreturners.app;

import com.techreturners.exception.CustomCinnamonCinemaException;
import com.techreturners.obj.*;

import java.io.*;
import java.util.ArrayList;

public class BookingApp {

    public void startBooking(){
        // Booking app starts booking and keeps it open until terminate booking
        // thread stops the app
        while(true) {
            // Get customer input of Customer object and no of seats
            Object[] input = Customer.getAndValidateCustomerInput();
            Customer customer = (Customer) input[0];
            int noOfSeats = (int) input[1];

            try {
                // Create booking object and check if seats are available
                Booking booking = new Booking();
                ArrayList<SeatNumber> seatNumbers = checkAvailableSeats(booking);
                // if seats can be allocated generate new seat numbers for
                // noOfSeats requested
                if (canAllocateSeats(seatNumbers, noOfSeats, booking)) {
                    ArrayList<SeatNumber> newSeatNumbers = generateSeatNumbers(seatNumbers, noOfSeats);
                    // allocate and save seats to seatMapping.txt file
                    boolean isSaveSuccess = allocateAndSaveSeats(newSeatNumbers, booking);
                    // if save successful generate ticket details and display to customer
                    if (isSaveSuccess) {
                        int ticketId = seatNumbers.size() + 1000;
                        Ticket ticket = new Ticket(ticketId, newSeatNumbers);
                        ticket.displayTicket(customer, noOfSeats);
                    } else {
                        System.out.println("Your tickets were not booked! Try again later");
                    }
                } else {
                    // If generate seats are booked concurrently by another app show
                    // message and ask for customer to rebook
                    throw new CustomCinnamonCinemaException("Seats are not available, " +
                            "cannot proceed with booking");
                }
            } catch (FileNotFoundException fe) {
                System.out.println("File seatMapping.txt is not found in folder config");
            } catch (IOException ioe) {
                System.out.println("Could not read data from seatMapping.txt file, IOException occurred");
            } catch (CustomCinnamonCinemaException cex) {
                System.out.println(cex.getMessage());
            }
        }
    }

        public ArrayList<SeatNumber> checkAvailableSeats(Booking booking)
                throws IOException, NumberFormatException{
        return booking.checkAvailableSeats();
    }

    public boolean canAllocateSeats(ArrayList<SeatNumber> seatNumbers,
                                    int noOfSeats,
                                    Booking booking){
        return booking.canAllocateSeats(seatNumbers, noOfSeats);
    }

    public ArrayList<SeatNumber> generateSeatNumbers(ArrayList<SeatNumber> seatNumbers,
                                                     int noOfSeats){
        int seatAllocatedSize = seatNumbers.size();
        ArrayList<SeatNumber> newSeatNumbers = new ArrayList<>();
        SeatMap seatMap = new SeatMap();
        // get the full map of row number and seat numbers
        seatMap.initialiseSeatMap();
        for (int i=0 ; i < noOfSeats; i++) {
            // Add 1 to existing seatNumbers and get the row and SeatNumber from
            // seatMap
            String rowSeatNumber = seatMap.getSeatMap().get(seatAllocatedSize + 1);
            String rowStr = rowSeatNumber.substring(0,1);
            String seatStr = rowSeatNumber.substring(1);
            SeatNumber newRowSeatNumber = new SeatNumber(Row.valueOf(rowStr),
                    Seat.fromInt(Integer.parseInt(seatStr)));
            // Add new seat numbers to new seat numbers and do this for requested
            // number of seats
            newSeatNumbers.add(newRowSeatNumber);
            seatAllocatedSize++;
        }
        return newSeatNumbers;
    }

     public boolean allocateAndSaveSeats(ArrayList<SeatNumber> seatNumbers,
                                        Booking booking)
             throws CustomCinnamonCinemaException{
        return booking.saveSeats(seatNumbers);
    }

    public void terminateBooking(){
        // This method is called by TerminateBooking thread which checks
        // if all seats have been booked. If so the app is terminated with a message
        System.out.println("All seats have been booked! Terminating booking app");
        System.exit(0);
    }

    public static void main(String[] args){
        BookingApp bookingApp = new BookingApp();
        // TerminateBooking booking thread starts and bookingApp is passed to it
        TerminateBooking terminateBooking = new TerminateBooking(bookingApp);
        terminateBooking.start();
        // bookingApp starts the booking
        bookingApp.startBooking();
    }
}
