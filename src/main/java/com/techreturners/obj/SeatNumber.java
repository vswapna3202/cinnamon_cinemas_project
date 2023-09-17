package com.techreturners.obj;

public class SeatNumber {
    private Row row;
    private Seat seat;
    private char locked;

    public SeatNumber(Row row, Seat seat){
        this.row = row;
        this.seat = seat;
        this.locked = 'N';
    }

    public Row getRow() {
        return row;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setLocked(char locked) {
        this.locked = locked;
    }

    public char isLocked() {
        return locked;
    }
}
