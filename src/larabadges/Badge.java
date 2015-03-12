package larabadges;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public interface Badge {
    void paint(Graphics2D ctx, Rectangle2D cell);
}
