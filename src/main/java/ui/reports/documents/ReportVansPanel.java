package ui.reports.documents;

import services.ReportService;
import ui.reports.ReportHeaderPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ReportVansPanel extends JPanel {

    private ReportService reportService = new ReportService();

    public ReportVansPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(new ReportHeaderPanel("Vans per Store"), BorderLayout.NORTH);

        Map<String, Integer> vans = reportService.getVansPerStore();

        String[] cols = {"Store", "Number of Vans"};
        Object[][] data = new Object[vans.size()][2];

        int i = 0;
        for (String store : vans.keySet()) {
            data[i][0] = store;
            data[i][1] = vans.get(store);
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