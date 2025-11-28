package ui.order;

import models.Customer;
import models.Order;
import services.CustomerService;
import services.OrderService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class OrderAddPanel extends JDialog {

    private JComboBox<Customer> comboCustomer;
    private JComboBox<String> comboStatus;

    private JTextField txtOrderDate;
    private JTextField txtDueDate;
    private JTextField txtPieces;
    private JTextField txtAmount;

    private JButton btnSave, btnCancel;

    private OrderService orderService;
    private CustomerService customerService;

    public OrderAddPanel(JFrame parent) {
        super(parent, "Add Order", true);

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

        form.add(new JLabel("Order Date (YYYY-MM-DD):"));
        txtOrderDate = new JTextField(LocalDate.now().toString());
        form.add(txtOrderDate);

        form.add(new JLabel("Due Date (YYYY-MM-DD):"));
        txtDueDate = new JTextField(LocalDate.now().plusDays(3).toString());
        form.add(txtDueDate);

        form.add(new JLabel("Total Pieces:"));
        txtPieces = new JTextField("1");
        form.add(txtPieces);

        form.add(new JLabel("Status:"));
        comboStatus = new JComboBox<>(new String[]{
                "Received", "Processing", "Ready", "Delivered", "Cancelled"
        });
        form.add(comboStatus);

        form.add(new JLabel("Amount:"));
        txtAmount = new JTextField("0.00");
        form.add(txtAmount);

        add(form, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
        buttons.add(btnSave);
        buttons.add(btnCancel);
        add(buttons, BorderLayout.SOUTH);

        loadCustomers();

        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> save());
    }

    private void loadCustomers() {
        comboCustomer.removeAllItems();
        for (Customer c : customerService.getAllCustomers()) {
            comboCustomer.addItem(c);
        }
    }

    private void save() {
        Customer customer = (Customer) comboCustomer.getSelectedItem();
        if (customer == null) {
            JOptionPane.showMessageDialog(this, "Select customer!");
            return;
        }

        int pieces;
        double amount;

        try {
            pieces = Integer.parseInt(txtPieces.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid pieces!");
            return;
        }

        try {
            amount = Double.parseDouble(txtAmount.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid amount!");
            return;
        }

        Order o = new Order();
        o.setCustomerId(customer.getCustomerId());
        o.setOrderDate(LocalDate.parse(txtOrderDate.getText().trim()));
        o.setDueDate(LocalDate.parse(txtDueDate.getText().trim()));
        o.setTotalPieces(pieces);
        o.setStatus((String) comboStatus.getSelectedItem());
        o.setTotalAmount(amount);

        if (orderService.addOrder(o)) {
            JOptionPane.showMessageDialog(this, "Order added!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error saving order!");
        }
    }
}
