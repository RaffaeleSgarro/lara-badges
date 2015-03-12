package larabadges;

import org.w3c.dom.Document;
import org.xhtmlrenderer.simple.Graphics2DRenderer;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class HtmlBadge implements Badge {

    private final Document document;

    public HtmlBadge(Document document) {
        this.document = document;
    }

    @Override
    public void paint(Graphics2D ctx, Rectangle2D cell) {
        try {
            double scaleFactor = 25.4 / 72;
            ctx.scale(scaleFactor, scaleFactor);

            Graphics2DRenderer renderer = new Graphics2DRenderer();
            renderer.setDocument(document, "");

            int width = (int) (cell.getWidth() / scaleFactor);
            int height = (int) (cell.getHeight() / scaleFactor);
            renderer.layout(ctx, new Dimension(width, height));
            renderer.render(ctx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
