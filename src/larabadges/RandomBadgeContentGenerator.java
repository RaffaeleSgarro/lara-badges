package larabadges;

import java.awt.*;
import java.util.Random;

public class RandomBadgeContentGenerator {

    private final Random random = new Random();

    public void setUp(int pageIndex) {
        random.setSeed(pageIndex);
    }

    public Theme randomTheme() {
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

    public String randomLetter() {
        return Character.toString((char) ('A' + random.nextInt(25)));
    }

}
