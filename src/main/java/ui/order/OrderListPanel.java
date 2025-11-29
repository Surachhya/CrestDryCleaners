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

    private List<Order> orderList;   // mirror vanList pattern

    public OrderListPanel() {
        orderService = new OrderService();
        customerService = new CustomerService();

        initUI();
        loadOrders();
    }

    private void initUI() {

        setLayout(new BorderLayout());

        // Top button bar
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
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
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);


        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editOrder();
                }
            }
        });


        btnAdd.addActionListener(e -> {
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
            OrderAddPanel dlg = new OrderAddPanel(parent);
            dlg.setVisible(true);
            loadOrders();
        });

        btnEdit.addActionListener(e -> editOrder());
        btnDelete.addActionListener(e -> deleteSelected());
        btnRefresh.addActionListener(e -> loadOrders());
    }

    private void loadOrders() {
        tableModel.setRowCount(0);

        orderList = orderService.getAllOrders();

        for (Order o : orderList) {
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

    private void editOrder() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select an order to edit.");
            return;
        }

        Order order = orderList.get(row);

        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        OrderEditPanel dlg = new OrderEditPanel(parent, order);
        dlg.setVisible(true);
        loadOrders();
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select an order!");
            return;
        }

        Order order = orderList.get(row);
        int id = order.getOrderId();

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