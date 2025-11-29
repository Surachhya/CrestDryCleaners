package ui.reports.documents;

import services.ReportService;
import ui.reports.ReportHeaderPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ReportEmployeesPanel extends JPanel {

    private ReportService reportService = new ReportService();

    public ReportEmployeesPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(new ReportHeaderPanel("Employees per Store"), BorderLayout.NORTH);

        Map<String, Integer> employees = reportService.getEmployeesPerStore();

        String[] cols = {"Store", "Employee Count"};
        Object[][] data = new Object[employees.size()][2];

        int i = 0;
        for (String store : employees.keySet()) {
            data[i][0] = store;
            data[i][1] = employees.get(store);
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