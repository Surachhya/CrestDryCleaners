package ui.home;

import javax.swing.*;
import java.awt.*;
import java.util.prefs.Preferences;

public class TopBarPanel extends JPanel {

    public JButton btnHome;
    public JButton btnReports;
    public JButton btnSettings;

    private JLabel lblUser;
    private Preferences prefs;

    public TopBarPanel(CardLayout cardLayout, JPanel mainContainer) {

        prefs = Preferences.userNodeForPackage(MainFrame.class);
        String username = prefs.get("username", "Admin");

        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        leftPanel.setOpaque(false);

        ImageIcon icon = new ImageIcon(getClass().getResource("/clothes.png"));
        Image scaledImg = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(scaledImg));

        JLabel lblCompany = new JLabel("C D Cleaners");
        lblCompany.setFont(new Font("Arial", Font.BOLD, 16));
        lblCompany.setForeground(new Color(50, 50, 50));

        JLabel lblTagline = new JLabel("Complete Cleaning Service");
        lblTagline.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTagline.setForeground(new Color(90, 90, 90));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.add(lblCompany);
        textPanel.add(lblTagline);

        leftPanel.add(lblLogo);
        leftPanel.add(textPanel);

        gbc.gridx = 0;
        gbc.weightx = 0.20;
        add(leftPanel, gbc);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        centerPanel.setOpaque(false);

        btnHome = createButton("Home");
        btnHome.addActionListener(e -> cardLayout.show(mainContainer, "Startup"));
        centerPanel.add(btnHome);

        btnReports = createButton("Reports");
        btnReports.addActionListener(e -> cardLayout.show(mainContainer, "ReportsPage"));
        centerPanel.add(btnReports);

        btnSettings = createButton("Settings");
        btnSettings.addActionListener(e -> cardLayout.show(mainContainer, "SettingsPage"));
        centerPanel.add(btnSettings);

        gbc.gridx = 1;
        gbc.weightx = 0.60;
        add(centerPanel, gbc);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        rightPanel.setOpaque(false);

        lblUser = new JLabel("Hello " + username + " !");
        lblUser.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUser.setForeground(new Color(60, 60, 60));

        rightPanel.add(lblUser);

        gbc.gridx = 2;
        gbc.weightx = 0.20;
        add(rightPanel, gbc);
    }

    public void refreshUsername() {
        String username = prefs.get("username", "Admin");
        System.out.println("TopBarPanel.refreshUsername called, username = " + username);
        lblUser.setText("Hello " + username + " !");
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(0, 0, 0, 30));
        g2.fillRect(0, getHeight() - 3, getWidth(), 3);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(70, 130, 180));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
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