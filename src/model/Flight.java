package model;

public class Flight {
	private int flightId;
    private String source;
    private String destination;
    private int seats;

    public Flight(String source, String destination, int seats) {
        this.source = source;
        this.destination = destination;
        this.seats = seats;
    }

    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public int getSeats() { return seats; }

}
