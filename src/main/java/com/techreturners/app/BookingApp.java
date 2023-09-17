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
        boolean isSaveSuccess = false;
        try {
            Booking booking = new Booking();
            ArrayList<SeatNumber> seatNumbers = checkAvailableSeats(noOfSeats, booking);
            if (canAllocateSeats(seatNumbers, noOfSeats, booking)) {
                ArrayList<SeatNumber> newSeatNumbers = generateSeatNumbers(seatNumbers, noOfSeats);
                isSaveSuccess = allocateAndSaveSeats(newSeatNumbers, noOfSeats, booking);
            }else{
                throw new CustomCinnamonCinemaException("Seats are not available, " +
                        "cannot proceed with booking");
            }
        }catch(FileNotFoundException fe){
            System.out.println("File seatMapping.txt is not found in folder config");
        }catch(IOException ioe){
            System.out.println("Could not read data from seatMapping.txt file, IOException occurred");
        }catch(CustomCinnamonCinemaException ccce){
            System.out.println(ccce.getMessage());
        }
    }

    public ArrayList<SeatNumber> checkAvailableSeats(int noOfSeats,
                                                     Booking booking)
                throws IOException, NumberFormatException{
        return booking.checkAvailableSeats(noOfSeats);
    }

    public boolean canAllocateSeats(ArrayList<SeatNumber> seatNumbers,
                                    int noOfSeats,
                                    Booking booking){
        return booking.canAllocateSeats(seatNumbers, noOfSeats);
    }

    public ArrayList<SeatNumber> generateSeatNumbers(ArrayList<SeatNumber> seatNumbers,
                                                     int noOfSeats){
        int seatAllocatedSize = seatNumbers.size();
        int index = noOfSeats;
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
