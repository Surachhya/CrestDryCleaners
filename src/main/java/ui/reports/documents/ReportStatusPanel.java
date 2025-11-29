package ui.reports.documents;

import services.ReportService;
import ui.reports.ReportHeaderPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ReportStatusPanel extends JPanel {

    private ReportService reportService = new ReportService();

    public ReportStatusPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(new ReportHeaderPanel("Orders by Status"), BorderLayout.NORTH);

        Map<String, Integer> status = reportService.getOrdersByStatus();

        String[] cols = {"Status", "Count"};
        Object[][] data = new Object[status.size()][2];

        int i = 0;
        for (String s : status.keySet()) {
            data[i][0] = s;
            data[i][1] = status.get(s);
            i++;
        }

        JTable table = new JTable(data, cols);
        table.setFillsViewportHeight(true);

        table.setIntercellSpacing(new Dimension(5, 5));
        table.setRowHeight(20);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        JPanel padded = new JPanel(new BorderLayout());
        padded.setBackground(Color.white);
        padded.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        padded.add(scroll, BorderLayout.CENTER);

        add(padded, BorderLayout.CENTER);
    }
}