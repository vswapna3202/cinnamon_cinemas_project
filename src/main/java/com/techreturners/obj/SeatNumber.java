package com.techreturners.obj;

import java.util.Objects;

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatNumber that = (SeatNumber) o;
        return row == that.row &&
                seat == that.seat;
    }
    @Override
    public int hashCode() {
        return Objects.hash(row, seat);
    }
}
