package larabadges;

public class RandomBadgeFactory implements BadgeFactory {

    private final int pages;
    private final RandomBadgeContentGenerator badgeContentGenerator = new RandomBadgeContentGenerator();

    public RandomBadgeFactory(int pages) {
        this.pages = pages;
    }

    private Badge randomBadge() {
        return new SimpleBadge(badgeContentGenerator.randomTheme(), badgeContentGenerator.randomLetter());
    }

    @Override
    public boolean hasPage(int pageIndex) {
        return pageIndex < pages;
    }

    @Override
    public Page getPage(int pageIndex) {
        int rows = 5;
        int columns = 2;

        badgeContentGenerator.setUp(pageIndex);

        Page page = new Page(rows, columns);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                page.setBadge(row, col, randomBadge());
            }
        }

        return page;
    }
}
