package com.techreturners.obj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketTest {
    Ticket ticket;
    ByteArrayOutputStream outputStream;
    PrintStream originalOut;
    @BeforeEach
    public void setUp(){

        ticket = new Ticket(CinnamonCinemaTestData.ticketId,
                            CinnamonCinemaTestData.availableListExpected);
    }

    @Test
    public void testDisplayTickets(){
        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ticket.displayTicket(CinnamonCinemaTestData.customer,
                CinnamonCinemaTestData.noOfSeats);
        String consoleOutput = outputStream.toString();

        Pattern pattern = Pattern.compile(CinnamonCinemaTestData.patternData,
                Pattern.DOTALL);
        Matcher matcher = pattern.matcher(consoleOutput);
        assertTrue(matcher.find());
    }
}
