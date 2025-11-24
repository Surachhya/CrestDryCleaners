package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    public JButton btnCustomers;

    public MenuPanel() {
        setLayout(new GridLayout(10, 1));
        setPreferredSize(new Dimension(150, 400));

        btnCustomers = new JButton("Customers");

        add(btnCustomers);
    }
}
