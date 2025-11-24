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
    private CustomerService service = new CustomerService();
    private JFrame parent;
    private JTextField tfSearch;

    public CustomerListPanel() {
        this.parent = parent;
        this.service = service;

        setLayout(new BorderLayout());

        // Top panel for buttons + search
        JPanel topPanel = new JPanel();
        JButton btnAdd = new JButton("Add Customer");
        JButton btnEdit = new JButton("Edit Customer");
        JButton btnDelete = new JButton("Delete Customer");
        tfSearch = new JTextField(20);
        JButton btnSearch = new JButton("Search");

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);
        topPanel.add(tfSearch);
        topPanel.add(btnSearch);
        add(topPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {"Customer ID", "First Name", "Last Name", "Phone", "Email", "Address", "Payment Info"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadData();

        // Button actions
        btnAdd.addActionListener(e -> {
            AddCustomerDialog dialog = new AddCustomerDialog(parent, "Add Customer");
            dialog.setVisible(true);
            loadData();
        });

        btnEdit.addActionListener(e -> editCustomer());
        btnDelete.addActionListener(e -> deleteCustomer());
        btnSearch.addActionListener(e -> searchCustomer());
    }

    private void loadData() {
        model.setRowCount(0);
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

    private void editCustomer() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = (int) table.getValueAt(row, 0);
            Customer c = service.getCustomerById(id);
            AddCustomerDialog dialog = new AddCustomerDialog(parent, "Update Customer");
            dialog.setCustomerData(c);
            dialog.setVisible(true);
            loadData();
        } else {
            JOptionPane.showMessageDialog(parent, "Select a customer to edit.");
        }
    }

    private void deleteCustomer() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int id = (int) table.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(parent, "Delete this customer?");
            if (confirm == JOptionPane.YES_OPTION) {
                service.deleteCustomer(id);
                loadData();
            }
        } else {
            JOptionPane.showMessageDialog(parent, "Select a customer to delete.");
        }
    }

    private void searchCustomer() {
        String keyword = tfSearch.getText().trim().toLowerCase();
        model.setRowCount(0);
        List<Customer> customers = service.getAllCustomers();
        for (Customer c : customers) {
            if (c.getFirstName().toLowerCase().contains(keyword) ||
                    c.getLastName().toLowerCase().contains(keyword) ||
                    c.getEmail().toLowerCase().contains(keyword) ||
                    c.getPhone().toLowerCase().contains(keyword)) {
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
}
