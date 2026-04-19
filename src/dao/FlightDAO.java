package dao;

import model.Flight;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FlightDAO {

    public boolean addFlight(Flight flight) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO flights (source, destination, seats) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, flight.getSource());
            ps.setString(2, flight.getDestination());
            ps.setInt(3, flight.getSeats());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void getAllFlights() {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "SELECT * FROM flights";
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("flight_id") +
                    " | " + rs.getString("source") +
                    " -> " + rs.getString("destination") +
                    " | Seats: " + rs.getInt("seats")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getAvailableSeats(int flightId) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "SELECT seats FROM flights WHERE flight_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, flightId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("seats");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void reduceSeat(int flightId) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "UPDATE flights SET seats = seats - 1 WHERE flight_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, flightId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void increaseSeat(int flightId) {
        try {
            Connection conn = DBConnection.getConnection();

            String query = "UPDATE flights SET seats = seats + 1 WHERE flight_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, flightId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}