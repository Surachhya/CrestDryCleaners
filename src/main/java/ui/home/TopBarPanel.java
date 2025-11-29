package ui.home;

import javax.swing.*;
import java.awt.*;

public class TopBarPanel extends JPanel {

    public JButton btnHome;
    public JButton btnReports;
    public JButton btnSettings;

    private JLabel lblUser;

    public TopBarPanel(CardLayout cardLayout, JPanel mainContainer) {

        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // ==========================================================
        // LEFT: LOGO WRAPPER WITH ROUNDED BACKGROUND
        // ==========================================================
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        leftPanel.setOpaque(false);

        // Load and scale icon
        ImageIcon icon = new ImageIcon(getClass().getResource("/clothes.png"));
        Image scaledImg = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(scaledImg));

        // Rounded badge container around logo
        JPanel logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(245, 245, 245)); // same as background
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        logoPanel.setOpaque(false);
        logoPanel.setLayout(new BorderLayout());
        logoPanel.add(lblLogo, BorderLayout.CENTER);
        logoPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        leftPanel.add(logoPanel);
        add(leftPanel, BorderLayout.WEST);


        // ==========================================================
        // CENTER: BUTTONS (Perfectly Centered)
        // ==========================================================
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        centerPanel.setOpaque(false);

        btnHome = createButton("Home");
        btnHome.addActionListener(e -> cardLayout.show(mainContainer, "Startup"));
        centerPanel.add(btnHome);

        btnReports = createButton("Reports");
        btnReports.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Reports feature coming soon!"));
        centerPanel.add(btnReports);

        btnSettings = createButton("Settings");
        btnSettings.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Settings feature coming soon!"));
        centerPanel.add(btnSettings);

        add(centerPanel, BorderLayout.CENTER);


        // ==========================================================
        // RIGHT: USER LABEL
        // ==========================================================
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        rightPanel.setOpaque(false);

        lblUser = new JLabel("Hello Admin !");
        lblUser.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUser.setForeground(new Color(60, 60, 60));

        rightPanel.add(lblUser);
        add(rightPanel, BorderLayout.EAST);
    }


    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(70, 130, 180));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
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
