package larabadges;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;

public class Main {
    public static void main(String... args) throws Exception{

        RandomBadgeFactory randomBadgeFactory = new RandomBadgeFactory(1);
        SequenceBadgeFactory sequenceBadgeFactory = new SequenceBadgeFactory(Color.RED, Color.WHITE, 1, 51);
        HtmlBadgeFactory htmlBadgeFactory = new HtmlBadgeFactory();

        BadgesPrintable badgesPrintable = new BadgesPrintable(htmlBadgeFactory);

        PrinterJob job = PrinterJob.getPrinterJob();
        //if (job.printDialog()) {
        PageFormat pf = job.defaultPage();
        Paper paper = pf.getPaper();
        paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
        pf.setPaper(paper);
        job.setPrintable(badgesPrintable, pf);
        job.print();
        //}
    }
}
