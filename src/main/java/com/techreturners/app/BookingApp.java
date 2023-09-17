package com.techreturners.app;

import com.techreturners.obj.Customer;
import com.techreturners.obj.Row;
import com.techreturners.obj.Seat;
import com.techreturners.obj.SeatNumber;

import java.io.*;
import java.util.ArrayList;

public class BookingApp {
    public static final int TOTAL_SEATS_AVAILABLE = 15;
    public final static String FILE_NAME = "seatMapping.txt";
    public void startBooking(){
        Object[] input = Customer.getAndValidateCustomerInput();
        Customer customer = (Customer) input[0];
        int noOfSeats = (int) input[1];
        try {
            ArrayList<SeatNumber> seatNumbers = checkAvailableSeats(noOfSeats);
            canAllocateSeats(seatNumbers, noOfSeats);
        }catch(FileNotFoundException fe){
            System.out.println("File seatMapping.txt is not found in folder config");
        }catch(IOException ioe){
            System.out.println("Could not read data from seatMapping.txt file, IOException occurred");
        }
    }

    public boolean canAllocateSeats(ArrayList<SeatNumber> seatNumbers,
                                     int noOfSeats){
        return TOTAL_SEATS_AVAILABLE - seatNumbers.size() >= noOfSeats;
    }

    public ArrayList<SeatNumber> checkAvailableSeats(int noOfSeats) throws
            IOException, NumberFormatException {
        String fileName = FILE_NAME;
        ClassLoader classLoader = BookingApp.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME);
        ArrayList<SeatNumber> availableSeats = new ArrayList<>();
       if (inputStream != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    Row row = Row.valueOf(line.substring(0,1));
                    Seat seat = Seat.fromInt(Integer.parseInt(line.substring(1)));
                    SeatNumber seatNumber = new SeatNumber(row, seat);
                    availableSeats.add(seatNumber);
                }
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
    public static void main(String[] args){
        BookingApp bookingApp = new BookingApp();
        bookingApp.startBooking();
    }
}
