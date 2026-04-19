package main;

import service.FlightService;
import service.BookingService;
import service.UserService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FlightService flightService = new FlightService();
        UserService userService = new UserService();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (userService.login(email, password)) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("Login Failed!");
            return;
        }
        System.out.println("1. Add Flight");
        System.out.println("2. Show Flights");
        System.out.println("3. Book Ticket");
        System.out.println("4. Total Bookings");
        System.out.println("5. User Bookings");
        System.out.println("6. Show All Bookings");
        System.out.println("7. Cancel Booking");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.print("Enter Source: ");
            String source = sc.nextLine();
            System.out.print("Enter Destination: ");
            String destination = sc.nextLine();
            System.out.print("Enter Seats: ");
            int seats = sc.nextInt();
            flightService.addFlight(source, destination, seats);
        }

        else if (choice == 2) {
            flightService.showFlights();
        }
        else if (choice == 3) {
            System.out.print("Enter User ID: ");
            int userId = sc.nextInt();
            System.out.print("Enter Flight ID: ");
            int flightId = sc.nextInt();
            BookingService bookingService = new BookingService();
            bookingService.bookTicket(userId, flightId);
        }
        else if (choice == 4) {
            BookingService bookingService = new BookingService();
            bookingService.showTotalBookings();
        }
        else if (choice == 5) {
            System.out.print("Enter User ID: ");
            int userId = sc.nextInt();
            BookingService bookingService = new BookingService();
            bookingService.showUserBookings(userId);
        }
        else if (choice == 6) {
            BookingService bookingService = new BookingService();
            bookingService.showAllBookings();
        }
        else if (choice == 7) {
            System.out.print("Enter Booking ID: ");
            int bookingId = sc.nextInt();
            BookingService bookingService = new BookingService();
            bookingService.cancelBooking(bookingId);
        }
    }
}