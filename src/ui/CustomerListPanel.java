package ui;

import models.Customer;
import services.CustomerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerListPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public CustomerListPanel() {
        setLayout(new BorderLayout());

        String[] columns = {
                "Customer ID", "First Name", "Last Name",
                "Phone", "Email", "Address", "Payment Info"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setFillsViewportHeight(true);

        loadData();

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void loadData() {
        CustomerService service = new CustomerService();
        List<Customer> customers = service.getAllCustomers();

        for (Customer c : customers) {
            model.addRow(new Object[]{
                    c.getCustomerId(),
                    c.getFirstName(),
                    c.getLastName(),
                    c.getPhone(),
                    c.getEmail(),
                    c.getAddress(),
                    c.getPaymentInfo()
            });
        }
    }
}
