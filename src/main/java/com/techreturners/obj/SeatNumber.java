package com.techreturners.obj;

public class SeatNumber {
    private Row row;
    private Seat seat;
    private char locked;

    public SeatNumber(Row row, Seat seat){
        this.row = row;
        this.seat = seat;
    }

    public Row getRow() {
        return row;
    }

    public Seat getSeat() {
        return seat;
    }
}
