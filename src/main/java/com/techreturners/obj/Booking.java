package com.techreturners.obj;

import com.techreturners.app.BookingApp;
import com.techreturners.dao.BookingDAO;
import com.techreturners.dao.BookingFileDAO;

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

    public boolean allocateAndSaveSeats(ArrayList<SeatNumber> newSeatNumbers,
                                        int noOfSeats){
        BookingDAO bookingDAO = new BookingFileDAO();
        return bookingDAO.persistDAO(newSeatNumbers);
        /*
        ClassLoader classLoader = Booking.class.getClassLoader();
        URL resourceURL = classLoader.getResource(FILE_NAME);
        File outputFile;
        if (resourceURL != null) {
            outputFile = new File(resourceURL.getFile());
        }else{
            outputFile = new File(FILE_NAME);
        }
        try {
            FileChannel channel = FileChannel.open(outputFile.toPath(),
                    StandardOpenOption.APPEND);
            Channels.newInputStream(channel);
            FileLock lock = channel.lock();
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
            for (SeatNumber seatNumber : newSeatNumbers) {
                writer.append(seatNumber.toString());
                writer.newLine();
            }

            lock.release();
            channel.close();
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Unable to allocate and save seats as another" +
                    "update has occured. Kindly re-try booking" + ioe.getMessage());
            return false;
        }
        return true;*/
    }

    public ArrayList<SeatNumber> checkAvailableSeats()
            throws IOException,
                   NumberFormatException{
        BookingDAO bookingDAO = new BookingFileDAO();
        ArrayList<SeatNumber> availableSeats = bookingDAO.fetchDAO();
        return availableSeats;
    }
}
