package ui.home;

import javax.swing.*;
import java.awt.*;

public class UIComponents {

    public static JButton createPrimaryButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(70, 130, 180)); // steel blue
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
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