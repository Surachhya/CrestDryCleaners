package ui.employee;

import models.Employee;
import models.Store;
import services.EmployeeService;

import javax.swing.*;
import java.awt.*;

public class EmployeeEditPanel extends JDialog {

    private JTextField txtName;
    private JComboBox<String> comboRole;
    private JTextField txtStoreId; // temporary until StoreService ready
    private JComboBox<Store> comboStore;
    private JTextField txtPhone;

    private JButton btnSave;
    private JButton btnCancel;

    private EmployeeService employeeService;
    private Employee employee; // the employee being edited

    public EmployeeEditPanel(JFrame parent, Employee employee) {
        super(parent, "Edit Employee", true);
        this.employee = employee;
        employeeService = new EmployeeService();

        initUI();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // ---------- FORM PANEL ----------
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Name:"));
        txtName = new JTextField(employee.getName());
        formPanel.add(txtName);

        formPanel.add(new JLabel("Role:"));
        comboRole = new JComboBox<>(new String[]{
                "Front Desk","Cleaner","Presser","Tailor","Delivery Driver",
                "Store Manager","Corporate Coordinator","Supervisor"
        });
        comboRole.setSelectedItem(employee.getRole());
        formPanel.add(comboRole);

        formPanel.add(new JLabel("Store ID:"));
        txtStoreId = new JTextField(employee.getStoreId() != null ? employee.getStoreId().toString() : "");
        formPanel.add(txtStoreId);

        formPanel.add(new JLabel("Phone:"));
        txtPhone = new JTextField(employee.getPhone());
        formPanel.add(txtPhone);

        add(formPanel, BorderLayout.CENTER);
        formPanel.add(new JLabel("Store:"));
        comboStore = new JComboBox<>();
        loadStores();
        if (employee.getStoreId() != null) {
            for (int i = 0; i < comboStore.getItemCount(); i++) {
                Store s = comboStore.getItemAt(i);
                if (s != null && s.getStoreId() == employee.getStoreId()) {
                    comboStore.setSelectedIndex(i);
                    break;
                }
            }
        }
        formPanel.add(comboStore);


        // ---------- BUTTON PANEL ----------
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
        btnPanel.add(btnSave);
        btnPanel.add(btnCancel);

        add(btnPanel, BorderLayout.SOUTH);

        // ---------- ACTIONS ----------
        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> updateEmployee());
    }
    private void loadStores() {
        comboStore.removeAllItems();
        comboStore.addItem(null);
        comboStore.addItem(new Store(1, "Downtown", null, null));
        comboStore.addItem(new Store(2, "Uptown", null, null));
        comboStore.addItem(new Store(3, "Airport", null, null));
    }

    private void updateEmployee() {
        Store selectedStore = (Store) comboStore.getSelectedItem();
        Integer storeId = selectedStore != null ? selectedStore.getStoreId() : null;
        employee.setStoreId(storeId);

        String name = txtName.getText().trim();
        String role = (String) comboRole.getSelectedItem();
        String storeText = txtStoreId.getText().trim();

        if (!storeText.isEmpty()) {
            try {
                storeId = Integer.parseInt(storeText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Store ID must be a number");
                return;
            }
        }

        String phone = txtPhone.getText().trim();

        if (name.isEmpty() || role == null) {
            JOptionPane.showMessageDialog(this, "Name and Role are required.");
            return;
        }

        employee.setName(name);
        employee.setRole(role);
        employee.setStoreId(storeId);
        employee.setPhone(phone);

        boolean success = employeeService.updateEmployee(employee);

        if (success) {
            JOptionPane.showMessageDialog(this, "Employee updated successfully!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error updating employee!");
        }
    }
}
