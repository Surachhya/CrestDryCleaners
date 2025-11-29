package ui.home;

import ui.corporateclient.CorporateClientListPanel;
import ui.customer.CustomerListPanel;
import ui.employee.EmployeeListPanel;
import ui.item.ItemListPanel;
import ui.order.OrderAddPanel;
import ui.order.OrderListPanel;
import ui.store.StoreListPanel;
import ui.van.VanListPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    private JPanel mainContainer;   // CardLayout container
    private CardLayout cardLayout;

    private StartupPanel startupPanel;
    private JPanel managePanel;     // Existing menu + content layout
    private JPanel contentPanel;
    private MenuPanel menuPanel;

    private OrderAddPanel orderAddPanel; // Direct order add panel

    public MainFrame() {
        setTitle("Crest Dry Cleaners");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center window

        // -------------------------
        // CardLayout container
        // -------------------------
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // -------------------------
        // Startup Panel
        // -------------------------
        startupPanel = new StartupPanel(this);

        // -------------------------
        // Manage Orders Panel
        // -------------------------
        managePanel = new JPanel(new BorderLayout());

        menuPanel = new MenuPanel();
        contentPanel = new JPanel(new BorderLayout());

        // -------------------------
        // Home Button at top
        // -------------------------
        TopBarPanel topBar = new TopBarPanel(cardLayout, mainContainer);
        add(topBar, BorderLayout.NORTH);

        // Wrap content panel with top panel
        JPanel contentWrapper = new JPanel(new BorderLayout());
        //contentWrapper.add(topPanel, BorderLayout.NORTH);
        contentWrapper.add(contentPanel, BorderLayout.CENTER);

        // Add menu + contentWrapper to managePanel
        managePanel.add(menuPanel, BorderLayout.WEST);
        managePanel.add(contentWrapper, BorderLayout.CENTER);

        // -------------------------
        // Menu button actions
        // -------------------------
        menuPanel.btnCustomers.addActionListener(e -> showCustomers());
        menuPanel.btnEmployees.addActionListener(e -> showEmployees());
        menuPanel.btnStores.addActionListener(e -> showStores());
        menuPanel.btnOrders.addActionListener(e -> showOrders());
        menuPanel.btnCorporateClients.addActionListener(e -> showCorporateClient());
        menuPanel.btnVans.addActionListener(e -> showVans());
        menuPanel.btnItems.addActionListener(e -> showItems());

        // -------------------------
        // Start Order Panel
        // -------------------------
        orderAddPanel = new OrderAddPanel(this);

        // -------------------------
        // Add panels to CardLayout
        // -------------------------
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

    // -------------------------
    // MenuPanel show methods
    // -------------------------
    private void showCustomers() {
        contentPanel.removeAll();
        contentPanel.add(new CustomerListPanel(), BorderLayout.CENTER);
        refreshContent();
    }

    private void showEmployees() {
        contentPanel.removeAll();
        contentPanel.add(new EmployeeListPanel(), BorderLayout.CENTER);
        contentPanel.add(new EmployeeListPanel(), BorderLayout.CENTER);
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
