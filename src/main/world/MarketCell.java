package main.world;

import main.utils.Colors;

import java.util.ArrayList;
import java.util.List;

/**
 * Class MarketCell is a type of cell that contains a Market
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class MarketCell extends Cell {
    /**
     * Standard constructor
     * @param row the row location of this Cell
     * @param col the col location of this Cell
     */
    public MarketCell(int row, int col) {
        super(row, col);
    }

    /**
     * Indicates whether or not the Heroes can enter this cell
     *
     * @return true if the Heroes can enter this cell, false otherwise
     */
    @Override
    public boolean canEnter() {
        return true;
    }

    /**
     * The Heroes enter this cell, and something happens
     */
    @Override
    public void enter() {
        // TODO
    }

    /**
     * Returns a string representation of the implementing entity. If
     * the position of the entity corresponds to (heroesRow,heroesCol), then
     * draws that the Heroes are in that location.
     *
     * @param heroesRow location of the Heroes as a row
     * @param heroesCol location of the Heroes as a column
     */
    @Override
    public List<String> draw(int heroesRow, int heroesCol) {
        String color = sameLocation(heroesRow, heroesCol) ? Colors.ANSI_GREEN : Colors.ANSI_BLACK;
        List<String> output = new ArrayList<>();
        output.add(color + "+-----+");
        if(sameLocation(heroesRow, heroesCol)) {
            output.add(color + "|  H  |");
        } else {
            output.add(color + "|  " + Colors.ANSI_BLUE + "M" + Colors.ANSI_BLACK + "  |");
        }
        return output;
    }
}
