package moviereportapp;

public class MovieTickets implements IMovieTickets {

    @Override
    public int TotalMovieSales(int[] movieTicketSales) {
        int total = 0;
        for (int sales : movieTicketSales) {
            total += sales;
        }
        return total;
    }

    @Override
    public String TopMovie(String[] movies, int[] totalSales) {
        if (movies == null || totalSales == null || movies.length == 0 || totalSales.length != movies.length) {
            return "";
        }
        
        int maxIndex = 0;
        int maxSales = totalSales[0];
        
        for (int i = 1; i < totalSales.length; i++) {
            if (totalSales[i] > maxSales) {
                maxSales = totalSales[i];
                maxIndex = i;
            }
        }
        return movies[maxIndex];
    }

    double CalculateTotalTicketPrice(int numTickets, double price) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}