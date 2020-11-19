package main.world;
/**
 * Represents a hero nexus cell
 * @author: Sandra Zhen
 *
 */

import main.legends.Hero;

import java.util.List;

public class HeroNexusCell extends NexusCell{
    /**
     * Standard constructor
     * Throws an IllegalArgumentException if either row or col are negative
     *
     * @param row the row for this Cell
     * @param col the col for this Cell
     */
    public HeroNexusCell(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean canEnter(List<Hero> heroes) {
        return false;
    }

    @Override
    public void enter(List<Hero> heroes) {

    }
}
