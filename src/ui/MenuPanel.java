package ui;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public JButton btnCustomers;
    public JButton btnEmployees;
    public JButton btnStores = new JButton("Stores");


    public MenuPanel() {
        setLayout(new GridLayout(10, 1));
        setPreferredSize(new Dimension(150, 400));

        btnCustomers = new JButton("Customers");
        add(btnCustomers);
        btnEmployees = new JButton("Employees");
        add(btnEmployees);
        add(btnStores);
    }
}
