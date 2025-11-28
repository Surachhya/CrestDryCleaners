package ui.order;

import models.Customer;
import models.Order;
import services.CustomerService;
import services.OrderService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class OrderEditPanel extends JDialog {

    private JComboBox<Customer> comboCustomer;
    private JComboBox<String> comboStatus;

    private JTextField txtOrderDate;
    private JTextField txtDueDate;
    private JTextField txtPieces;
    private JTextField txtAmount;

    private JButton btnSave, btnCancel;

    private OrderService orderService;
    private CustomerService customerService;

    private Order order;

    public OrderEditPanel(JFrame parent, Order order) {
        super(parent, "Edit Order", true);

        this.order = order;

        orderService = new OrderService();
        customerService = new CustomerService();

        initUI();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initUI() {

        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        form.add(new JLabel("Customer:"));
        comboCustomer = new JComboBox<>();
        form.add(comboCustomer);

        form.add(new JLabel("Order Date:"));
        txtOrderDate = new JTextField(order.getOrderDate().toString());
        form.add(txtOrderDate);

        form.add(new JLabel("Due Date:"));
        txtDueDate = new JTextField(order.getDueDate().toString());
        form.add(txtDueDate);

        form.add(new JLabel("Total Pieces:"));
        txtPieces = new JTextField(String.valueOf(order.getTotalPieces()));
        form.add(txtPieces);

        form.add(new JLabel("Status:"));
        comboStatus = new JComboBox<>(new String[]{
                "Received", "Processing", "Ready", "Delivered", "Cancelled"
        });
        form.add(comboStatus);

        form.add(new JLabel("Amount:"));
        txtAmount = new JTextField(String.valueOf(order.getTotalAmount()));
        form.add(txtAmount);

        add(form, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
        buttons.add(btnSave);
        buttons.add(btnCancel);
        add(buttons, BorderLayout.SOUTH);

        loadCustomers();
        comboStatus.setSelectedItem(order.getStatus());
        selectCustomer(order.getCustomerId());

        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> update());
    }

    private void loadCustomers() {
        comboCustomer.removeAllItems();
        for (Customer c : customerService.getAllCustomers())
            comboCustomer.addItem(c);
    }

    private void selectCustomer(int id) {
        for (int i = 0; i < comboCustomer.getItemCount(); i++) {
            if (comboCustomer.getItemAt(i).getCustomerId() == id) {
                comboCustomer.setSelectedIndex(i);
                break;
            }
        }
    }

    private void update() {

        Customer customer = (Customer) comboCustomer.getSelectedItem();

        int pieces;
        double amount;

        try {
            pieces = Integer.parseInt(txtPieces.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid pieces!");
            return;
        }

        try {
            amount = Double.parseDouble(txtAmount.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid amount!");
            return;
        }

        order.setCustomerId(customer.getCustomerId());
        order.setOrderDate(LocalDate.parse(txtOrderDate.getText().trim()));
        order.setDueDate(LocalDate.parse(txtDueDate.getText().trim()));
        order.setTotalPieces(pieces);
        order.setStatus((String) comboStatus.getSelectedItem());
        order.setTotalAmount(amount);

        boolean ok = orderService.updateOrder(order);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Order updated!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error updating order!");
        }
    }
}
