package larabadges;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class BadgesPrintable implements Printable {

    private final BadgeFactory badgeFactory;

    public BadgesPrintable(BadgeFactory badgeFactory) {
        this.badgeFactory = badgeFactory;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Graphics2D ctx = (Graphics2D) graphics;
        // We want to draw in mm, but the Java print API uses dots. There are 72 dots in an inch,
        // one inch is 25.4 mm, so 1 dot = 1inch / 72 = 25.4mm / 72
        double scaleFactor = 72 / 25.4;
        ctx.scale(scaleFactor, scaleFactor);

        ctx.translate(15.0, 13.5);

        Grid grid = new Grid(5, 2, 10.0, 0.0);
        grid.layout(85.0 * 2 + 10.0, 54.0 * 5);

        AffineTransform transform = new AffineTransform(ctx.getTransform());

        if (badgeFactory.hasPage(pageIndex)) {
            Page page = badgeFactory.getPage(pageIndex);
            for (Cell cell : grid) {
                if (page.hasBadge(cell.getRowIndex(), cell.getColumnIndex())) {
                    AffineTransform cellTransform = new AffineTransform(transform);
                    Rectangle2D rect = cell.getRectangle();
                    cellTransform.translate(rect.getX(), rect.getY());
                    ctx.setTransform(cellTransform);
                    Badge badge = page.getBadgeAt(cell.getRowIndex(), cell.getColumnIndex());
                    Rectangle2D.Double badgeSize = new Rectangle.Double(0, 0, rect.getWidth(), rect.getHeight());
                    ctx.setClip(badgeSize);
                    badge.paint(ctx, badgeSize);
                }
            }
            return PAGE_EXISTS;
        } else {
            return NO_SUCH_PAGE;
        }
    }

}
