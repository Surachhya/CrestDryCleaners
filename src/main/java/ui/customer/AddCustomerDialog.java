package ui.customer;

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
        setSize(400, 300);
        service = new CustomerService();

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        tfFName = new JTextField();
        tfLName = new JTextField();
        tfPhone = new JTextField();
        tfEmail = new JTextField();
        tfAddress = new JTextField();
        tfPayment = new JTextField();

        btnAdd = new JButton("Add");

        form.add(row("First Name:", tfFName));
        form.add(row("Last Name:", tfLName));
        form.add(row("Phone:", tfPhone));
        form.add(row("Email:", tfEmail));
        form.add(row("Address:", tfAddress));
        form.add(row("Payment Info:", tfPayment));

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAdd);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        form.add(btnPanel);

        add(form);

        setLocationRelativeTo(parent);

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

    private JPanel row(String label, JComponent field) {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.add(new JLabel(label), BorderLayout.WEST);
        p.add(field, BorderLayout.CENTER);

        p.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        return p;
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
