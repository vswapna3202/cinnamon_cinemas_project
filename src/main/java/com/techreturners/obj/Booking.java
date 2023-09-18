package com.techreturners.obj;

import com.techreturners.dao.BookingDAO;
import com.techreturners.dao.BookingFileDAO;
import com.techreturners.exception.CustomCinnamonCinemaException;

import java.io.*;
import java.util.ArrayList;

public class Booking {
    public static final int TOTAL_SEATS_AVAILABLE = 15;

    public boolean canAllocateSeats(ArrayList<SeatNumber> seatNumbers,
                                    int noOfSeats){
        return TOTAL_SEATS_AVAILABLE - seatNumbers.size() >= noOfSeats;
    }

    public boolean allocateAndSaveSeats(ArrayList<SeatNumber> newSeatNumbers)
            throws CustomCinnamonCinemaException {
        BookingDAO bookingDAO = new BookingFileDAO();
        return bookingDAO.persistDAO(newSeatNumbers);
    }

    public ArrayList<SeatNumber> checkAvailableSeats()
            throws IOException,
                   NumberFormatException{
        BookingDAO bookingDAO = new BookingFileDAO();
        return bookingDAO.fetchDAO();
    }
}
