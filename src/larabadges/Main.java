package larabadges;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;

public class Main {
    public static void main(String... args) throws Exception{
        Badges badges = new Badges();
        PrinterJob job = PrinterJob.getPrinterJob();
        //if (job.printDialog()) {
        PageFormat pf = job.defaultPage();
        Paper paper = pf.getPaper();
        paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
        pf.setPaper(paper);
        job.setPrintable(badges, pf);
        job.print();
        //}
    }
}