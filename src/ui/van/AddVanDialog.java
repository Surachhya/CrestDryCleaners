package ui.van;

import models.Store;
import models.Van;
import services.StoreService;
import services.VanService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddVanDialog extends JDialog {

    protected JComboBox<Store> cbStore;
    protected JTextField tfPlate;
    protected JTextField tfModel;
    protected JTextField tfYear;
    protected JTextField tfCapacity;

    protected JButton btnAdd;
    protected VanService service = new VanService();
    protected StoreService storeService = new StoreService();

    public AddVanDialog(JFrame parent, String title) {
        super(parent, title, true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2, 5, 5));

        cbStore = new JComboBox<>();
        tfPlate = new JTextField();
        tfModel = new JTextField();
        tfYear = new JTextField();
        tfCapacity = new JTextField();
        btnAdd = new JButton("Add");

        add(new JLabel("Store:"));
        add(cbStore);
        add(new JLabel("Plate Number:"));
        add(tfPlate);
        add(new JLabel("Model:"));
        add(tfModel);
        add(new JLabel("Year:"));
        add(tfYear);
        add(new JLabel("Capacity:"));
        add(tfCapacity);
        add(new JLabel());
        add(btnAdd);

        // Load stores
        List<Store> stores = storeService.getAllStores();
        for (Store s : stores) cbStore.addItem(s);

        btnAdd.addActionListener(e -> {
            Van van = new Van(
                    0,
                    ((Store) cbStore.getSelectedItem()).getStoreId(),
                    tfPlate.getText(),
                    tfModel.getText(),
                    Integer.parseInt(tfYear.getText()),
                    Integer.parseInt(tfCapacity.getText())
            );
            boolean success = service.addVan(van);
            if (success) {
                JOptionPane.showMessageDialog(this, "Van added!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add van!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    protected void setVanData(Van van) {
        cbStore.setSelectedItem(storeService.getAllStores().stream()
                .filter(s -> s.getStoreId() == van.getStoreID())
                .findFirst().orElse(null));
        tfPlate.setText(van.getPlateNumber());
        tfModel.setText(van.getModel());
        tfYear.setText(String.valueOf(van.getYear()));
        tfCapacity.setText(String.valueOf(van.getCapacity()));
    }
}
