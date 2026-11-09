package TicketProcessing;

public class MovieTicketData {
    private String movieName;
    private double ticketPrice;
    private int numberOfTickets;

    public MovieTicketData(String movieName, double ticketPrice, int numberOfTickets) {
        this.movieName = movieName;
        this.ticketPrice = ticketPrice;
        this.numberOfTickets = numberOfTickets;
    }

    public String getMovieName() { return movieName; }
    public double getTicketPrice() { return ticketPrice; }
    public int getNumberOfTickets() { return numberOfTickets; }
}