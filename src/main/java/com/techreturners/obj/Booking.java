package com.techreturners.obj;

import com.techreturners.app.BookingApp;
import com.techreturners.dao.BookingDAO;
import com.techreturners.dao.BookingFileDAO;
import com.techreturners.exception.CustomCinnamonCinemaException;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Booking {
    public static final int TOTAL_SEATS_AVAILABLE = 15;
    public final static String FILE_NAME = "seatMapping.txt";

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
        ArrayList<SeatNumber> availableSeats = bookingDAO.fetchDAO();
        return availableSeats;
    }
}
