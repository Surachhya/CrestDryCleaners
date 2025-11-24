package ui;

import models.Customer;
import services.CustomerService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCustomerDialog extends AddCustomerDialog {

    private final Customer customer;

    public EditCustomerDialog(JFrame parent, Customer customer) {
        super(parent, "Edit Customer");
        this.customer = customer;

        // Populate existing data
        setCustomerData(customer);

        // Change Add button to Save
        btnAdd.setText("Save");

        // Remove old Add action
        for (ActionListener al : btnAdd.getActionListeners()) {
            btnAdd.removeActionListener(al);
        }

        // Set Save action
        btnAdd.addActionListener(e -> saveCustomer());
    }

    private void saveCustomer() {
        customer.setFirstName(tfFName.getText());
        customer.setLastName(tfLName.getText());
        customer.setPhone(tfPhone.getText());
        customer.setEmail(tfEmail.getText());
        customer.setAddress(tfAddress.getText());
        customer.setPaymentInfo(tfPayment.getText());

        boolean success = service.updateCustomer(customer);

        if (success) {
            JOptionPane.showMessageDialog(this, "Customer updated successfully!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update customer!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
