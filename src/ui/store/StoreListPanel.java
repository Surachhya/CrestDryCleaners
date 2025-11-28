package ui.store;

import models.Store;
import services.StoreService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StoreListPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnRefresh;
    private JFrame parent;

    private StoreService storeService;

    public StoreListPanel(JFrame parent)
    {
        this.parent = parent;
        this.storeService = new StoreService();
        initUI();
        loadStores();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // ---------- TOP BUTTON PANEL ----------
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAdd = new JButton("Add Store");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);
        topPanel.add(btnRefresh);

        add(topPanel, BorderLayout.NORTH);

        // ---------- TABLE ----------
        tableModel = new DefaultTableModel(
                new Object[]{"Store ID", "Location", "Manager ID", "Supervisor ID"}, 0
        );
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Row double-click → Edit
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        int storeId = (int) tableModel.getValueAt(row, 0);
                        Store store = storeService.getStoreById(storeId);
                        if (store != null) {
                            EditStoreDialog editDialog = new EditStoreDialog(
                                    (JFrame) SwingUtilities.getWindowAncestor(parent),
                                    store
                            );
                            editDialog.setVisible(true);
                            loadStores();
                        }
                    }
                }
            }
        });

        add(scrollPane, BorderLayout.CENTER);

        attachListeners();
    }

    private void loadStores() {
        tableModel.setRowCount(0);

        List<Store> stores = storeService.getAllStores();
        for (Store s : stores) {
            tableModel.addRow(new Object[]{
                    s.getStoreId(),
                    s.getLocation(),
                    s.getManagerId(),
                    s.getSupervisorId()
            });
        }
    }

    private void attachListeners() {
        btnRefresh.addActionListener(e -> loadStores());
        btnDelete.addActionListener(e -> deleteSelectedStore());

        btnAdd.addActionListener(e -> {
            AddStoreDialog addDialog = new AddStoreDialog(
                    (JFrame) SwingUtilities.getWindowAncestor(parent)
            );
            addDialog.setVisible(true);
            loadStores();
        });

        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a store to edit!");
                return;
            }
            int storeId = (int) tableModel.getValueAt(row, 0);
            Store store = storeService.getStoreById(storeId);
            if (store != null) {
                EditStoreDialog editDialog = new EditStoreDialog(
                        (JFrame) SwingUtilities.getWindowAncestor(parent),
                        store
                );
                editDialog.setVisible(true);
                loadStores();
            }
        });
    }

    private void deleteSelectedStore() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a store!");
            return;
        }
        int storeId = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this store?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            storeService.deleteStore(storeId);
            loadStores();
        }
    }
}
