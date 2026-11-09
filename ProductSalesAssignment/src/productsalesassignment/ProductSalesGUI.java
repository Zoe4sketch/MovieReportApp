package productsalesassignment;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class ProductSalesGUI extends JFrame {

    private JTextArea txtReport;
    private JLabel lblYearsProcessed;
    
    // In-memory 2D array tracking Year 1 and Year 2 sales data
    private final int[][] salesData = {
        {300, 150, 700}, // Year 1
        {250, 200, 600}  // Year 2
    };
    private final int SALES_LIMIT = 500;

    public ProductSalesGUI() {
        // Main Application Window Settings
        setTitle("Product Sales Application");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Top Pull-Down Menu Bar System
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem loadItem = new JMenuItem("Load Product Data");
        JMenuItem saveItem = new JMenuItem("Save Product Data");
        JMenuItem clearItem = new JMenuItem("Clear");

        loadItem.addActionListener(e -> loadProductData());
        saveItem.addActionListener(e -> saveProductData());
        clearItem.addActionListener(e -> clearForm());

        toolsMenu.add(loadItem);
        toolsMenu.add(saveItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // 2. Control Layout Panel (North Side Buttons)
        JPanel btnPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        JButton btnLoad = new JButton("Load Product Data");
        JButton btnSave = new JButton("Save Product Data");

        btnLoad.addActionListener(e -> loadProductData());
        btnSave.addActionListener(e -> saveProductData());

        btnPanel.add(btnLoad);
        btnPanel.add(btnSave);
        add(btnPanel, BorderLayout.NORTH);

        // 3. Central Plain Text Report Display Component Area
        txtReport = new JTextArea();
        txtReport.setEditable(false);
        txtReport.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtReport);
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // 4. Bottom Read-Only Status Bar Information Area
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        lblYearsProcessed = new JLabel("Years Processed: ");
        statusPanel.add(lblYearsProcessed);
        add(statusPanel, BorderLayout.SOUTH);
    }

    private void loadProductData() {
        int totalSales = 0;
        int totalItems = 0;
        int overLimitCount = 0;
        int underLimitCount = 0;

        // Iterating across data structure using conditional threshold rules (500)
        for (int[] yearSales : salesData) {
            for (int sale : yearSales) {
                totalSales += sale;
                totalItems++;
                if (sale > SALES_LIMIT) {
                    overLimitCount++;
                } else {
                    underLimitCount++;
                }
            }
        }

        int averageSales = totalItems == 0 ? 0 : totalSales / totalItems;

        // Construct formatting layout text
        StringBuilder sb = new StringBuilder();
        sb.append("Total Sales: ").append(totalSales).append("\n");
        sb.append("Average Sales: ").append(averageSales).append("\n");
        sb.append("Sales over limit: ").append(overLimitCount).append("\n");
        sb.append("Sales under limit: ").append(underLimitCount).append("\n");

        txtReport.setText(sb.toString());
        lblYearsProcessed.setText("Years Processed: " + salesData.length);
    }

    private void saveProductData() {
        if (txtReport.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No data available to save. Please load data first.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Export stream saving information out to a standard local text document
        try (FileWriter writer = new FileWriter("data.txt")) {
            writer.write("DATA LOG\n");
            writer.write("**********************************\n");
            writer.write(txtReport.getText());
            writer.write("**********************************\n");
            
            JOptionPane.showMessageDialog(this, "Data successfully saved to data.txt", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "An error occurred while saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtReport.setText("");
        lblYearsProcessed.setText("Years Processed: ");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductSalesGUI().setVisible(true);
        });
    }
}