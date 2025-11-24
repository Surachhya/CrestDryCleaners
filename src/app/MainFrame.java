package app;

import ui.MenuPanel;
import ui.CustomerListPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private MenuPanel menuPanel;
    private JPanel contentPanel;

    public MainFrame() {
        setTitle("Crest Dry Cleaners");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        menuPanel = new MenuPanel();
        contentPanel = new JPanel(new BorderLayout());

        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        menuPanel.btnCustomers.addActionListener(e -> showCustomers());

        setVisible(true);
    }

    private void showCustomers() {
        contentPanel.removeAll();
        contentPanel.add(new CustomerListPanel(), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
