package main.world;

import main.legends.Hero;

/**
 * Represents a monster nexus cell
 * @author: Sandra Zhen
 *
 */

public class MonsterNexusCell extends NexusCell{
    /**
     * Standard constructor
     * Throws an IllegalArgumentException if either row or col are negative
     *
     * @param row the row for this Cell
     * @param col the col for this Cell
     */
    public MonsterNexusCell(int row, int col) {
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
