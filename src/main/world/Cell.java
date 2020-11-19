package main.world;

import main.attributes.Position;
import main.legends.Hero;
import main.utils.Validations;

import java.util.List;
import java.util.Objects;

/**
 * Class Cell is an abstract class that represents a location in the World.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class Cell implements Drawable {
    public static final int numDrawnRows = 3;
    private int row;
    private int col;

    /**
     * Standard constructor
     * Throws an IllegalArgumentException if either row or col are negative
     * @param row the row for this Cell
     * @param col the col for this Cell
     */
    public Cell(int row, int col) {
        Validations.nonNegative(row, "row");
        Validations.nonNegative(col, "col");
        this.row = row;
        this.col = col;
    }

    /**
     *
     * @return the row of this Cell
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @return the column of this Cell
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets the row of this Cell to the passed in value
     * @param row the new row value for this cell
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Sets the col of this Cell to the passed in value
     * @param col the new column value for this cell
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Indicates if the passed in row and col represent the same location
     * @param row the row in question
     * @param col the col in question
     * @return true if (row/col) are the same as this Cell, false otherwise
     */
    public boolean sameLocation(int row, int col) {
        return row == getRow() && col == getCol();
    }

    /**
     * Indicates whether or not this Cell has the passed in Position
     * @param position the relevant Position
     * @return true if the passed Position has the correct row/col, false otherwise
     */
    public boolean hasPosition(Position position) {
        return sameLocation(position.getRow(), position.getCol());
    }

    /**
     * Indicates if this Cell is just below the Heroes location.
     * @param heroesRow the heroes row
     * @param heroesCol the heroes col
     * @return true if this Cell is just beneath the Heroes location, false otherwise
     */
    public boolean heroesJustAbove(int heroesRow, int heroesCol) {
        if(getCol() != heroesCol) {
            return false;
        }

        return getRow() == (heroesRow + 1);
    }

    /**
     * Indicates whether or not the Hero can enter this cell
     * @return true if the Heroes can enter this cell, false otherwise
     */
    public abstract boolean canEnter(List<Hero> heroes);

    /**
     * The Hero enter this cell, and something happens
     * @param heroes - the heroes to enter this cell
     */
    public abstract void enter(List<Hero> heroes);



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row &&
                col == cell.col;
    }
}
