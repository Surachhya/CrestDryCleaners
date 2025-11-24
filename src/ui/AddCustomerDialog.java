package ui;

import models.Customer;
import services.CustomerService;

import javax.swing.*;
import java.awt.*;

public class AddCustomerDialog extends JDialog {

    protected JTextField tfFName;
    protected JTextField tfLName;
    protected JTextField tfPhone;
    protected JTextField tfEmail;
    protected JTextField tfAddress;
    protected JTextField tfPayment;

    protected JButton btnAdd;

    protected CustomerService service;

    public AddCustomerDialog(JFrame parent, String title) {
        super(parent, title, true);

        service = new CustomerService();

        setSize(400, 400);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(8, 2, 5, 5));

        tfFName = new JTextField();
        tfLName = new JTextField();
        tfPhone = new JTextField();
        tfEmail = new JTextField();
        tfAddress = new JTextField();
        tfPayment = new JTextField();

        btnAdd = new JButton("Add");

        add(new JLabel("First Name:"));
        add(tfFName);
        add(new JLabel("Last Name:"));
        add(tfLName);
        add(new JLabel("Phone:"));
        add(tfPhone);
        add(new JLabel("Email:"));
        add(tfEmail);
        add(new JLabel("Address:"));
        add(tfAddress);
        add(new JLabel("Payment Info:"));
        add(tfPayment);

        add(new JLabel()); // empty cell
        add(btnAdd);

        // Default Add action
        btnAdd.addActionListener(e -> {
            Customer customer = new Customer(
                    0,
                    tfFName.getText(),
                    tfLName.getText(),
                    tfPhone.getText(),
                    tfEmail.getText(),
                    tfAddress.getText(),
                    tfPayment.getText()
            );
            boolean success = service.addCustomer(customer);
            if (success) {
                JOptionPane.showMessageDialog(this, "Customer added!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add customer!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    protected void setCustomerData(Customer customer) {
        tfFName.setText(customer.getFirstName());
        tfLName.setText(customer.getLastName());
        tfPhone.setText(customer.getPhone());
        tfEmail.setText(customer.getEmail());
        tfAddress.setText(customer.getAddress());
        tfPayment.setText(customer.getPaymentInfo());
    }
}
