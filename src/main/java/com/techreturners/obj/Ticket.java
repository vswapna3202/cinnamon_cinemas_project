package com.techreturners.obj;

import java.util.ArrayList;

public class Ticket {
    private final int ticketId;
    private final ArrayList<SeatNumber> seatNumbers;

    public Ticket(int ticketId, ArrayList<SeatNumber> seatNumbers){
        this.ticketId = ticketId;
        this.seatNumbers = seatNumbers;
    }

    public void displayTicket(Customer customer, int noOfSeats){
        System.out.println("Dear "+customer.getCustomerName()+"\n@ [ "
                        +customer.getCustomerAddress()+" - Contact:  "
                        +customer.getCustomerContactNo()+"] \n"
                        +"Your "+ noOfSeats+ " ticket(s) has been booked "
                        +"\nYour ticket Id is: "+this.ticketId+" "
                        +"\nIt has been emailed to you @ "+customer.getCustomerEmail()
                        +"\nYour seat numbers are: ");
        for(SeatNumber seatNumber : seatNumbers){
            System.out.print("Row: "+seatNumber.row()+" ");
            System.out.print("Seat: "+seatNumber.seat()+"\n");
        }
    }
}
