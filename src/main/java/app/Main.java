package app;

import ui.home.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        // GLOBAL BUTTON
        UIManager.put("Button.opaque", true);
        UIManager.put("Button.contentAreaFilled", false);
        UIManager.put("Button.borderPainted", false);

        // GLOBAL TABLE STYLING
        UIManager.put("Table.rowHeight", 28);
        UIManager.put("Table.showGrid", false);
        UIManager.put("Table.intercellSpacing", new Dimension(0, 0));

        // Selection colors
        UIManager.put("Table.selectionBackground", new Color(100, 149, 237));
        UIManager.put("Table.selectionForeground", Color.WHITE);

        // Header styling
        UIManager.put("TableHeader.font", new Font("Arial", Font.BOLD, 14));
        UIManager.put("TableHeader.background", new Color(70, 130, 180));
        UIManager.put("TableHeader.foreground", Color.WHITE);

        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}