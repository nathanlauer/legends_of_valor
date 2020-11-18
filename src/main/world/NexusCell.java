package main.world;

import java.util.List;

public class NexusCell extends Cell{

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

    @Override
    public boolean canEnter() {
        return true;
    }

    @Override
    public void enter() {

    }

    @Override
    public List<String> draw(int heroesRow, int heroesCol) {
        return null;
    }
}
