package main.world;
/**
 * Represents a hero nexus cell
 * @author: Sandra Zhen
 *
 */

import main.Runner;
import main.legends.Hero;
import main.utils.Colors;

import java.util.ArrayList;
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
        // TODO: check if there is another Hero in this cell already
        return true;
    }

    @Override
    public void enter(List<Hero> heroes) {
        // This is a market cell, so enter the Market with the heroes
        super.enter(heroes);
    }

    /**
     * Returns a string representation of the implementing entity. If
     * the position of the entity corresponds to (heroesRow,heroesCol), then
     * draws that the Heroes are in that location.
     */
    @Override
    public List<String> draw() {
        List<String> output = new ArrayList<>();
        String color = Colors.ANSI_GREEN;
        output.add(color + "M-----M ");
        World world = Runner.getInstance().getWorld();
        output.add(world.drawMiddleRow(this, Colors.ANSI_GREEN));
        output.add(color + "M-----M " + Colors.ANSI_RESET); // Reset the color for the next Cell
        return output;
    }
}
