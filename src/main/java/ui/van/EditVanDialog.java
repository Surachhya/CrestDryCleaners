package ui.van;

import models.Store;
import models.Van;

import javax.swing.*;
import java.awt.*;

public class EditVanDialog extends AddVanDialog {

    private final Van van;

    public EditVanDialog(JFrame parent, Van van) {
        super(parent, "Edit Van");
        this.van = van;

        setVanData(van);
        btnAdd.setText("Save");

        // Remove old listener
        for (var al : btnAdd.getActionListeners()) btnAdd.removeActionListener(al);

        btnAdd.addActionListener(e -> saveVan());
    }

    private void saveVan() {
        van.setStoreID(((Store) cbStore.getSelectedItem()).getStoreId());
        van.setPlateNumber(tfPlate.getText());
        van.setModel(tfModel.getText());
        van.setYear(Integer.parseInt(tfYear.getText()));
        van.setCapacity(Integer.parseInt(tfCapacity.getText()));

        boolean success = service.updateVan(van);
        if (success) {
            JOptionPane.showMessageDialog(this, "Van updated successfully!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update van!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
