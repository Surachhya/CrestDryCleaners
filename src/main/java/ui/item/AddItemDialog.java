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

        setSize(300, 400);
        setLocationRelativeTo(parent);

        // Main container with padding
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        cbOrder = new JComboBox<>();
        tfType = new JTextField();
        tfBrand = new JTextField();
        tfColor = new JTextField();
        tfMaterial = new JTextField();
        tfPattern = new JTextField();
        tfSpecial = new JTextField();
        tfBarcode = new JTextField();
        btnAdd = new JButton("Add");

        // Load orders
        for (Order o : orderService.getAllOrders()) {
            cbOrder.addItem(o);
        }

        // Helper to add label + field with spacing
        form.add(row("Order:", cbOrder));
        form.add(row("Type:", tfType));
        form.add(row("Brand:", tfBrand));
        form.add(row("Color:", tfColor));
        form.add(row("Material:", tfMaterial));
        form.add(row("Pattern:", tfPattern));
        form.add(row("Special Request:", tfSpecial));
        form.add(row("Barcode:", tfBarcode));

        // Add button centered
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAdd);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        form.add(btnPanel);

        add(form);

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

            if (service.addItem(item)) {
                JOptionPane.showMessageDialog(this, "Item added successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add item!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Helper method
    private JPanel row(String label, JComponent field) {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.add(new JLabel(label), BorderLayout.WEST);
        p.add(field, BorderLayout.CENTER);
        p.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        return p;
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
