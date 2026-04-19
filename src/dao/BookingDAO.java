package dao;

import model.Booking;
import utils.DBConnection;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class BookingDAO {

    public boolean bookFlight(Booking booking) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "INSERT INTO bookings (user_id, flight_id) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getFlightId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public int getTotalBookings() {
        int count = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String query = "SELECT COUNT(*) AS total FROM bookings";
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
    public int getBookingsByUser(int userId) {
        int count = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String query = "SELECT COUNT(*) AS total FROM bookings WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
    public void showAllBookings() {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "SELECT b.booking_id, b.user_id, f.source, f.destination " +
                           "FROM bookings b JOIN flights f " +
                           "ON b.flight_id = f.flight_id";

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                    "Booking ID: " + rs.getInt("booking_id") +
                    " | User ID: " + rs.getInt("user_id") +
                    " | " + rs.getString("source") +
                    " → " + rs.getString("destination")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean cancelBooking(int bookingId) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "DELETE FROM bookings WHERE booking_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bookingId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public int getFlightIdByBooking(int bookingId) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "SELECT flight_id FROM bookings WHERE booking_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, bookingId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("flight_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}