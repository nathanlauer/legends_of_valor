package main.world;
/**
 * Represents a hero nexus cell
 * @author: Sandra Zhen
 *
 */

import main.legends.Hero;

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
    public boolean canEnter(Hero hero) {
        return false;
    }

    @Override
    public void enter(Hero hero) {

    }
}
