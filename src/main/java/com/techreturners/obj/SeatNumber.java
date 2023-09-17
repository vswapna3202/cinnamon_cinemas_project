package com.techreturners.obj;

public class SeatNumber {
    private Row row;
    private Seat seat;

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

    @Override
    public String toString() {
        return row.name() + seat.getSeatNumber();
    }
}
