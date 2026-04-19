package model;

public class Booking {
    private int userId;
    private int flightId;

    public Booking(int userId, int flightId) {
        this.userId = userId;
        this.flightId = flightId;
    }

    public int getUserId() { return userId; }
    public int getFlightId() { return flightId; }
}

