package main.world;

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
    public String draw(int heroesRow, int heroesCol) {
        String output = "";
        output += "-----";
        output += "|   |";
        if(sameLocation(heroesRow, heroesCol)) {
            output += "| H |";
        } else {
            output += "| M |";
        }
        output += "|   |";
        output += "-----";
        return output;
    }
}
