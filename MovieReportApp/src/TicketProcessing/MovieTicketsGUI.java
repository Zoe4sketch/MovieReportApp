package TicketProcessing;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class MovieTicketsGUI extends JFrame {
    private JComboBox<String> cmbMovie;
    private JTextField txtNumTickets;
    private JTextField txtTicketPrice;
    private JTextArea txtReport;
    
    private final MovieTickets processor = new MovieTickets();

    public MovieTicketsGUI() {
        setTitle("MOVIE TICKETS - PROCESSING SYSTEM");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem itemExit = new JMenuItem("Exit");
        menuFile.add(itemExit);
        
        JMenu menuTools = new JMenu("Tools");
        JMenuItem itemProcess = new JMenuItem("Process");
        JMenuItem itemClear = new JMenuItem("Clear");
        menuTools.add(itemProcess);
        menuTools.add(itemClear);
        
        menuBar.add(menuFile);
        menuBar.add(menuTools);
        setJMenuBar(menuBar);

        // Input panel
        JPanel panelInput = new JPanel(new GridLayout(3, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panelInput.add(new JLabel("MOVIE:"));
        cmbMovie = new JComboBox<>(new String[]{"Napoleon", "Oppenheimer", "Damsel"});
        panelInput.add(cmbMovie);

        panelInput.add(new JLabel("NUMBER OF TICKETS:"));
        txtNumTickets = new JTextField();
        panelInput.add(txtNumTickets);

        panelInput.add(new JLabel("TICKET PRICE:"));
        txtTicketPrice = new JTextField();
        panelInput.add(txtTicketPrice);

        add(panelInput, BorderLayout.NORTH);

        // Report display panel
        JPanel panelReport = new JPanel(new BorderLayout());
        panelReport.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        panelReport.add(new JLabel("TICKET REPORT:"), BorderLayout.NORTH);
        
        txtReport = new JTextArea();
        txtReport.setEditable(false);
        txtReport.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtReport);
        panelReport.add(scrollPane, BorderLayout.CENTER);

        add(panelReport, BorderLayout.CENTER);

        // Events Actions
        itemExit.addActionListener(e -> System.exit(0));
        itemClear.addActionListener(e -> clearFields());
        itemProcess.addActionListener(e -> processTicketSales());
    }

    private void clearFields() {
        cmbMovie.setSelectedIndex(0);
        txtNumTickets.setText("");
        txtTicketPrice.setText("");
        txtReport.setText("");
    }

    private void processTicketSales() {
        try {
            String movieName = (String) cmbMovie.getSelectedItem();
            int numTickets = Integer.parseInt(txtNumTickets.getText().trim());
            double ticketPrice = Double.parseDouble(txtTicketPrice.getText().trim());

            MovieTicketData data = new MovieTicketData(movieName, ticketPrice, numTickets);

            if (!processor.ValidateData(data)) {
                JOptionPane.showMessageDialog(this, "Validation Failed! Verify positive numeric selections.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double totalCost = processor.CalculateTotalTicketPrice(numTickets, ticketPrice);

            String reportText = "MOVIE TICKET REPORT\n" +
                                "*****************************************\n" +
                                "MOVIE NAME: " + movieName + "\n" +
                                "MOVIE TICKET PRICE: R " + String.format("%.1f", ticketPrice) + "\n" +
                                "NUMBER OF TICKETS: " + numTickets + "\n" +
                                "TOTAL TICKET PRICE: R " + String.format("%.1f", totalCost) + "\n" +
                                "*****************************************";

            txtReport.setText(reportText);
            saveReportToFile(reportText);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric integers and decimal prices.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveReportToFile(String reportContent) {
        try (FileWriter writer = new FileWriter("report.txt")) {
            writer.write(reportContent);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing report text file destination.", "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MovieTicketsGUI().setVisible(true));
    }
}