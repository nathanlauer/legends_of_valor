package main.world;

import java.util.List;

public abstract class NexusCell extends MarketCell {

    /**
     * Standard constructor
     * Throws an IllegalArgumentException if either row or col are negative
     *
     * @param row the row for this Cell
     * @param col the col for this Cell
     */
    public NexusCell(int row, int col) {
        super(row, col);
    }

}
