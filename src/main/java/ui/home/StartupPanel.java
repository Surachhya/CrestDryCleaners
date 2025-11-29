package ui.home;

import ui.order.OrderAddPanel;

import javax.swing.*;
import java.awt.*;

public class StartupPanel extends JPanel {
    public JButton btnStartOrder;
    public JButton btnManageOrders;

    public StartupPanel(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245)); // light gray background

        // -------------------------
        // Top: Company Name / Logo
        // -------------------------
        JLabel lblTitle = new JLabel("Crest Dry Cleaners", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitle.setForeground(new Color(30, 30, 30));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

        JLabel lblSubtitle = new JLabel("Efficient. Clean. Reliable.", SwingConstants.CENTER);
        lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        lblSubtitle.setForeground(new Color(80, 80, 80));
        lblSubtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false); // transparent panel
        topPanel.add(lblTitle, BorderLayout.NORTH);
        topPanel.add(lblSubtitle, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // -------------------------
        // Center: Buttons
        // -------------------------
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        btnStartOrder = createButton("Start Order");
        btnManageOrders = createButton("Manage Orders");

        buttonPanel.add(btnStartOrder);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20))); // spacing
        buttonPanel.add(btnManageOrders);

        add(buttonPanel, BorderLayout.CENTER);

        // -------------------------
        // Button actions
        // -------------------------
        btnStartOrder.addActionListener(e -> {
            OrderAddPanel dlg = new OrderAddPanel(mainFrame);
            dlg.setVisible(true);
        });

        btnManageOrders.addActionListener(e -> mainFrame.showManageOrders());
    }

    // -------------------------
    // Helper: styled button
    // -------------------------
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setPreferredSize(new Dimension(250, 70));
        btn.setMaximumSize(new Dimension(250, 70));
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(70, 130, 180)); // steel blue
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Optional: hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(100, 149, 237)); // lighter blue
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(70, 130, 180));
            }
        });

        return btn;
    }
}
