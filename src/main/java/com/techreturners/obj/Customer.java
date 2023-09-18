package com.techreturners.obj;

import java.util.Scanner;

public class Customer {
    private final String  customerName;
    private final String  customerAddress;
    private final String customerContactNo;
    private final String customerEmail;


    public Customer(String customerName,
                    String customerAddress,
                    String customerEmail,
                    String customerContactNo) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContactNo = customerContactNo;
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerContactNo() {
        return customerContactNo;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public static Object[] getAndValidateCustomerInput(){
        return Customer.getCustomerInput();
    }

    private static String getUserInput(String prompt,
                                       Scanner scanner){
        String variableName = "";
        while(variableName == null || variableName.isEmpty()) {
            System.out.print(prompt);
            variableName = scanner.nextLine();
        }
        return variableName;
    }

    private static Object[] getCustomerInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Cinnamon Cinemas!");
        System.out.println("Enter the following details: ");

        String prompt = "Enter your name: ";
        String customerName = getUserInput(prompt, scanner);

        prompt = "Enter your address: ";
        String customerAddress = getUserInput(prompt, scanner);

        prompt = "Enter your email: ";
        String customerEmail = getUserInput(prompt, scanner);

        prompt = "Enter your contact number: ";
        String customerContactNumber = getUserInput(prompt, scanner);


        int noOfSeats = 0;
        String pattern = "\\d+\\s*";
        boolean isValid = false;
        while(noOfSeats == 0 || !isValid) {
            System.out.println("Enter number of seats between 1 and 3 for booking");
            String inputStr = scanner.nextLine();
            if (inputStr.matches(pattern)){
                noOfSeats = Integer.parseInt(inputStr);
                if ((noOfSeats >= 1) && (noOfSeats <= 3))
                    isValid = true;
            }
        }

        Customer customer = new Customer(customerName,
                                        customerAddress,
                                        customerEmail,
                                        customerContactNumber);
        return new Object[] {customer, noOfSeats};
    }
}
