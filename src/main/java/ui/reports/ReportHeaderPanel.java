package ui.reports;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportHeaderPanel extends JPanel {

    public ReportHeaderPanel(String title) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel lblCompany = new JLabel("C D Cleaners");
        lblCompany.setFont(new Font("Arial", Font.BOLD, 22));
        lblCompany.setForeground(new Color(40, 40, 40));

        JLabel lblTagline = new JLabel("Complete Cleaning Service");
        lblTagline.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTagline.setForeground(new Color(90, 90, 90));


        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(new Color(60, 60, 60));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));


        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MMM dd, yyyy  hh:mm a"));

        JLabel lblDate = new JLabel("Generated: " + timestamp);
        lblDate.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDate.setForeground(new Color(100, 100, 100));


        JPanel stack = new JPanel();
        stack.setLayout(new BoxLayout(stack, BoxLayout.Y_AXIS));
        stack.setOpaque(false);

        stack.add(lblCompany);
        stack.add(lblTagline);
        stack.add(Box.createVerticalStrut(10));
        stack.add(lblTitle);
        stack.add(lblDate);

        add(stack, BorderLayout.NORTH);


        JSeparator sep = new JSeparator();
        add(sep, BorderLayout.SOUTH);
    }
}