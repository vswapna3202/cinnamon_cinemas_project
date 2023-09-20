# **Cinnamon Cinemas Booking**
## ***Version 1.0***

### **Installation:**
    - Clone this repository using git clone https://github.com/vswapna3202/cinnamon_cinemas_project
    - Navigate to project directory cinnamon_cinemas_project 
    - Run mvn test to build and test the project

### **Project Brief:** 
This application enables booking of seats at Cinnamon Cinemas. There are 3 rows named
A, B, C and each row has 5 seats. The customer can request booking of any number of
seats between 1 and 3. The booking is with seat allocation from left to right and then
front to back. Seats allocated to customer are saved and a ticket issued to customer.
When all seats have been booked the app automatically terminates.

### **Inputs:**
    - Customer name
    - Customer address
    - Customer email
    - Customer contact number
    - Number of Seats needed to book (usually between 1 and 3)

### **Outputs:**
The ticket details are displayed to user with customer name, address, email and 
contact numbers. A message with number of seats in the booking and the ticket id with 
seat numbers and row numbers are displayed to user.
The app keeps running until all the seats are booked. It terminates automatically when
all seats have been booked.


### **Sample Output:**
Dear Harry Potter  
@ [ 4 Privet Drive Surrey UK - Contact:  07986541234]  
Your 3 seat(s) has been booked  
Your ticket Id is: 1000  
It has been emailed to you @ harrypotter@hogwarts.com  
Your seat numbers are:  
Row: A Seat: ONE  
Row: A Seat: TWO  
Row: A Seat: THREE  

### **Assumptions:**
    - Customer can do booking of 1 to 3 seats
    - Row numbers are limited to A, B, C
    - Seat numbers are limited to 5 per row
    - Once all seats have been booked, when re-starting app for next booking check
      needs to be done to ensure seatMapping.txt is empty. This is not done 
      automatically in this release and manual intervention is needed.

### **Future considerations:**
    - A data file is used to store the seats allocated and booked. This can be
    stored better in a database with table structure. In future this could be a 
    good extension to this current project
    - The booking system handles seat allocation for one film at the moment and 
    this could be added to selection of films as well before booking the seats
    - The seats and rows are fixed and limited at the moment this could be extended
    to more seats and rows