package ui.reports;

import java.awt.*;
import java.awt.print.*;
import javax.swing.*;

public class ReportPrinter {

    public static void printPanel(JPanel panel, String jobName) {

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName(jobName);

        job.setPrintable((graphics, pageFormat, pageIndex) -> {

            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2 = (Graphics2D) graphics;

            g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            double scaleX = pageFormat.getImageableWidth() / panel.getWidth();
            double scaleY = pageFormat.getImageableHeight() / panel.getHeight();
            double scale = Math.min(scaleX, scaleY);
            g2.scale(scale, scale);

            panel.printAll(g2);

            return Printable.PAGE_EXISTS;
        });

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
}