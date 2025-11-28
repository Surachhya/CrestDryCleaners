package ui.store;

import models.Employee;
import models.Store;
import services.EmployeeService;
import services.StoreService;

import javax.swing.*;
import java.awt.*;

public class EditStoreDialog extends JDialog {

    private JTextField txtLocation;
    private JComboBox<Employee> comboManager;
    private JComboBox<Employee> comboSupervisor;

    private JButton btnSave;
    private JButton btnCancel;

    private StoreService storeService;
    private Store store; // the store being edited

    public EditStoreDialog(JFrame parent, Store store) {
        super(parent, "Edit Store", true);
        this.store = store;
        storeService = new StoreService();

        initUI();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initUI() {
        setLayout(new BorderLayout());

//        setSize(400, 400);
//        setLayout(new GridLayout(8, 2, 5, 5));

        // ---------- FORM PANEL ----------
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Location:"));
        txtLocation = new JTextField(store.getLocation());
        formPanel.add(txtLocation);

        formPanel.add(new JLabel("Manager:"));
        comboManager = new JComboBox<>();
        formPanel.add(comboManager);

        formPanel.add(new JLabel("Supervisor:"));
        comboSupervisor = new JComboBox<>();
        loadEmployees();
        formPanel.add(comboSupervisor);

        if (store.getManagerId() != null) {
            for (int i = 0; i < comboManager.getItemCount(); i++) {
                Employee emp = comboManager.getItemAt(i);
                if (emp != null && emp.getEmployeeId() == store.getManagerId()) {
                    comboManager.setSelectedIndex(i);
                    break;
                }
            }
        }

        if (store.getSupervisorId() != null) {
            for (int i = 0; i < comboSupervisor.getItemCount(); i++) {
                Employee emp = comboSupervisor.getItemAt(i);
                if (emp != null && emp.getEmployeeId() == store.getSupervisorId()) {
                    comboSupervisor.setSelectedIndex(i);
                    break;
                }
            }
        }



        add(formPanel, BorderLayout.CENTER);

        // ---------- BUTTON PANEL ----------
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
        btnPanel.add(btnSave);
        btnPanel.add(btnCancel);

        add(btnPanel, BorderLayout.SOUTH);

        // ---------- ACTIONS ----------
        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> updateStore());
    }

    private void loadEmployees() {
        comboManager.removeAllItems();
        comboSupervisor.removeAllItems();

        // Optional: empty selection
        comboManager.addItem(null);
        comboSupervisor.addItem(null);

        EmployeeService empService = new EmployeeService();
        for (Employee e : empService.getAllEmployees()) {
            comboManager.addItem(e);
            comboSupervisor.addItem(e);
        }
    }


    private void updateStore() {
        String location = txtLocation.getText().trim();
        Employee manager = (Employee) comboManager.getSelectedItem();
        Employee supervisor = (Employee) comboSupervisor.getSelectedItem();

        if (location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Location is required!");
            return;
        }

        Integer managerId = manager.getEmployeeId();
        Integer supervisorId = supervisor.getEmployeeId();

        store.setLocation(location);
        store.setManagerId(managerId);
        store.setSupervisorId(supervisorId);

        boolean success = storeService.updateStore(store);

        if (success) {
            JOptionPane.showMessageDialog(this, "Store updated successfully!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error updating store!");
        }
    }
}
