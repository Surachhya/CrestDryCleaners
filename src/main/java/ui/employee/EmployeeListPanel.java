package ui.employee;

import models.Employee;
import models.Store;
import services.EmployeeService;
import services.StoreService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployeeListPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnRefresh;

    private EmployeeService employeeService;
    private StoreService storeService;

    public EmployeeListPanel() {
        this.employeeService = new EmployeeService();
        this.storeService = new StoreService();
        initUI();
        loadEmployees();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // ---------- TOP BAR ----------
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAdd = new JButton("Add Employee");
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
                new Object[]{"Employee ID", "Name", "Role", "Store", "Phone"}, 0
        );
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Double-click to edit
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    editSelectedEmployee();
                }
            }
        });

        attachListeners();
    }

    private void loadEmployees() {
        tableModel.setRowCount(0); // clear previous data
        List<Employee> employees = employeeService.getAllEmployees();

        for (Employee e : employees) {
            String storeName = "";
            if (e.getStoreId() != null) {
                Store store = storeService.getStoreById(e.getStoreId());
                if (store != null) storeName = store.getLocation();
            }
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
            EmployeeAddPanel addDialog = new EmployeeAddPanel(
                    (JFrame) SwingUtilities.getWindowAncestor(EmployeeListPanel.this)
            );
            addDialog.setVisible(true);
            loadEmployees();
        });

        btnEdit.addActionListener(e -> editSelectedEmployee());
    }

    private void editSelectedEmployee() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to edit!");
            return;
        }
        int employeeId = (int) tableModel.getValueAt(row, 0);
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employee != null) {
            EmployeeEditPanel editDialog = new EmployeeEditPanel(
                    (JFrame) SwingUtilities.getWindowAncestor(this),
                    employee
            );
            editDialog.setVisible(true);
            loadEmployees();
        }
    }

    private void deleteSelectedEmployee() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee!");
            return;
        }
        int employeeId = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(
                this,
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
