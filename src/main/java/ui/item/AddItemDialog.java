package ui.item;

import models.Item;
import models.Order;
import services.ItemService;
import services.OrderService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddItemDialog extends JDialog {

    protected JComboBox<Order> cbOrder;
    protected JTextField tfType;
    protected JTextField tfBrand;
    protected JTextField tfColor;
    protected JTextField tfMaterial;
    protected JTextField tfPattern;
    protected JTextField tfSpecial;
    protected JTextField tfBarcode;

    protected JButton btnAdd;
    protected ItemService service;
    protected OrderService orderService;

    public AddItemDialog(JFrame parent, String title) {
        super(parent, title, true);

        service = new ItemService();
        orderService = new OrderService();

        setSize(400, 500);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(9, 2, 5, 5));

        cbOrder = new JComboBox<>();
        tfType = new JTextField();
        tfBrand = new JTextField();
        tfColor = new JTextField();
        tfMaterial = new JTextField();
        tfPattern = new JTextField();
        tfSpecial = new JTextField();
        tfBarcode = new JTextField();
        btnAdd = new JButton("Add");

        // Load orders into dropdown
        List<Order> orders = orderService.getAllOrders();
        for (Order o : orders) {
            cbOrder.addItem(o); // make sure Order.toString() shows Order ID + Customer Name
        }

        add(new JLabel("Order:"));
        add(cbOrder);
        add(new JLabel("Type:"));
        add(tfType);
        add(new JLabel("Brand:"));
        add(tfBrand);
        add(new JLabel("Color:"));
        add(tfColor);
        add(new JLabel("Material:"));
        add(tfMaterial);
        add(new JLabel("Pattern:"));
        add(tfPattern);
        add(new JLabel("Special Request:"));
        add(tfSpecial);
        add(new JLabel("Barcode:"));
        add(tfBarcode);
        add(new JLabel());
        add(btnAdd);

        btnAdd.addActionListener(e -> {
            Item item = new Item(
                    0,
                    ((Order) cbOrder.getSelectedItem()).getOrderId(),
                    tfType.getText(),
                    tfBrand.getText(),
                    tfColor.getText(),
                    tfMaterial.getText(),
                    tfPattern.getText(),
                    tfSpecial.getText(),
                    tfBarcode.getText()
            );

            boolean success = service.addItem(item);
            if (success) {
                JOptionPane.showMessageDialog(this, "Item added successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add item!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    protected void setItemData(Item item) {
        // Set fields for edit
        tfType.setText(item.getType());
        tfBrand.setText(item.getBrand());
        tfColor.setText(item.getColor());
        tfMaterial.setText(item.getMaterial());
        tfPattern.setText(item.getPattern());
        tfSpecial.setText(item.getSpecialRequest());
        tfBarcode.setText(item.getBarcode());

        // Select correct order
        for (int i = 0; i < cbOrder.getItemCount(); i++) {
            if (cbOrder.getItemAt(i).getOrderId() == item.getOrderID()) {
                cbOrder.setSelectedIndex(i);
                break;
            }
        }
    }
}
