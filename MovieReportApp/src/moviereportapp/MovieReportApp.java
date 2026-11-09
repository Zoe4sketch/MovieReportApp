package moviereportapp;

public class MovieReportApp {
    public static void main(String[] args) {
        // Single-dimensional array for movie names
        String[] movies = {"Napoleon", "Oppenheimer"};
        
        // Two-dimensional array for monthly ticket sales (Jan, Feb, Mar)
        int[][] salesData = {
            {3000, 1500, 1700}, // Napoleon
            {3500, 1200, 1600}  // Oppenheimer
        };
        
        MovieTickets movieTicketsProcessor = new MovieTickets();
        int[] totalSales = new int[movies.length];
        
        // Display report header
        System.out.println("MOVIE TICKET SALES REPORT - 2024");
        System.out.printf("%-15s%-10s%-10s%-10s\n", "", "JAN", "FEB", "MAR");
        System.out.println("----------------------------------------------");
        
        // Loop through and print table rows while accumulating totals
        for (int i = 0; i < movies.length; i++) {
            System.out.printf("%-15s%-10d%-10d%-10d\n", movies[i], salesData[i][0], salesData[i][1], salesData[i][2]);
            totalSales[i] = movieTicketsProcessor.TotalMovieSales(salesData[i]);
        }
        
        System.out.println("----------------------------------------------");
        
        // Display total ticket sales per movie
        for (int i = 0; i < movies.length; i++) {
            System.out.println("Total movie ticket sales for " + movies[i] + ": " + totalSales[i]);
        }
        
        // Determine and display top performing movie
        String topMovie = movieTicketsProcessor.TopMovie(movies, totalSales);
        System.out.println("\nTop performing movie: " + topMovie);
    }
}