package TicketProcessing;

public class MovieTickets implements IMovieTickets {

    @Override
    public double CalculateTotalTicketPrice(int numberOfTickets, double ticketPrice) {
        double subtotal = numberOfTickets * ticketPrice;
        double vat = subtotal * 0.14; // 14% VAT
        return subtotal + vat;
    }

    @Override
    public boolean ValidateData(MovieTicketData movieTicketData) {
        if (movieTicketData == null) return false;
        if (movieTicketData.getMovieName() == null || movieTicketData.getMovieName().trim().isEmpty()) return false;
        if (movieTicketData.getTicketPrice() <= 0) return false;
        if (movieTicketData.getNumberOfTickets() <= 0) return false;
        return true;
    }
}