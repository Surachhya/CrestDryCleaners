package ui.corporateclient;

import models.CorporateClient;
import services.CorporateClientService;

import javax.swing.*;
import java.awt.*;

public class AddCorporateClientDialog extends JDialog {

    protected JTextField tfCompanyName;
    protected JTextField tfContactPerson;
    protected JTextField tfDiscountRate;

    protected JButton btnAdd;
    protected CorporateClientService service;

    public AddCorporateClientDialog(JFrame parent, String title) {
        super(parent, title, true);

        service = new CorporateClientService();

        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2, 5, 5));

        tfCompanyName = new JTextField();
        tfContactPerson = new JTextField();
        tfDiscountRate = new JTextField();

        btnAdd = new JButton("Add");

        add(new JLabel("Company Name:"));
        add(tfCompanyName);
        add(new JLabel("Contact Person:"));
        add(tfContactPerson);
        add(new JLabel("Discount Rate (%):"));
        add(tfDiscountRate);

        add(new JLabel());
        add(btnAdd);

        btnAdd.addActionListener(e -> {
            CorporateClient client = new CorporateClient();
            client.setCompanyName(tfCompanyName.getText());
            client.setContactPerson(tfContactPerson.getText());
            client.setDiscountRate(Double.parseDouble(tfDiscountRate.getText()));

            boolean success = service.addCorporateClient(client);
            if (success) {
                JOptionPane.showMessageDialog(this, "Corporate client added!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add corporate client!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    protected void setClientData(CorporateClient client) {
        tfCompanyName.setText(client.getCompanyName());
        tfContactPerson.setText(client.getContactPerson());
        tfDiscountRate.setText(String.valueOf(client.getDiscountRate()));
    }
}
