package ui.employee;

import models.Employee;
import services.EmployeeService;

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

    public EmployeeListPanel() {
        this.employeeService = new EmployeeService();
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

        // ---------- TABLE MODEL ----------
        tableModel = new DefaultTableModel(
                new Object[]{"Employee ID", "Name", "Role", "Store ID", "Phone"},
                0
        );

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        // Enable row double-click to open Edit dialog
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        int employeeId = (int) tableModel.getValueAt(row, 0);
                        Employee employee = employeeService.getEmployeeById(employeeId);

                        if (employee != null) {
                            EmployeeEditPanel editDialog = new EmployeeEditPanel(
                                    (JFrame) SwingUtilities.getWindowAncestor(EmployeeListPanel.this),
                                    employee
                            );
                            editDialog.setVisible(true);
                            loadEmployees(); // refresh table after edit
                        }
                    }
                }
            }
        });


        add(scrollPane, BorderLayout.CENTER);

        attachListeners();
    }

    private void loadEmployees() {
        tableModel.setRowCount(0); // clear previous data

        List<Employee> employees = employeeService.getAllEmployees();

        for (Employee e : employees) {
            tableModel.addRow(new Object[]{
                    e.getEmployeeId(),
                    e.getName(),
                    e.getRole(),
                    e.getStoreId(),
                    e.getPhone()
            });
        }
    }

    private void attachListeners() {
        // Refresh already wired
        btnRefresh.addActionListener(e -> loadEmployees());

        // Delete already wired
        btnDelete.addActionListener(e -> deleteSelectedEmployee());

        // ---------- Add Employee ----------
        btnAdd.addActionListener(e -> {
            EmployeeAddPanel addDialog = new EmployeeAddPanel(
                    (JFrame) SwingUtilities.getWindowAncestor(EmployeeListPanel.this)
            );
            addDialog.setVisible(true);
            loadEmployees(); // refresh table after add
        });

        // ---------- Edit Employee ----------
        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select an employee to edit!");
                return;
            }
            int employeeId = (int) tableModel.getValueAt(row, 0);
            Employee employee = employeeService.getEmployeeById(employeeId);

            if (employee != null) {
                EmployeeEditPanel editDialog = new EmployeeEditPanel(
                        (JFrame) SwingUtilities.getWindowAncestor(EmployeeListPanel.this),
                        employee
                );
                editDialog.setVisible(true);
                loadEmployees(); // refresh table after edit
            }
        });
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
