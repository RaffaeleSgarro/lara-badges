package larabadges;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class Badges implements Printable {
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

        for (Rectangle2D cell : grid) {
            AffineTransform cellTransform = new AffineTransform(transform);
            cellTransform.translate(cell.getX(), cell.getY());
            ctx.setTransform(cellTransform);
            //ctx.setClip(new Rectangle.Double(0, 0, cell.getWidth(), cell.getHeight()));
            Badge badge = randomBadge(new Rectangle.Double(0, 0, cell.getWidth(), cell.getHeight()));
            badge.paint(ctx);
        }

        return pageIndex < 1 ? PAGE_EXISTS : NO_SUCH_PAGE;
    }

    private Badge randomBadge(Rectangle2D cell) {
        return new Badge(randomTheme(), randomLetter(), cell);
    }

    private Theme randomTheme() {

        Color[] colors = {
                  new Color(0xF9E3EA)
                , new Color(0xFFC6A3)
                , new Color(0xFFFBC1)
                , new Color(0xEAF9CA)
                , new Color(0xCAEAF9)
        };

        return new Theme(colors[((int) (Math.random() * 4))], Color.WHITE);
    }

    private String randomLetter() {
        return Character.toString((char) ('A' + (int) (Math.random() * 25)));
    }

}
