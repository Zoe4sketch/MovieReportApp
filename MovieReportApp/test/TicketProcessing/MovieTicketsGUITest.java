package TicketProcessing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTicketsGUITest {

    @Test
    public void CalculateTotalTicketPrice_CalculatedSuccessfully() {
        MovieTickets processor = new MovieTickets();
        
        // 3 tickets * R100 each + 14% VAT = 300 + 42 = R342.0
        double expectedTotal = 342.0;
        double actualTotal = processor.CalculateTotalTicketPrice(3, 100.0);
        
        assertEquals(expectedTotal, actualTotal, 0.001, "The calculated ticket price including VAT is incorrect.");
    }

    @Test
    public void ValidationTests_InvalidDataReturnsFalse() {
        MovieTickets processor = new MovieTickets();
        
        // Scenario 1: Empty Movie Name Check
        MovieTicketData badName = new MovieTicketData("", 100.0, 3);
        assertFalse(processor.ValidateData(badName), "Validation must fail with blank names.");

        // Scenario 2: Ticket Quantity Zero or Negative Check
        MovieTicketData badQty = new MovieTicketData("Napoleon", 100.0, 0);
        assertFalse(processor.ValidateData(badQty), "Validation must fail on zero items.");

        // Scenario 3: Valid Input Conditions Check
        MovieTicketData valid = new MovieTicketData("Oppenheimer", 120.0, 2);
        assertTrue(processor.ValidateData(valid), "Validation incorrectly flagged proper standard arguments.");
    }
}