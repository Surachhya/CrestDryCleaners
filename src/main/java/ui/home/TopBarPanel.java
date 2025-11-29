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
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // ==========================================================
        // LEFT: LOGO
        // ==========================================================
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 1));
        leftPanel.setOpaque(false);

        JLabel lblLogo = new JLabel();

        ImageIcon icon = new ImageIcon(getClass().getResource("/clothes.png"));
        Image scaledImg = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(scaledImg));


        leftPanel.add(lblLogo);


        add(leftPanel, BorderLayout.WEST);


        // ==========================================================
        // CENTER: BUTTONS (Centered horizontally)
        // ==========================================================
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        centerPanel.setOpaque(false);

        btnHome = createButton("Home");
        btnHome.addActionListener(e -> cardLayout.show(mainContainer, "Startup"));
        centerPanel.add(btnHome);

        btnReports = createButton("Reports");
        btnReports.addActionListener(e -> JOptionPane.showMessageDialog(this, "Reports feature coming soon!"));
        centerPanel.add(btnReports);

        btnSettings = createButton("Settings");
        btnSettings.addActionListener(e -> JOptionPane.showMessageDialog(this, "Settings feature coming soon!"));
        centerPanel.add(btnSettings);

        add(centerPanel, BorderLayout.CENTER);


        // ==========================================================
        // RIGHT: USER LABEL
        // ==========================================================
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        rightPanel.setOpaque(false);

        lblUser = new JLabel("Admin");
        lblUser.setFont(new Font("Arial", Font.PLAIN, 14));
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
