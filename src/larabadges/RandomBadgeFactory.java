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
        return new SimpleBadge(randomTheme(), randomLetter());
    }

    private Theme randomTheme() {
        // Palette http://www.colourlovers.com/fashion/trends/street-fashion/7896/Floral_Much

        Theme[] themes = {
                  theme(0xEA3556, 0xFFFFFF)
                , theme(0x61D2D6, 0xFFFFFF)
                , theme(0xEDE5E2, 0x808080)
                , theme(0xED146F, 0xFFFFFF)
                , theme(0xEDDE45, 0x808080)
                , theme(0x9BF0E9, 0x808080)
        };

        return themes[random.nextInt(themes.length - 1)];
    }
    
    private Theme theme(int background, int foreground) {
        return new Theme(new Color(background), new Color(foreground));
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
