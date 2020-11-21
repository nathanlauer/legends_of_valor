package main.world;

import main.legends.Hero;
import main.utils.Colors;

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
    public boolean canEnter(List<Hero> heroes) {
        return false;
    }

    /**
     * The Heroes enter this cell, and something happens
     */
    @Override
    public void enter(List<Hero> heroes) {
        throw new RuntimeException("Cannot enter this cell!");
    }

    /**
     * Returns a string representation of the implementing entity. If
     * the position of the entity corresponds to (heroesRow,heroesCol), then
     * draws that the Heroes are in that location.
     */
    @Override
    public List<String> draw() {
        List<String> output = new ArrayList<>();
        String color = Colors.ANSI_GRAY;
        output.add(color + "+-----+ ");
        output.add(color + "|/////| ");
        output.add(color + "+-----+ " + Colors.ANSI_RESET); // Reset the color for the next Cell
        return output;
    }

	@Override
	public void exit(List<Hero> heroes) {
		// nothing to do
	}
}
