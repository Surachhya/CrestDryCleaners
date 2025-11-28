package ui.corporateclient;

import models.CorporateClient;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EditCorporateClientDialog extends AddCorporateClientDialog {

    private final CorporateClient client;

    public EditCorporateClientDialog(JFrame parent, CorporateClient client) {
        super(parent, "Edit Corporate Client");
        this.client = client;

        setClientData(client);

        btnAdd.setText("Save");

        for (ActionListener al : btnAdd.getActionListeners()) {
            btnAdd.removeActionListener(al);
        }

        btnAdd.addActionListener(e -> saveClient());
    }

    private void saveClient() {
        client.setCompanyName(tfCompanyName.getText());
        client.setContactPerson(tfContactPerson.getText());
        client.setDiscountRate(Double.parseDouble(tfDiscountRate.getText()));

        boolean success = service.updateCorporateClient(client);
        if (success) {
            JOptionPane.showMessageDialog(this, "Corporate client updated successfully!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update corporate client!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
