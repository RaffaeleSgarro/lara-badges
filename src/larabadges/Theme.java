package larabadges;

import java.awt.*;

public class Theme {

    private final Color background;
    private final Color foreground;

    public Theme(Color background, Color foreground) {
        this.background = background;
        this.foreground = foreground;
    }

    public Color getBackground() {
        return background;
    }

    public Color getForeground() {
        return foreground;
    }
}
