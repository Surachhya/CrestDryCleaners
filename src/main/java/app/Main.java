package app;

import ui.home.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        UIManager.put("Button.opaque", true);
        UIManager.put("Button.contentAreaFilled", false);
        UIManager.put("Button.borderPainted", false);
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
