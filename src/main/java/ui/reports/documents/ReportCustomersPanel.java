package ui.reports.documents;

import services.CustomerService;
import models.Customer;
import ui.reports.ReportHeaderPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReportCustomersPanel extends JPanel {

    private CustomerService customerService = new CustomerService();

    public ReportCustomersPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(new ReportHeaderPanel("Customer List"), BorderLayout.NORTH);

        // Fetch data
        List<Customer> customers = customerService.getAllCustomers();

        // Table model
        String[] cols = {"ID", "First Name", "Last Name", "Phone", "Email"};
        Object[][] data = new Object[customers.size()][cols.length];

        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            data[i][0] = c.getCustomerId();
            data[i][1] = c.getFirstName();
            data[i][2] = c.getLastName();
            data[i][3] = c.getPhone();
            data[i][4] = c.getEmail();
        }

        JTable table = new JTable(data, cols);
        table.setFillsViewportHeight(true);

        table.setIntercellSpacing(new Dimension(5, 5));
        table.setRowHeight(20);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        JPanel padded = new JPanel(new BorderLayout());
        padded.setBackground(Color.white);
        padded.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        padded.add(scroll, BorderLayout.CENTER);

        add(padded, BorderLayout.CENTER);

    }
}