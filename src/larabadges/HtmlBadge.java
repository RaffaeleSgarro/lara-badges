package larabadges;

import com.samskivert.mustache.Template;
import org.w3c.dom.Document;
import org.xhtmlrenderer.simple.Graphics2DRenderer;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class HtmlBadge implements Badge {

    private final Template template;

    public HtmlBadge(Template template) {
        this.template = template;
    }

    @Override
    public void paint(Graphics2D ctx, Rectangle2D cell) {
        try {
            double scaleFactor = 25.4 / 72;
            ctx.scale(scaleFactor, scaleFactor);

            Map<String, Object> bindings = new HashMap<>();
            bindings.put("text", "L");
            bindings.put("background", "yellow");
            bindings.put("foreground", "green");
            String text = template.execute(bindings);
            InputSource is = new InputSource(new StringReader(text));
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

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
