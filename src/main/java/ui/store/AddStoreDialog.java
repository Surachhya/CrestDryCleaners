package ui.store;

import models.Employee;
import models.Store;
import services.EmployeeService;
import services.StoreService;

import javax.swing.*;
import java.awt.*;

public class AddStoreDialog extends JDialog {

    private JTextField txtLocation;
    private JComboBox<Employee> comboManager;
    private JComboBox<Employee> comboSupervisor;

    private JButton btnSave;
    private JButton btnCancel;

    private StoreService storeService;

    public AddStoreDialog(JFrame parent) {
        super(parent, "Add Store", true);
        storeService = new StoreService();

        initUI();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // ---------- FORM PANEL ----------
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Location:"));
        txtLocation = new JTextField();
        formPanel.add(txtLocation);

        formPanel.add(new JLabel("Manager:"));
        comboManager = new JComboBox<>();
        formPanel.add(comboManager);

        formPanel.add(new JLabel("Supervisor:"));
        comboSupervisor = new JComboBox<>();
        loadEmployees();
        formPanel.add(comboSupervisor);


        add(formPanel, BorderLayout.CENTER);

        // ---------- BUTTONS ----------
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
        btnPanel.add(btnSave);
        btnPanel.add(btnCancel);

        add(btnPanel, BorderLayout.SOUTH);

        // ---------- ACTIONS ----------
        btnCancel.addActionListener(e -> dispose());

        btnSave.addActionListener(e -> saveStore());
    }

    private void saveStore() {
        String location = txtLocation.getText().trim();
        Employee manager = (Employee) comboManager.getSelectedItem();
        Employee supervisor = (Employee) comboSupervisor.getSelectedItem();



        if (location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Location is required!");
            return;
        }


        Store store = new Store();
        store.setLocation(location);
        store.setManagerId(manager.getEmployeeId());
        store.setSupervisorId(supervisor.getEmployeeId());

        boolean success = storeService.addStore(store);

        if (success) {
            JOptionPane.showMessageDialog(this, "Store added successfully!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error adding store!");
        }
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

}
