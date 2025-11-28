package app;

import ui.MenuPanel;
import ui.corporateclient.CorporateClientListPanel;
import ui.customer.CustomerListPanel;
import ui.employee.EmployeeListPanel;
import ui.item.ItemListPanel;
import ui.order.OrderListPanel;
import ui.store.StoreListPanel;
import ui.van.VanListPanel;

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
        menuPanel.btnOrders.addActionListener(e -> showOrders());
        menuPanel.btnCorporateClients.addActionListener(e -> showCorporateClient());
        menuPanel.btnVans.addActionListener(e -> showVans());
        menuPanel.btnItems.addActionListener(e -> showItems());


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
        contentPanel.add(new StoreListPanel(this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showOrders() {
        contentPanel.removeAll();
        contentPanel.add(new OrderListPanel(), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showCorporateClient() {
        contentPanel.removeAll();
        contentPanel.add(new CorporateClientListPanel(this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showVans() {
        contentPanel.removeAll();
        contentPanel.add(new VanListPanel(this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showItems() {
        contentPanel.removeAll();
        contentPanel.add(new ItemListPanel(this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

}
