package ui.home;

import services.ResetService;

import javax.swing.*;
import java.awt.*;

public class ResetPage extends JPanel {

    private ResetService resetService = new ResetService();

    public ResetPage() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Application Reset");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnReset = UIComponents.createPrimaryButton("Reset Database (Wipe All Data)");
        JButton btnSample = UIComponents.createPrimaryButton("Load Sample Data");

        btnReset.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnSample.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnReset.addActionListener(e -> {

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "This will ERASE all data and reset the database.\nAre you absolutely sure?",
                    "Confirm Reset",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return; // user cancelled
            }

            runWithProgress("Resetting database...", () -> resetService.resetDatabase());
        });

        btnSample.addActionListener(e -> {

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Load sample data?\nThis will insert demo rows into the database.",
                    "Confirm Load",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            runWithProgress("Loading sample data...", () -> resetService.loadSampleData());
        });

        add(title);
        add(Box.createVerticalStrut(20));
        add(btnReset);
        add(Box.createVerticalStrut(10));
        add(btnSample);
    }

    private void runWithProgress(String message, Runnable task) {
        JDialog dlg = new JDialog((Frame) null, message, true);
        dlg.setLayout(new BorderLayout());
        dlg.add(new JLabel(message, SwingConstants.CENTER), BorderLayout.CENTER);
        dlg.setSize(300, 120);
        dlg.setLocationRelativeTo(null);

        new Thread(() -> {
            try {
                task.run();
                JOptionPane.showMessageDialog(null, "Operation completed successfully.");
            } finally {
                dlg.dispose();
            }
        }).start();

        dlg.setVisible(true);
    }
}