package ui.home;

import ui.corporateclient.CorporateClientListPanel;
import ui.customer.CustomerListPanel;
import ui.employee.EmployeeListPanel;
import ui.item.ItemListPanel;
import ui.order.OrderAddPanel;
import ui.order.OrderListPanel;
import ui.reports.ReportsPage;
import ui.settings.SettingsPage;
import ui.store.StoreListPanel;
import ui.van.VanListPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    private JPanel mainContainer;
    private CardLayout cardLayout;

    private StartupPanel startupPanel;
    private JPanel managePanel;
    private JPanel contentPanel;
    private MenuPanel menuPanel;

    private OrderAddPanel orderAddPanel;

    public MainFrame() {
        setTitle("Crest Dry Cleaners");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center window

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        startupPanel = new StartupPanel(this);
        mainContainer.add(new ReportsPage(), "ReportsPage");

        managePanel = new JPanel(new BorderLayout());

        menuPanel = new MenuPanel();
        contentPanel = new JPanel(new BorderLayout());

        TopBarPanel topBar = new TopBarPanel(cardLayout, mainContainer);
        mainContainer.add(new SettingsPage(topBar), "SettingsPage");
        add(topBar, BorderLayout.NORTH);

        // Wrap content panel with top panel
        JPanel contentWrapper = new JPanel(new BorderLayout());
        //contentWrapper.add(topPanel, BorderLayout.NORTH);
        contentWrapper.add(contentPanel, BorderLayout.CENTER);

        // Add menu + contentWrapper to managePanel
        managePanel.add(menuPanel, BorderLayout.WEST);
        managePanel.add(contentWrapper, BorderLayout.CENTER);

        menuPanel.btnCustomers.addActionListener(e -> showCustomers());
        menuPanel.btnEmployees.addActionListener(e -> showEmployees());
        menuPanel.btnStores.addActionListener(e -> showStores());
        menuPanel.btnOrders.addActionListener(e -> showOrders());
        menuPanel.btnCorporateClients.addActionListener(e -> showCorporateClient());
        menuPanel.btnVans.addActionListener(e -> showVans());
        menuPanel.btnItems.addActionListener(e -> showItems());


        orderAddPanel = new OrderAddPanel(this);


        mainContainer.add(startupPanel, "Startup");
        mainContainer.add(managePanel, "ManageOrders");

        add(mainContainer);

        // Show StartupPanel first
        cardLayout.show(mainContainer, "Startup");

        setVisible(true);
    }
    public void showManageOrders() {
        cardLayout.show(mainContainer, "ManageOrders");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startupPanel.btnStartOrder) {
            cardLayout.show(mainContainer, "StartOrder");
        } else if (e.getSource() == startupPanel.btnManageOrders) {
            cardLayout.show(mainContainer, "ManageOrders");
        }
    }

    private void showCustomers() {
        contentPanel.removeAll();
        contentPanel.add(new CustomerListPanel(this), BorderLayout.CENTER);
        refreshContent();
    }

    private void showEmployees() {
        contentPanel.removeAll();
        contentPanel.add(new EmployeeListPanel(this), BorderLayout.CENTER);
        refreshContent();
    }

    private void showStores() {
        contentPanel.removeAll();
        contentPanel.add(new StoreListPanel(this), BorderLayout.CENTER);
        refreshContent();
    }

    private void showOrders() {
        contentPanel.removeAll();
        contentPanel.add(new OrderListPanel(), BorderLayout.CENTER);
        refreshContent();
    }

    private void showCorporateClient() {
        contentPanel.removeAll();
        contentPanel.add(new CorporateClientListPanel(this), BorderLayout.CENTER);
        refreshContent();
    }

    private void showVans() {
        contentPanel.removeAll();
        contentPanel.add(new VanListPanel(this), BorderLayout.CENTER);
        refreshContent();
    }

    private void showItems() {
        contentPanel.removeAll();
        contentPanel.add(new ItemListPanel(this), BorderLayout.CENTER);
        refreshContent();
    }

    private void refreshContent() {
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
