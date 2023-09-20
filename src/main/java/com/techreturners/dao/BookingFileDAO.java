package com.techreturners.dao;

import com.techreturners.app.BookingApp;
import com.techreturners.exception.CustomCinnamonCinemaException;
import com.techreturners.obj.Booking;
import com.techreturners.obj.Row;
import com.techreturners.obj.Seat;
import com.techreturners.obj.SeatNumber;

import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class BookingFileDAO extends BookingDAO {
    public static final String FILE_NAME = "seatMapping.txt";
    public boolean persistDAO(ArrayList<SeatNumber> newSeatNumbers)
        throws CustomCinnamonCinemaException{
        try {
            // Fetch seatNumbers already allocated check if another
            // booking has happened before persisting and allocating
            ArrayList<SeatNumber> seatNumbers = this.fetchDAO();
            if (seatNumbers.containsAll(newSeatNumbers)){
                throw new CustomCinnamonCinemaException("Seats requested " +
                        "already exists in seatMapping.txt "+
                        "Unable to book tickets. Please try later");
            }
        }catch(IOException ioe){
            System.out.println("Error accessing file seatMapping.txt"+ioe.getMessage());
        }
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
                    "update has occurred. Kindly re-try booking" + ioe.getMessage());
            return false;
        }
        return true;
    }

    public  ArrayList<SeatNumber> fetchDAO()
            throws IOException, NumberFormatException{
        ClassLoader classLoader = BookingApp.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME);
        ArrayList<SeatNumber> availableSeats = new ArrayList<>();
        if (inputStream != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if(!line.isEmpty()) {
                        Row row = Row.valueOf(line.substring(0, 1));
                        Seat seat = Seat.fromInt(Integer.parseInt(line.substring(1)));
                        SeatNumber seatNumber = new SeatNumber(row, seat);
                        availableSeats.add(seatNumber);
                    }
                }
                bufferedReader.close();
                inputStream.close();
                return availableSeats;
            }catch(IOException ioe){
                throw new IOException("Could not read file, File IO Error occured");
            }catch(NumberFormatException nfe){
                throw new NumberFormatException("Seat Number was not a number in seatMapping.txt file");
            }
        }else{
           throw new FileNotFoundException("File seatMapping.txt not found");
        }
    }
}
