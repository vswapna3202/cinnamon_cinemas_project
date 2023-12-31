package com.techreturners.dao;

import com.techreturners.exception.CustomCinnamonCinemaException;
import com.techreturners.obj.SeatNumber;

import java.io.IOException;
import java.util.ArrayList;

public abstract class BookingDAO {
    public abstract boolean persistDAO(ArrayList<SeatNumber> newSeatNumbers)
            throws CustomCinnamonCinemaException;
    public abstract ArrayList<SeatNumber> fetchDAO() throws IOException;

}
