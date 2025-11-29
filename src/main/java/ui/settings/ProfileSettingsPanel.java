package ui.settings;

import ui.home.MainFrame;
import ui.home.UIComponents;

import javax.swing.*;
import java.awt.*;
import java.util.prefs.Preferences;

public class ProfileSettingsPanel extends JPanel {

    private Preferences prefs = Preferences.userNodeForPackage(MainFrame.class);

    public ProfileSettingsPanel(Runnable onProfileUpdated) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Profile Settings");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField txtName = new JTextField(prefs.get("username", "Admin"));
        txtName.setMaximumSize(new Dimension(300, 35));

        JButton btnSave = UIComponents.createPrimaryButton("Save");
        btnSave.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnSave.addActionListener(e -> {
            prefs.put("username", txtName.getText().trim());
            JOptionPane.showMessageDialog(this, "Profile updated.");

            onProfileUpdated.run();
        });

        add(title);
        add(Box.createVerticalStrut(20));
        add(new JLabel("Display Name:"));
        add(txtName);
        add(Box.createVerticalStrut(15));
        add(btnSave);
    }
}