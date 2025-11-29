package ui.settings;

import ui.home.ResetPage;
import ui.home.TopBarPanel;

import javax.swing.*;
import java.awt.*;

public class SettingsPage extends JPanel {
    private TopBarPanel topBar;
    private CardLayout cardLayout;
    private JPanel rightContent;

    public SettingsPage(TopBarPanel topBar) {
        this.topBar = topBar;
        setLayout(new BorderLayout());

        // ============================
        // LEFT MENU
        // ============================
        JPanel leftMenu = new JPanel();
        leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.Y_AXIS));
        leftMenu.setPreferredSize(new Dimension(220, 0));
        leftMenu.setBackground(new Color(235, 235, 235));
        leftMenu.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JButton btnProfile = createMenuButton("Profile");
        JButton btnReset   = createMenuButton("Reset Database");
        JButton btnAbout   = createMenuButton("About");

        addMenuItem(leftMenu, btnProfile);
        addMenuItem(leftMenu, btnReset);
        addMenuItem(leftMenu, btnAbout);

        add(leftMenu, BorderLayout.WEST);

        // ============================
        // RIGHT CONTENT (CardLayout)
        // ============================
        cardLayout = new CardLayout();
        rightContent = new JPanel(cardLayout);

        rightContent.add(new ProfileSettingsPanel(() -> topBar.refreshUsername()), "profile");
        rightContent.add(new ResetPage(), "reset");
        rightContent.add(new AboutPanel(), "about");

        add(rightContent, BorderLayout.CENTER);

        // ============================
        // BUTTON ACTIONS
        // ============================
        btnProfile.addActionListener(e -> cardLayout.show(rightContent, "profile"));
        btnReset.addActionListener(e -> cardLayout.show(rightContent, "reset"));
        btnAbout.addActionListener(e -> cardLayout.show(rightContent, "about"));
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

    private void addMenuItem(JPanel menu, JButton btn) {
        menu.add(btn);
        menu.add(Box.createVerticalStrut(5));

        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setForeground(new Color(200, 200, 200));

        menu.add(sep);
        menu.add(Box.createVerticalStrut(10));
    }
}