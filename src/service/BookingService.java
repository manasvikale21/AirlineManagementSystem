package service;

import dao.BookingDAO;
import dao.FlightDAO;
import model.Booking;

public class BookingService {

    BookingDAO bookingDAO = new BookingDAO();
    FlightDAO flightDAO = new FlightDAO();

    public void bookTicket(int userId, int flightId) {
        int seats = flightDAO.getAvailableSeats(flightId);
        if (seats > 0) {
            Booking booking = new Booking(userId, flightId);

            if (bookingDAO.bookFlight(booking)) {
                flightDAO.reduceSeat(flightId);  // 👈 reduce seat
                System.out.println("Ticket Booked Successfully!");
            } else {
                System.out.println("Booking Failed!");
            }
        } else {
            System.out.println("No seats available!");
        }
    }
    public void showTotalBookings() {
        int total = bookingDAO.getTotalBookings();
        System.out.println("Total Tickets Booked: " + total);
    }
    public void showUserBookings(int userId) {
        int total = bookingDAO.getBookingsByUser(userId);
        System.out.println("User " + userId + " booked tickets: " + total);
    }
    public void showAllBookings() {
        bookingDAO.showAllBookings();
    }
    public void cancelBooking(int bookingId) {

        int flightId = bookingDAO.getFlightIdByBooking(bookingId);

        if (flightId == -1) {
            System.out.println("Invalid Booking ID!");
            return;
        }

        if (bookingDAO.cancelBooking(bookingId)) {
            flightDAO.increaseSeat(flightId);
            System.out.println("Booking Cancelled Successfully!");
        } else {
            System.out.println("Cancellation Failed!");
        }
    }
}