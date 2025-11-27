package ui.employee;

import models.Employee;
import models.Store;
import services.EmployeeService;
import services.StoreService;

import javax.swing.*;
import java.awt.*;

public class EmployeeEditPanel extends JDialog {

    private JTextField txtName;
    private JComboBox<String> comboRole;
    private JComboBox<Store> comboStore;
    private JTextField txtPhone;

    private JButton btnSave;
    private JButton btnCancel;

    private EmployeeService employeeService;
    private StoreService storeService;
    private Employee employee;

    public EmployeeEditPanel(JFrame parent, Employee employee) {
        super(parent, "Edit Employee", true);
        this.employee = employee;

        employeeService = new EmployeeService();
        storeService = new StoreService();

        initUI();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initUI() {
        setLayout(new BorderLayout());

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

        formPanel.add(new JLabel("Store:"));
        comboStore = new JComboBox<>();
        formPanel.add(comboStore);

        formPanel.add(new JLabel("Phone:"));
        txtPhone = new JTextField(employee.getPhone());
        formPanel.add(txtPhone);

        add(formPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
        btnPanel.add(btnSave);
        btnPanel.add(btnCancel);
        add(btnPanel, BorderLayout.SOUTH);

        loadStores();

        // Preselect current store
        if (employee.getStoreId() != null) {
            for (int i = 0; i < comboStore.getItemCount(); i++) {
                Store s = comboStore.getItemAt(i);
                if (s != null && s.getStoreId() == employee.getStoreId()) {
                    comboStore.setSelectedIndex(i);
                    break;
                }
            }
        }

        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> updateEmployee());
    }

    private void loadStores() {
        comboStore.removeAllItems();
        comboStore.addItem(null);
        for (Store s : storeService.getAllStores()) {
            comboStore.addItem(s);
        }
    }

    private void updateEmployee() {
        String name = txtName.getText().trim();
        String role = (String) comboRole.getSelectedItem();
        Store store = (Store) comboStore.getSelectedItem();
        String phone = txtPhone.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required!");
            return;
        }

        employee.setName(name);
        employee.setRole(role);
        employee.setStoreId(store != null ? store.getStoreId() : null);
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
