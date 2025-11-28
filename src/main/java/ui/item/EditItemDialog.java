package ui.item;

import models.Item;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EditItemDialog extends AddItemDialog {

    private final Item item;

    public EditItemDialog(JFrame parent, Item item) {
        super(parent, "Edit Item");
        this.item = item;

        setItemData(item);

        btnAdd.setText("Save");

        // Remove old Add listener
        for (ActionListener al : btnAdd.getActionListeners()) {
            btnAdd.removeActionListener(al);
        }

        btnAdd.addActionListener(e -> saveItem());
    }

    private void saveItem() {
        item.setOrderID(((models.Order) cbOrder.getSelectedItem()).getOrderId());
        item.setType(tfType.getText());
        item.setBrand(tfBrand.getText());
        item.setColor(tfColor.getText());
        item.setMaterial(tfMaterial.getText());
        item.setPattern(tfPattern.getText());
        item.setSpecialRequest(tfSpecial.getText());
        item.setBarcode(tfBarcode.getText());

        boolean success = service.updateItem(item);
        if (success) {
            JOptionPane.showMessageDialog(this, "Item updated successfully!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update item!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
