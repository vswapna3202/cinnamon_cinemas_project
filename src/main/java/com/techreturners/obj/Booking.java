package com.techreturners.obj;

import com.techreturners.app.BookingApp;

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
        return true;
    }

    public ArrayList<SeatNumber> checkAvailableSeats(int noOfSeats)
            throws IOException,
                   NumberFormatException{
        String fileName = FILE_NAME;
        ClassLoader classLoader = BookingApp.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME);
        ArrayList<SeatNumber> availableSeats = new ArrayList<>();
        if (inputStream != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
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

        }//else{
           // throw new FileNotFoundException("File seatMapping.txt not found");
        //}
        return availableSeats;
    }
}
