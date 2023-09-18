package com.techreturners.obj;

public record SeatNumber(Row row, Seat seat) {

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
}
