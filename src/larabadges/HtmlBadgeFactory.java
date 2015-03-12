package larabadges;

public class HtmlBadgeFactory implements BadgeFactory {

    public static final int ROWS = 5;
    public static final int COLUMNS = 2;

    @Override
    public boolean hasPage(int pageIndex) {
        return pageIndex == 0;
    }

    @Override
    public Page getPage(int pageIndex) {
        Page page = new Page(ROWS, COLUMNS);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                Badge badge = new HtmlBadge();
                page.setBadge(row, col, badge);
            }
        }
        return page;
    }
}
