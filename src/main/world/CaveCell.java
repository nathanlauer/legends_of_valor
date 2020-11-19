package main.world;

/**
 * Cave cell
 * @author Sandra Zhen
 */
import main.legends.Hero;
import main.utils.Colors;

import java.util.ArrayList;
import java.util.List;

public class CaveCell extends Cell{
    /**
     * Standard constructor
     * Throws an IllegalArgumentException if either row or col are negative
     *
     * @param row the row for this Cell
     * @param col the col for this Cell
     */
    public CaveCell(int row, int col) {
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
        // TODO: add in Hero and Monster if necessary
        List<String> output = new ArrayList<>();
        String color = Colors.ANSI_RESET;
        output.add(color + "C-----C ");
        output.add(color + "|     | ");
        output.add(color + "C-----C " + Colors.ANSI_RESET); // Reset the color for the next Cell
        return output;
    }
}
