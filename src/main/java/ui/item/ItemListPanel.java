package ui.item;

import models.Item;
import models.Order;
import services.ItemService;
import services.OrderService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ItemListPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private ItemService service = new ItemService();
    private OrderService orderService = new OrderService();
    private JTextField tfSearch;
    private JFrame parent;
    private List<Item> itemList;
    private List<Order> orderList;

    public ItemListPanel(JFrame parent) {
        this.parent = parent;
        setLayout(new BorderLayout());

        orderList = orderService.getAllOrders();

        JPanel topPanel = new JPanel();
        JButton btnAdd = new JButton("Add Item");
        JButton btnEdit = new JButton("Edit Item");
        JButton btnDelete = new JButton("Delete Item");
        tfSearch = new JTextField(20);
        JButton btnSearch = new JButton("Search");

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);
        topPanel.add(tfSearch);
        topPanel.add(btnSearch);
        add(topPanel, BorderLayout.NORTH);

        String[] columns = {"Item ID", "Order", "Type", "Brand", "Color", "Material", "Pattern", "SpecialRequest", "Barcode"};
        model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadData();

        btnAdd.addActionListener(e -> {
            AddItemDialog dialog = new AddItemDialog(parent, "Add Item");
            dialog.setVisible(true);
            loadData();
        });
        btnEdit.addActionListener(e -> editItem());
        btnDelete.addActionListener(e -> deleteItem());
        btnSearch.addActionListener(e -> searchItem());

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) editItem();
            }
        });
    }

    private void loadData() {
        model.setRowCount(0);
        itemList = service.getAllItems();
        for (Item item : itemList) {
            Order o = orderList.stream().filter(order -> order.getOrderId() == item.getOrderID()).findFirst().orElse(null);
            String orderLabel = o != null ? "Order #: " + o.getOrderId() + " (" + o.getCustomerName() + ")" : "Unknown";

            model.addRow(new Object[]{
                    item.getItemID(),
                    orderLabel,
                    item.getType(),
                    item.getBrand(),
                    item.getColor(),
                    item.getMaterial(),
                    item.getPattern(),
                    item.getSpecialRequest(),
                    item.getBarcode()
            });
        }
    }

    private void editItem() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Item item = itemList.get(row);
            EditItemDialog dialog = new EditItemDialog(parent, item);
            dialog.setVisible(true);
            loadData();
        } else {
            JOptionPane.showMessageDialog(parent, "Select an item to edit.");
        }
    }

    private void deleteItem() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Item item = itemList.get(row);
            int confirm = JOptionPane.showConfirmDialog(parent, "Delete this item?");
            if (confirm == JOptionPane.YES_OPTION) {
                service.deleteItem(item.getItemID());
                loadData();
            }
        } else {
            JOptionPane.showMessageDialog(parent, "Select an item to delete.");
        }
    }

    private void searchItem() {
        String keyword = tfSearch.getText().trim().toLowerCase();
        model.setRowCount(0);
        itemList = service.searchItems(keyword);
        for (Item item : itemList) {
            Order o = orderList.stream().filter(order -> order.getOrderId() == item.getOrderID()).findFirst().orElse(null);
            String orderLabel = o != null ? "Order " + o.getOrderId() + " (" + o.getCustomerName() + ")" : "Unknown";

            model.addRow(new Object[]{
                    item.getItemID(),
                    orderLabel,
                    item.getType(),
                    item.getBrand(),
                    item.getColor(),
                    item.getMaterial(),
                    item.getPattern(),
                    item.getSpecialRequest(),
                    item.getBarcode()
            });
        }
    }
}
