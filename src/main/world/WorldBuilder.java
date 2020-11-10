package main.world;

import main.utils.Validations;

/**
 * Class WorldBuilder is a class that builds a Board for this world. It ensures
 * that at least one CommonCell and one Market are contained.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class WorldBuilder {
    private final int numRows;
    private final int numCols;

    public WorldBuilder(int numRows, int numCols) {
        Validations.nonNegative(numRows, "numRows");
        Validations.nonNegative(numCols, "numCols");
        this.numRows = numRows;
        this.numCols = numCols;
    }

    /**
     *
     * @return the number of rows to build
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     *
     * @return the number of columns to build
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * Builds a world composed of Cells, and returns it. The returned world will have at least
     * one CommonCell and at least one MarketCell.
     * @return the list of Cells composing this world
     */
    public Cell[][] run() {
        boolean succeeded = false;
        Cell[][] cells = null;
        while(!succeeded) {
            cells = build();
            succeeded = this.builtSuccessfulWorld(cells);
        }
        return cells;
    }

    /**
     * Helper method which actually performs the building
     * @return a two dimensional array of generated Cells
     */
    public abstract Cell[][] build();

    /**
     * Abstract method which allows subclasses to check if the specific kind of World built was successfuly.
     * @param cells the Cells constructed
     * @return true if the World was successfully built, false otherwise.
     */
    public abstract boolean builtSuccessfulWorld(Cell[][] cells);
}
