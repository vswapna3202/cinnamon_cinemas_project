package com.techreturners.app;

import com.techreturners.exception.CustomCinnamonCinemaException;
import com.techreturners.obj.*;

import java.io.*;
import java.util.ArrayList;

public class BookingApp {


    public void startBooking(){
        Object[] input = Customer.getAndValidateCustomerInput();
        Customer customer = (Customer) input[0];
        int noOfSeats = (int) input[1];

        try {
            Booking booking = new Booking();
            ArrayList<SeatNumber> seatNumbers = checkAvailableSeats(booking);
            if (canAllocateSeats(seatNumbers, noOfSeats, booking)) {
                ArrayList<SeatNumber> newSeatNumbers = generateSeatNumbers(seatNumbers, noOfSeats);
                boolean isSaveSuccess = allocateAndSaveSeats(newSeatNumbers, noOfSeats, booking);
                if (isSaveSuccess){
                    int ticketId = seatNumbers.size()+1000;
                    Ticket ticket = new Ticket(ticketId, newSeatNumbers);
                    ticket.displayTicket(customer, noOfSeats);
                }else{
                    System.out.println("Your tickets were not booked! Try again later");
                }
            }else{
                throw new CustomCinnamonCinemaException("Seats are not available, " +
                        "cannot proceed with booking");
            }
        }catch(FileNotFoundException fe){
            System.out.println("File seatMapping.txt is not found in folder config");
        }catch(IOException ioe){
            System.out.println("Could not read data from seatMapping.txt file, IOException occurred");
        }catch(CustomCinnamonCinemaException cex){
            System.out.println(cex.getMessage());
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
        seatMap.initialiseSeatMap();
        for (int i=0 ; i < noOfSeats; i++) {
            String rowSeatNumber = seatMap.getSeatMap().get(seatAllocatedSize + 1);
            String rowStr = rowSeatNumber.substring(0,1);
            String seatStr = rowSeatNumber.substring(1);
            SeatNumber newRowSeatNumber = new SeatNumber(Row.valueOf(rowStr),
                    Seat.fromInt(Integer.parseInt(seatStr)));
            newSeatNumbers.add(newRowSeatNumber);
            seatAllocatedSize++;
        }
        return newSeatNumbers;
    }

     public boolean allocateAndSaveSeats(ArrayList<SeatNumber> seatNumbers,
                                        int noOfSeats,
                                        Booking booking){
        return booking.allocateAndSaveSeats(seatNumbers, noOfSeats);
    }

    public static void main(String[] args){
        BookingApp bookingApp = new BookingApp();
        bookingApp.startBooking();
    }
}
