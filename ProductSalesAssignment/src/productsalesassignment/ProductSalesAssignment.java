package productsalesassignment;

public class ProductSalesAssignment {

    public static void main(String[] args) {
        
        int[][] salesData = {
            {300, 150, 700}, // Year 1
            {250, 200, 600}  // Year 2
        };

        ProductSales reporter = new ProductSales();

        int total = reporter.TotalSales(salesData);
        long average = Math.round(reporter.AverageSales(salesData));
        int max = reporter.MaxSale(salesData);
        int min = reporter.MinSale(salesData);

      
        System.out.println("PRODUCT SALES REPORT - 2025");
        System.out.println("---------------------------");
        System.out.printf("Total sales:   %d\n", total);
        System.out.printf("Average sales: %d\n", average);
        System.out.printf("Maximum sale:  %d\n", max);
        System.out.printf("Minimum sale:  %d\n", min);
    }
}