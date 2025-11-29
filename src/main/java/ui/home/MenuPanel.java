package ui.home;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public JButton btnCustomers;
    public JButton btnEmployees;
    public JButton btnStores;
    public JButton btnOrders;
    public JButton btnCorporateClients;
    public JButton btnVans;
    public JButton btnItems;

    public MenuPanel() {
        setLayout(new GridLayout(10, 1, 5, 5)); // spacing between buttons
        setPreferredSize(new Dimension(160, 0)); // fixed width
        setBackground(new Color(230, 230, 230)); // light gray panel

        btnCustomers = createButton("Customers");
        btnEmployees = createButton("Employees");
        btnStores = createButton("Stores");
        btnOrders = createButton("Orders");
        btnCorporateClients = createButton("Corporate Clients");
        btnVans = createButton("Vans");
        btnItems = createButton("Order Items");

        add(btnCustomers);
        add(btnEmployees);
        add(btnStores);
        add(btnOrders);
        add(btnCorporateClients);
        add(btnVans);
        add(btnItems);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(70, 130, 180)); // steel blue
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        // Hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(100, 149, 237)); // lighter blue
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(70, 130, 180));
            }
        });

        return btn;
    }
}
