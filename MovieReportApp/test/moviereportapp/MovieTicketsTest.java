package moviereportapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTicketsTest {

    @Test
    public void CalculateTotalSales_ReturnsExpectedTotalSales() {
        MovieTickets processor = new MovieTickets();
        int[] napoleonSales = {3000, 1500, 1700};
        
        int expectedTotal = 6200;
        int actualTotal = processor.TotalMovieSales(napoleonSales);
        
        assertEquals(expectedTotal, actualTotal, "The total sales calculated for Napoleon is incorrect.");
    }

    @Test
    public void TopMovieSales_ReturnsTopMovie() {
        MovieTickets processor = new MovieTickets();
        String[] movies = {"Napoleon", "Oppenheimer"};
        int[] totalSales = {6200, 6300};
        
        String expectedTopMovie = "Oppenheimer";
        String actualTopMovie = processor.TopMovie(movies, totalSales);
        
        assertEquals(expectedTopMovie, actualTopMovie, "The top performing movie returned is incorrect.");
    }
}