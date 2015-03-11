package larabadges;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Badge {

    private final Theme theme;
    private final String text;
    private final Rectangle2D cell;

    public Badge(Theme theme, String text, Rectangle2D cell) {
        this.theme = theme;
        this.text = text;
        this.cell = cell;
    }

    public void paint(Graphics2D ctx) {
        ctx.setColor(theme.getBackground());
        ctx.fill(cell);

        Font font = new Font("Arial", Font.PLAIN, 20);
        ctx.setFont(font);
        FontMetrics fontMetrics = ctx.getFontMetrics();

        float ascent = fontMetrics.getAscent();
        float textWidth = fontMetrics.stringWidth(text);
        ctx.setColor(theme.getForeground());
        ctx.drawString(text, ((float) cell.getWidth() - textWidth) / 2, (54f - ascent) / 2 + ascent);
    }
}
