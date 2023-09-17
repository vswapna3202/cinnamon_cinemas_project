package com.techreturners.obj;

public enum Seat {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);
    private final int seatNumber;
    Seat(int seatNumber){
        this.seatNumber = seatNumber;
    }
    public int getSeatNumber(){
        return seatNumber;
    }
    public static Seat fromInt(int seatNumber){
        return switch(seatNumber) {
            case 1 -> Seat.ONE;
            case 2 -> Seat.TWO;
            case 3 -> Seat.THREE;
            case 4 -> Seat.FOUR;
            case 5 -> Seat.FIVE;
            default -> throw new IllegalArgumentException("Invalid seat number in seatMapping.txt file");
        };
    }
}
