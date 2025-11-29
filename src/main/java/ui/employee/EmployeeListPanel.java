package ui.employee;

import models.Employee;
import models.Store;
import services.EmployeeService;
import services.StoreService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeListPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnRefresh;

    private EmployeeService employeeService;
    private StoreService storeService;

    private JFrame parent;

    public EmployeeListPanel(JFrame parent) {
        this.parent = parent;
        this.employeeService = new EmployeeService();
        this.storeService = new StoreService();

        initUI();
        loadEmployees();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        btnAdd = new JButton("Add Employee");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);
        topPanel.add(btnRefresh);

        add(topPanel, BorderLayout.NORTH);

        // ✅ TABLE WITH PADDING
        tableModel = new DefaultTableModel(
                new Object[]{"Employee ID", "Name", "Role", "Store", "Phone"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(scrollPane, BorderLayout.CENTER);

        // ✅ DOUBLE CLICK — RELIABLE VERSION
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(evt)) {
                    int row = table.rowAtPoint(evt.getPoint());
                    if (row >= 0) {
                        table.setRowSelectionInterval(row, row);
                        editSelectedEmployee();
                    }
                }
            }
        });

        attachListeners();
    }

    private void loadEmployees() {
        tableModel.setRowCount(0);

        // ✅ MASSIVE SPEED BOOST: CACHE STORES
        Map<Integer, String> storeMap = new HashMap<>();
        for (Store s : storeService.getAllStores()) {
            storeMap.put(s.getStoreId(), s.getLocation());
        }

        List<Employee> employees = employeeService.getAllEmployees();

        for (Employee e : employees) {
            String storeName = storeMap.getOrDefault(e.getStoreId(), "");

            tableModel.addRow(new Object[]{
                    e.getEmployeeId(),
                    e.getName(),
                    e.getRole(),
                    storeName,
                    e.getPhone()
            });
        }
    }

    private void attachListeners() {
        btnRefresh.addActionListener(e -> loadEmployees());
        btnDelete.addActionListener(e -> deleteSelectedEmployee());

        btnAdd.addActionListener(e -> {
            EmployeeAddPanel addDialog = new EmployeeAddPanel(parent);
            addDialog.setVisible(true);
            loadEmployees();
        });

        btnEdit.addActionListener(e -> editSelectedEmployee());
    }

    private void editSelectedEmployee() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(parent, "Please select an employee to edit.");
            return;
        }

        int employeeId = (int) tableModel.getValueAt(row, 0);
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employee != null) {
            EmployeeEditPanel editDialog = new EmployeeEditPanel(parent, employee);
            editDialog.setVisible(true);
            loadEmployees();
        }
    }

    private void deleteSelectedEmployee() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(parent, "Please select an employee.");
            return;
        }

        int employeeId = (int) tableModel.getValueAt(row, 0);

        int confirm = JOptionPane.showConfirmDialog(
                parent,
                "Are you sure you want to delete this employee?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            employeeService.deleteEmployee(employeeId);
            loadEmployees();
        }
    }
}