package main.world;

import main.Runner;
import main.legends.Hero;
import main.utils.Colors;

import java.util.ArrayList;
import java.util.List;

/**
 * Koulou cell
 * @author Sandra Zhen
 */
public class KoulouCell extends Cell{
    /**
     * Standard constructor
     * Throws an IllegalArgumentException if either row or col are negative
     *
     * @param row the row for this Cell
     * @param col the col for this Cell
     */
    public KoulouCell(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean canEnter(List<Hero> heroes) {
        // TODO: check if there is another Hero in this cell already
        return true;
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
        // TODO: add in Hero and Monster if necessary
        List<String> output = new ArrayList<>();
        String color = Colors.ANSI_RESET;
        output.add(color + "K-----K ");
        World world = Runner.getInstance().getWorld();
        output.add(world.drawMiddleRow(this, Colors.ANSI_RESET));
        output.add(color + "K-----K " + Colors.ANSI_RESET); // Reset the color for the next Cell
        return output;
    }
}
