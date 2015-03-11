package larabadges;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
* Created by admin on 12/03/2015.
*/
public class Grid implements Iterable<Rectangle2D> {

    private final int rows;
    private final int columns;
    private final double horizontalGap;
    private final double verticalGap;

    private double cellWidth;
    private double cellHeight;

    public Grid(int rows, int columns, double horizontalGap, double verticalGap) {
        this.rows = rows;
        this.columns = columns;
        this.horizontalGap = horizontalGap;
        this.verticalGap = verticalGap;
    }

    @Override
    public Iterator<Rectangle2D> iterator() {
        List<Rectangle2D> cells = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                double x = col * (cellWidth + horizontalGap);
                double y = row * (cellHeight + verticalGap);
                Rectangle2D rectangle = new Rectangle.Double(x, y, cellWidth, cellHeight);
                cells.add(rectangle);
            }
        }

        return cells.iterator();
    }

    public void layout(double width, double height) {
        if (rows < 1 || columns < 1)
            throw new RuntimeException("This grid has " + rows + " rows and " + columns + " columns");

        cellWidth = (width - (horizontalGap * (columns - 1))) / columns ;
        cellHeight = (height - (verticalGap * (rows - 1)))/ rows;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double getHorizontalGap() {
        return horizontalGap;
    }

    public double getVerticalGap() {
        return verticalGap;
    }
}
