package larabadges;

import java.awt.*;

public class SequenceBadgeFactory implements BadgeFactory {

    private static final int ROWS = 5;
    private static final int COLUMNS = 2;

    private final Color backgroundColor;
    private final Color foregroundColor;
    private final int startInclusive;
    private final int endInclusive;

    public SequenceBadgeFactory(Color backgroundColor, Color foregroundColor, int startInclusive, int endInclusive) {
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
    }

    @Override
    public boolean hasPage(int pageIndex) {
        return pageIndex <= (endInclusive - startInclusive + 1) / (ROWS * COLUMNS);
    }

    @Override
    public Page getPage(int pageIndex) {
        Page page = new Page(ROWS, COLUMNS);
        int counter = startInclusive + (pageIndex * ROWS * COLUMNS);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (counter <= endInclusive) {
                    page.setBadge(row, col, new Badge(new Theme(backgroundColor, foregroundColor), Integer.toString(counter)));
                }
                counter++;
            }
        }
        return page;
    }
}
