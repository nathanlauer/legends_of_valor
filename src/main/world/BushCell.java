package main.world;

/**
 * Bush cell
 * @author Sandra Zhen
 */
import main.legends.Hero;

import java.util.List;

public class BushCell extends Cell{
    /**
     * Standard constructor
     * Throws an IllegalArgumentException if either row or col are negative
     *
     * @param row the row for this Cell
     * @param col the col for this Cell
     */
    public BushCell(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean canEnter(Hero hero) {
        return false;
    }

    @Override
    public void enter(Hero hero) {

    }

    @Override
    public List<String> draw(int heroesRow, int heroesCol) {
        return null;
    }
}
