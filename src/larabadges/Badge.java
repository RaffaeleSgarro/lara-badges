package larabadges;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public interface Badge {

    /**
     * Paint the badge. ctx is translated such that the badge can paint relative to (0; 0)
     * It's also scaled to allow drawing in mm
     *
     * cell is the rectangle containing the badge. It's positioned to (0, 0) and width and
     * height are given in mm, too
     *
     * @param ctx
     * @param cell
     */
    void paint(Graphics2D ctx, Rectangle2D cell);
}
