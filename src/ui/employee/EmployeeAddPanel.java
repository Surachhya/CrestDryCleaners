package ui.employee;

import models.Employee;
import services.EmployeeService;
//import services.StoreService;
import models.Store;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EmployeeAddPanel extends JDialog {

    private JTextField txtName;
    private JComboBox<String> comboRole;
    private JComboBox<Store> comboStore;
    private JTextField txtPhone;

    private JButton btnSave;
    private JButton btnCancel;

    private EmployeeService employeeService;
    //private StoreService storeService;

    public EmployeeAddPanel(JFrame parent) {
        super(parent, "Add Employee", true);
        employeeService = new EmployeeService();
      //  storeService = new StoreService();

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
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Role:"));
        comboRole = new JComboBox<>(new String[]{
                "Front Desk","Cleaner","Presser","Tailor","Delivery Driver",
                "Store Manager","Corporate Coordinator","Supervisor"
        });
        formPanel.add(comboRole);

        formPanel.add(new JLabel("Store:"));
        comboStore = new JComboBox<>();
        loadStores();
        formPanel.add(comboStore);

        formPanel.add(new JLabel("Phone:"));
        txtPhone = new JTextField();
        formPanel.add(txtPhone);

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

        btnSave.addActionListener(e -> saveEmployee());
    }

    private void loadStores() {
        comboStore.removeAllItems();
//        List<Store> stores = storeService.getAllStores();
//        comboStore.addItem(null); // optional: allow no store
//        for (Store s : stores) {
//            comboStore.addItem(s);
//        }
        // Temporary static list of stores for testing
        comboStore.addItem(null); // optional "no store"
        comboStore.addItem(new Store(1, "Downtown", null, null));
        comboStore.addItem(new Store(2, "Uptown", null, null));
        comboStore.addItem(new Store(3, "Airport", null, null));
    }

    private void saveEmployee() {
        String name = txtName.getText().trim();
        String role = (String) comboRole.getSelectedItem();
        Store selectedStore = (Store) comboStore.getSelectedItem();
        Integer storeId = selectedStore != null ? selectedStore.getStoreId() : null;
        String phone = txtPhone.getText().trim();

        if (name.isEmpty() || role == null) {
            JOptionPane.showMessageDialog(this, "Name and Role are required.");
            return;
        }

        Employee e = new Employee();
        e.setName(name);
        e.setRole(role);
        e.setStoreId(storeId);
        e.setPhone(phone);

        boolean success = employeeService.addEmployee(e);

        if (success) {
            JOptionPane.showMessageDialog(this, "Employee added successfully!");
            dispose(); // close dialog
        } else {
            JOptionPane.showMessageDialog(this, "Error adding employee!");
        }
    }

}
