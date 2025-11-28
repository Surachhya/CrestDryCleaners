package ui.van;

import models.Store;
import models.Van;
import services.StoreService;
import services.VanService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VanListPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private VanService vanService = new VanService();
    private StoreService storeService = new StoreService();
    private JTextField tfSearch;
    private JFrame parent;
    private List<Van> vanList;
    private Map<Integer, String> storeMap = new HashMap<>(); // Store_ID -> Store Name

    public VanListPanel(JFrame parent) {
        this.parent = parent;
        setLayout(new BorderLayout());

        // Load stores into a map for fast lookup
        for (Store s : storeService.getAllStores()) {
            storeMap.put(s.getStoreId(), s.getLocation());
        }

        JPanel topPanel = new JPanel();
        JButton btnAdd = new JButton("Add Van");
        JButton btnEdit = new JButton("Edit Van");
        JButton btnDelete = new JButton("Delete Van");
        tfSearch = new JTextField(20);
        JButton btnSearch = new JButton("Search");

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);
        topPanel.add(tfSearch);
        topPanel.add(btnSearch);
        add(topPanel, BorderLayout.NORTH);

        String[] columns = {"Van ID", "Store", "Plate Number", "Model", "Year", "Capacity"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadData();

        btnAdd.addActionListener(e -> {
            AddVanDialog dialog = new AddVanDialog(parent, "Add Van");
            dialog.setVisible(true);
            loadData();
        });
        btnEdit.addActionListener(e -> editVan());
        btnDelete.addActionListener(e -> deleteVan());
        btnSearch.addActionListener(e -> searchVan());

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) editVan();
            }
        });
    }

    private void loadData() {
        model.setRowCount(0);
        vanList = vanService.getAllVans();
        for (Van v : vanList) {
            model.addRow(new Object[]{
                    v.getVanID(),
                    storeMap.getOrDefault(v.getStoreID(), "Unknown"), // Show store name
                    v.getPlateNumber(),
                    v.getModel(),
                    v.getYear(),
                    v.getCapacity()
            });
        }
    }

    private void editVan() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Van van = vanList.get(row);
            EditVanDialog dialog = new EditVanDialog(parent, van);
            dialog.setVisible(true);
            loadData();
        } else {
            JOptionPane.showMessageDialog(parent, "Select a van to edit.");
        }
    }

    private void deleteVan() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Van van = vanList.get(row);
            int confirm = JOptionPane.showConfirmDialog(parent, "Delete this van?");
            if (confirm == JOptionPane.YES_OPTION) {
                vanService.deleteVan(van.getVanID());
                loadData();
            }
        } else {
            JOptionPane.showMessageDialog(parent, "Select a van to delete.");
        }
    }

    private void searchVan() {
        String keyword = tfSearch.getText().trim().toLowerCase();
        model.setRowCount(0);
        vanList = vanService.getAllVans();
        for (Van v : vanList) {
            String storeName = storeMap.getOrDefault(v.getStoreID(), "");
            if (storeName.toLowerCase().contains(keyword) ||
                    v.getPlateNumber().toLowerCase().contains(keyword) ||
                    v.getModel().toLowerCase().contains(keyword)) {

                model.addRow(new Object[]{
                        v.getVanID(),
                        storeName,
                        v.getPlateNumber(),
                        v.getModel(),
                        v.getYear(),
                        v.getCapacity()
                });
            }
        }
    }
}
