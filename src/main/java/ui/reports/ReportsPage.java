package ui.reports;
import ui.home.UIComponents;
import ui.reports.documents.*;

import javax.swing.*;
import java.awt.*;

public class ReportsPage extends JPanel {

    private JPanel leftMenu;
    private JPanel rightContent;
    private CardLayout cardLayout;

    public ReportsPage() {

        setLayout(new BorderLayout());

        leftMenu = new JPanel();
        leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.Y_AXIS));
        leftMenu.setPreferredSize(new Dimension(220, 0));
        leftMenu.setBackground(new Color(235, 235, 235));
        leftMenu.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JButton btnSales      = createMenuButton("Total Sales");
        JButton btnCustomers  = createMenuButton("Customers");
        JButton btnVans       = createMenuButton("Vans per Store");
        JButton btnEmployees  = createMenuButton("Employees per Store");
        JButton btnStatus     = createMenuButton("Orders by Status");

        addMenuItem(btnSales);
        addMenuItem(btnCustomers);
        addMenuItem(btnVans);
        addMenuItem(btnEmployees);
        addMenuItem(btnStatus);

        add(leftMenu, BorderLayout.WEST);

        cardLayout = new CardLayout();
        rightContent = new JPanel(cardLayout);

        rightContent.add(new ReportTotalSalesPanel(), "sales");
        rightContent.add(new ReportCustomersPanel(), "customers");
        rightContent.add(new ReportVansPanel(), "vans");
        rightContent.add(new ReportEmployeesPanel(), "employees");
        rightContent.add(new ReportStatusPanel(), "status");

        JPanel topRightBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        topRightBar.setBackground(Color.WHITE);

        JButton btnPrint = new JButton();

        ImageIcon printIcon = new ImageIcon(getClass().getResource("/print.jpg"));
        Image scaledImg = printIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        btnPrint.setIcon(new ImageIcon(scaledImg));

        btnPrint.setToolTipText("Print this report");
        btnPrint.setBorderPainted(false);
        btnPrint.setFocusPainted(false);
        btnPrint.setContentAreaFilled(false);
        btnPrint.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnPrint.addActionListener(e -> showPrintPreview());

        topRightBar.add(btnPrint);

        JPanel rightWrapper = new JPanel(new BorderLayout());
        rightWrapper.add(topRightBar, BorderLayout.NORTH);
        rightWrapper.add(rightContent, BorderLayout.CENTER);

        add(rightWrapper, BorderLayout.CENTER);

        btnSales.addActionListener(e -> cardLayout.show(rightContent, "sales"));
        btnCustomers.addActionListener(e -> cardLayout.show(rightContent, "customers"));
        btnVans.addActionListener(e -> cardLayout.show(rightContent, "vans"));
        btnEmployees.addActionListener(e -> cardLayout.show(rightContent, "employees"));
        btnStatus.addActionListener(e -> cardLayout.show(rightContent, "status"));
    }

    private void showPrintPreview() {

        Component current = null;
        for (Component c : rightContent.getComponents()) {
            if (c.isVisible()) {
                current = c;
                break;
            }
        }
        if (current == null) return;

        JPanel reportPanel = (JPanel) current;

        Image preview = reportPanel.createImage(reportPanel.getWidth(), reportPanel.getHeight());
        Graphics g = preview.getGraphics();
        reportPanel.paint(g);

        Image scaled = preview.getScaledInstance(
                reportPanel.getWidth() / 2,
                reportPanel.getHeight() / 2,
                Image.SCALE_SMOOTH
        );

        JLabel previewLabel = new JLabel(new ImageIcon(scaled));


        JDialog dlg = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Print Preview", true);
        dlg.setLayout(new BorderLayout());
        dlg.add(new JScrollPane(previewLabel), BorderLayout.CENTER);

        JButton btnPrintNow = UIComponents.createPrimaryButton("Print");
        btnPrintNow.addActionListener(e -> {
            ReportPrinter.printPanel(reportPanel, "Report");
            dlg.dispose();
        });

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(btnPrintNow);

        dlg.add(bottom, BorderLayout.SOUTH);
        dlg.setSize(600, 700);
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);
    }

    private void addMenuItem(JButton btn) {
        leftMenu.add(btn);
        leftMenu.add(Box.createVerticalStrut(5));

        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setForeground(new Color(200, 200, 200));

        leftMenu.add(sep);
        leftMenu.add(Box.createVerticalStrut(10));
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(70, 130, 180));
        btn.setFocusPainted(false);

        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(100, 149, 237));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(70, 130, 180));
            }
        });

        return btn;
    }
}