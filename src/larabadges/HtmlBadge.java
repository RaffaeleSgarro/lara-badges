package larabadges;

import org.w3c.dom.Document;
import org.xhtmlrenderer.simple.Graphics2DRenderer;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HtmlBadge implements Badge {

    @Override
    public void paint(Graphics2D ctx, Rectangle2D cell) {
        try (InputStream in = HtmlBadge.class.getResourceAsStream("/simple_badge.html")) {
            Graphics2DRenderer renderer = new Graphics2DRenderer();
            InputSource is = new InputSource(new InputStreamReader(in, "UTF-8"));
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            renderer.setDocument(document, "");
            renderer.layout(ctx, new Dimension((int) cell.getWidth(), (int) cell.getHeight()));
            renderer.render(ctx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
