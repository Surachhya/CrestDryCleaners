package ui.corporateclient;

import models.CorporateClient;
import services.CorporateClientService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CorporateClientListPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private CorporateClientService service = new CorporateClientService();
    private JTextField tfSearch;
    private JFrame parent;
    private List<CorporateClient> clientList;

    public CorporateClientListPanel(JFrame parent) {
        this.parent = parent; // store the frame for dialogs
        setLayout(new BorderLayout());

        // Top panel with buttons + search
        JPanel topPanel = new JPanel();
        JButton btnAdd = new JButton("Add Client");
        JButton btnEdit = new JButton("Edit Client");
        JButton btnDelete = new JButton("Delete Client");
        tfSearch = new JTextField(20);
        JButton btnSearch = new JButton("Search");

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);
        topPanel.add(tfSearch);
        topPanel.add(btnSearch);
        add(topPanel, BorderLayout.NORTH);

        // Table setup
        String[] columns = {"Corporate ID", "Company Name", "Contact Person", "Discount Rate (%)"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // make table read-only
            }
        };
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // single row selection
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Load initial data
        loadData();

        // Button actions
        btnAdd.addActionListener(e -> {
            AddCorporateClientDialog dialog = new AddCorporateClientDialog(parent, "Add Corporate Client");
            dialog.setVisible(true);
            loadData();
        });

        btnEdit.addActionListener(e -> editClient());
        btnDelete.addActionListener(e -> deleteClient());
        btnSearch.addActionListener(e -> searchClient());

        // Optional: double-click to edit
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    editClient();
                }
            }
        });
    }

    private void loadData() {
        model.setRowCount(0);
        clientList = service.getAllClients(); // store list for edit/delete
        for (CorporateClient c : clientList) {
            model.addRow(new Object[]{
                    c.getCorporateID(),
                    c.getCompanyName(),
                    c.getContactPerson(),
                    String.format("%.2f", c.getDiscountRate())
            });
        }
    }

    private void editClient() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            CorporateClient client = clientList.get(row); // get client directly
            EditCorporateClientDialog dialog = new EditCorporateClientDialog(parent, client);
            dialog.setVisible(true);
            loadData();
        } else {
            JOptionPane.showMessageDialog(parent, "Select a client to edit.");
        }
    }

    private void deleteClient() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            CorporateClient client = clientList.get(row);
            int confirm = JOptionPane.showConfirmDialog(parent, "Delete this client?");
            if (confirm == JOptionPane.YES_OPTION) {
                service.deleteCorporateClient(client.getCorporateID());
                loadData();
            }
        } else {
            JOptionPane.showMessageDialog(parent, "Select a client to delete.");
        }
    }

    private void searchClient() {
        String keyword = tfSearch.getText().trim();
        model.setRowCount(0);
        clientList = service.searchClients(keyword); // use service search
        for (CorporateClient c : clientList) {
            model.addRow(new Object[]{
                    c.getCorporateID(),
                    c.getCompanyName(),
                    c.getContactPerson(),
                    String.format("%.2f", c.getDiscountRate())
            });
        }
    }
}
