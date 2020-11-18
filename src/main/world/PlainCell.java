package main.world;

import main.legends.Hero;

import java.util.List;
/**
 * Plain cell
 * @author Sandra Zhen
 */
public class PlainCell extends Cell{
    /**
     * Standard constructor
     * Throws an IllegalArgumentException if either row or col are negative
     *
     * @param row the row for this Cell
     * @param col the col for this Cell
     */
    public PlainCell(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean canEnter(List<Hero> heroes) {
        return false;
    }

    @Override
    public void enter(List<Hero> heroes) {

    }

    @Override
    public List<String> draw(int heroesRow, int heroesCol) {
        return null;
    }
}
