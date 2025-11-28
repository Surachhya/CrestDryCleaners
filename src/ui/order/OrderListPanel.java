package ui.order;

import models.Customer;
import models.Order;
import services.CustomerService;
import services.OrderService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OrderListPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    private JButton btnAdd, btnEdit, btnDelete, btnRefresh;

    private OrderService orderService;
    private CustomerService customerService;

    public OrderListPanel() {
        orderService = new OrderService();
        customerService = new CustomerService();

        initUI();
        loadOrders();
    }

    private void initUI() {

        setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAdd = new JButton("Add Order");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");

        top.add(btnAdd);
        top.add(btnEdit);
        top.add(btnDelete);
        top.add(btnRefresh);

        add(top, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[]{"Order ID", "Customer", "Order Date", "Due Date", "Pieces", "Status", "Amount"},
                0
        );

        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) editSelected();
            }
        });

        btnAdd.addActionListener(e -> {
            OrderAddPanel dlg = new OrderAddPanel((JFrame) SwingUtilities.getWindowAncestor(this));
            dlg.setVisible(true);
            loadOrders();
        });

        btnEdit.addActionListener(e -> editSelected());
        btnDelete.addActionListener(e -> deleteSelected());
        btnRefresh.addActionListener(e -> loadOrders());
    }

    private void loadOrders() {
        tableModel.setRowCount(0);

        List<Order> list = orderService.getAllOrders();

        for (Order o : list) {
            Customer cust = customerService.getCustomerById(o.getCustomerId());

            tableModel.addRow(new Object[]{
                    o.getOrderId(),
                    cust != null ? cust.getFullName() : "",
                    o.getOrderDate(),
                    o.getDueDate(),
                    o.getTotalPieces(),
                    o.getStatus(),
                    o.getTotalAmount()
            });
        }
    }

    private void editSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an order first!");
            return;
        }

        int id = (int) tableModel.getValueAt(row, 0);
        Order order = orderService.getOrderById(id);

        OrderEditPanel dlg = new OrderEditPanel((JFrame) SwingUtilities.getWindowAncestor(this), order);
        dlg.setVisible(true);
        loadOrders();
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an order!");
            return;
        }

        int id = (int) tableModel.getValueAt(row, 0);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete this order?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            orderService.deleteOrder(id);
            loadOrders();
        }
    }
}
