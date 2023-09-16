package com.techreturners.obj;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CustomerTest {
    ByteArrayOutputStream outputStream;
    PrintStream originalOut;

    @Test
    public void testGetCustomerAndValidateInputValid(){
        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        //Simulating input for testing main method
        String simulatedInput = String.join("\n",
                CinnamonCinemaTestData.customer.getCustomerName(),
                CinnamonCinemaTestData.customer.getCustomerAddress(),
                CinnamonCinemaTestData.customer.getCustomerContactNo(),
                CinnamonCinemaTestData.customer.getCustomerEmail(),
                String.valueOf(CinnamonCinemaTestData.noOfSeats)
        );

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Object[] input = Customer.getAndValidateCustomerInput();
        Customer customerInput = (Customer) input[0];
        int noOfSeatsInput = (int) input[1];
        assertEquals(CinnamonCinemaTestData.customer.getCustomerName(),
                customerInput.getCustomerName());
        assertEquals(CinnamonCinemaTestData.customer.getCustomerAddress(),
                customerInput.getCustomerAddress());
        assertEquals(CinnamonCinemaTestData.customer.getCustomerContactNo(),
                customerInput.getCustomerContactNo());
        assertEquals(CinnamonCinemaTestData.customer.getCustomerEmail(),
                customerInput.getCustomerEmail());
        assertEquals(CinnamonCinemaTestData.noOfSeats, noOfSeatsInput);
    }

    @Test
    public void testGetCustomerAndValidateInputEmptyStrings(){
        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        //Simulating input for testing main method
        String simulatedInput = String.join("\n",
                "", CinnamonCinemaTestData.customerName,
                          "", CinnamonCinemaTestData.customerAddress,
                          "", CinnamonCinemaTestData.customerContactNo,
                          "", CinnamonCinemaTestData.customerEmail,
                String.valueOf(CinnamonCinemaTestData.noOfSeats)
        );

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Object[] input = Customer.getAndValidateCustomerInput();
        Customer customerInput = (Customer) input[0];
        int noOfSeatsInput = (int) input[1];
        assertEquals(CinnamonCinemaTestData.customer.getCustomerName(),
                customerInput.getCustomerName());
        assertEquals(CinnamonCinemaTestData.customer.getCustomerAddress(),
                customerInput.getCustomerAddress());
        assertEquals(CinnamonCinemaTestData.customer.getCustomerContactNo(),
                customerInput.getCustomerContactNo());
        assertEquals(CinnamonCinemaTestData.customer.getCustomerEmail(),
                customerInput.getCustomerEmail());
        assertEquals(CinnamonCinemaTestData.noOfSeats, noOfSeatsInput);
    }
}
