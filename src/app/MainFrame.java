package app;

import ui.MenuPanel;
import ui.CustomerListPanel;
import ui.employee.EmployeeListPanel;
import ui.employee.EmployeeEditPanel;
import models.Employee;
import ui.store.StoreListPanel;

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

        if (menuPanel.btnEmployees != null) {
            menuPanel.btnEmployees.addActionListener(e -> showEmployees());
        }
        menuPanel.btnStores.addActionListener(e -> showStores());

        setVisible(true);
    }

    private void showCustomers() {
        contentPanel.removeAll();
        contentPanel.add(new CustomerListPanel(), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showEmployees() {
        contentPanel.removeAll();
        EmployeeListPanel empPanel = new EmployeeListPanel();
        contentPanel.add(empPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    private void showStores() {
        contentPanel.removeAll();
        contentPanel.add(new StoreListPanel(), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
