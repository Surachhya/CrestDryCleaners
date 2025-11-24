package ui;

import models.Customer;
import services.CustomerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public CustomerPanel() {
        setLayout(new BorderLayout());

        // Column headers
        String[] columns = {"ID", "First Name", "Last Name", "Phone", "Email", "Address", "Payment Info"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadCustomers();
    }

    private void loadCustomers() {
        CustomerService service = new CustomerService();
        List<Customer> customers = service.getAllCustomers();
        for (Customer c : customers) {
            tableModel.addRow(new Object[]{
                    c.getCustomerId(),
                    c.getFName(),
                    c.getLName(),
                    c.getPhone(),
                    c.getEmail(),
                    c.getAddress(),
                    c.getPaymentInfo()
            });
        }
    }
}
