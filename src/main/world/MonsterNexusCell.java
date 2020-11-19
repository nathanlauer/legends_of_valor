package main.world;

import main.legends.Hero;
import main.utils.Colors;

import java.util.ArrayList;
import java.util.List;

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
    public boolean canEnter(List<Hero> heroes) {
        return false;
    }

    @Override
    public void enter(List<Hero> heroes) {

    }

    /**
     * Returns a string representation of the implementing entity. If
     * the position of the entity corresponds to (heroesRow,heroesCol), then
     * draws that the Heroes are in that location.
     */
    @Override
    public List<String> draw() {
        List<String> output = new ArrayList<>();
        String color = Colors.ANSI_CYAN;
        output.add(color + "+-----+ ");
        output.add(color + "|     | ");
        output.add(color + "+-----+ " + Colors.ANSI_RESET); // Reset the color for the next Cell
        return output;
    }
}
