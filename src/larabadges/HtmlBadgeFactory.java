package larabadges;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.io.InputStreamReader;
import java.io.Reader;

public class HtmlBadgeFactory implements BadgeFactory {

    public static final int ROWS = 5;
    public static final int COLUMNS = 2;

    @Override
    public boolean hasPage(int pageIndex) {
        return pageIndex == 0;
    }

    @Override
    public Page getPage(int pageIndex) {
        try (Reader in = new InputStreamReader(HtmlBadge.class.getResourceAsStream("/simple_badge.html"), "UTF-8")) {
            Template template = Mustache.compiler().compile(in);
            Page page = new Page(ROWS, COLUMNS);
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLUMNS; col++) {
                    Badge badge = new HtmlBadge(template);
                    page.setBadge(row, col, badge);
                }
            }
            return page;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
