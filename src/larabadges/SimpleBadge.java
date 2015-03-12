package larabadges;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SimpleBadge implements Badge {

    private final Theme theme;
    private final String text;

    public SimpleBadge(Theme theme, String text) {
        this.theme = theme;
        this.text = text;
    }

    @Override
    public void paint(Graphics2D ctx, Rectangle2D cell) {
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
