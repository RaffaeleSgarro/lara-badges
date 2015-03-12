package larabadges;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class HtmlBadgeFactory implements BadgeFactory {

    public static final int ROWS = 5;
    public static final int COLUMNS = 2;

    private final RandomBadgeContentGenerator badgeContentGenerator = new RandomBadgeContentGenerator();

    @Override
    public boolean hasPage(int pageIndex) {
        return pageIndex == 0;
    }

    @Override
    public Page getPage(int pageIndex) {
        try (Reader in = new InputStreamReader(HtmlBadge.class.getResourceAsStream("/simple_badge.html"), "UTF-8")) {

            badgeContentGenerator.setUp(pageIndex);
            Template template = Mustache.compiler().compile(in);

            Page page = new Page(ROWS, COLUMNS);
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLUMNS; col++) {

                    Map<String, Object> bindings = new HashMap<>();
                    bindings.put("text", badgeContentGenerator.randomLetter());

                    Theme theme = badgeContentGenerator.randomTheme();
                    bindings.put("background", cssColor(theme.getBackground()));
                    bindings.put("foreground", cssColor(theme.getForeground()));

                    String text = template.execute(bindings);

                    InputSource is = new InputSource(new StringReader(text));
                    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
                    Badge badge = new HtmlBadge(document);
                    page.setBadge(row, col, badge);
                }
            }
            return page;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String cssColor(Color color) {
        return "#" + Integer.toHexString(color.getRGB()).toUpperCase().substring(2);
    }
}
