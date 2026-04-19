package service;

import dao.FlightDAO;
import model.Flight;

public class FlightService {

    FlightDAO flightDAO = new FlightDAO();

    public void addFlight(String source, String destination, int seats) {
        Flight flight = new Flight(source, destination, seats);

        if (flightDAO.addFlight(flight)) {
            System.out.println("Flight Added Successfully!");
        } else {
            System.out.println("Failed to Add Flight!");
        }
    }
    public void showFlights() {
        flightDAO.getAllFlights();
    }
}