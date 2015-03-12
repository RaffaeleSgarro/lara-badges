package larabadges;

public class Page {

    private final Badge[][] badges;

    public Page(int rows, int columns) {
        badges = new Badge[rows][columns];
    }

    public boolean hasBadge(int row, int column) {
        return badges[row][column] != null;
    }

    public Badge getBadgeAt(int row, int column) {
        return badges[row][column];
    }

    public void setBadge(int row, int col, Badge badge) {
        badges[row][col] = badge;
    }

}
