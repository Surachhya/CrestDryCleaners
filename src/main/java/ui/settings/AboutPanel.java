package ui.settings;

import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {

    public AboutPanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("About This Application");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel info = new JLabel("<html>This is a dry cleaning management system built with Java Swing and MySQL.</html>");
        info.setFont(new Font("Arial", Font.PLAIN, 14));
        info.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(title);
        add(Box.createVerticalStrut(20));
        add(info);
    }
}