package larabadges;

import java.awt.geom.Rectangle2D;

public class Cell {

    private final int rowIndex;
    private final int columnIndex;
    private final Rectangle2D rectangle;

    public Cell(int rowIndex, int columnIndex, Rectangle2D rectangle) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.rectangle = rectangle;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public Rectangle2D getRectangle() {
        return rectangle;
    }
}
