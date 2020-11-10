package main.world;

import java.util.ArrayList;
import java.util.List;

/**
 * Class NonAccessibleCell is a type of Cell that the Heroes cannot enter
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class NonAccessibleCell extends Cell {
    /**
     * Standard constructor
     * @param row row location of this Cell
     * @param col col location of this Cell
     */
    public NonAccessibleCell(int row, int col) {
        super(row, col);
    }

    /**
     * Indicates whether or not the Heroes can enter this cell
     *
     * @return true if the Heroes can enter this cell, false otherwise
     */
    @Override
    public boolean canEnter() {
        return false;
    }

    /**
     * The Heroes enter this cell, and something happens
     */
    @Override
    public void enter() {
        throw new RuntimeException("Cannot enter this cell!");
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
        if(sameLocation(heroesRow, heroesCol)) {
            throw new RuntimeException("Heroes cannot be in this cell!");
        }

        List<String> output = new ArrayList<>();
        output.add("+-----+");
        output.add("|/ / /|");
        output.add("+-----+");
        return output;
    }
}
