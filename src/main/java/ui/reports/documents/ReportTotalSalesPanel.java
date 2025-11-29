package ui.reports.documents;

import services.ReportService;
import ui.reports.ReportHeaderPanel;

import javax.swing.*;
import java.awt.*;

public class ReportTotalSalesPanel extends JPanel {

    private ReportService reportService = new ReportService();

    public ReportTotalSalesPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);


        ReportHeaderPanel header = new ReportHeaderPanel("Total Sales Summary");
        add(header, BorderLayout.NORTH);


        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        double total = reportService.getTotalSales();

        JLabel lblTotal = new JLabel("Total Sales: $" + total);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 24));
        lblTotal.setForeground(new Color(50, 50, 50));

        content.add(lblTotal);

        add(content, BorderLayout.CENTER);
    }
}