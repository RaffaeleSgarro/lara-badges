package larabadges;

import java.awt.*;
import java.util.Random;

public class RandomBadgeFactory implements BadgeFactory {

    private final int pages;
    private final Random random = new Random();

    public RandomBadgeFactory(int pages) {
        this.pages = pages;
    }

    private Badge randomBadge() {
        return new Badge(randomTheme(), randomLetter());
    }

    private Theme randomTheme() {

        Color[] colors = {
                new Color(0x791718)
                , new Color(0xFE3942)
                , new Color(0xFF5888)
                , new Color(0xD9B7B6)
                , new Color(0x3B3436)
        };

        return new Theme(colors[random.nextInt(colors.length - 1)], Color.WHITE);
    }

    private String randomLetter() {
        return Character.toString((char) ('A' + random.nextInt(25)));
    }

    @Override
    public boolean hasPage(int pageIndex) {
        return pageIndex < pages;
    }

    @Override
    public Page getPage(int pageIndex) {
        int rows = 5;
        int columns = 2;

        random.setSeed(pageIndex);
        Page page = new Page(rows, columns);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                page.setBadge(row, col, randomBadge());
            }
        }

        return page;
    }
}
