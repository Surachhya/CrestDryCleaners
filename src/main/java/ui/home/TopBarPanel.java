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
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        leftPanel.setOpaque(false);

        ImageIcon icon = new ImageIcon(getClass().getResource("/clothes.png"));
        Image scaledImg = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(scaledImg));

        leftPanel.add(lblLogo);

        add(leftPanel, BorderLayout.WEST);

        JPanel centerWrapper = new JPanel(new GridBagLayout());  // always centers its content
        centerWrapper.setOpaque(false);

        JPanel centerButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        centerButtons.setOpaque(false);

        btnHome = createButton("Home");
        btnHome.addActionListener(e -> cardLayout.show(mainContainer, "Startup"));
        centerButtons.add(btnHome);

        btnReports = createButton("Reports");
        centerButtons.add(btnReports);

        btnSettings = createButton("Settings");
        centerButtons.add(btnSettings);

        centerWrapper.add(centerButtons); // centered by GridBagLayout

        add(centerWrapper, BorderLayout.CENTER);


        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        rightPanel.setOpaque(false);

        lblUser = new JLabel("Hello Admin !");
        lblUser.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUser.setForeground(new Color(60, 60, 60));

        rightPanel.add(lblUser);

        add(rightPanel, BorderLayout.EAST);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Drop shadow at the bottom
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
