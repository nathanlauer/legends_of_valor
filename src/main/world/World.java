package main.world;

import main.utils.Validations;

import java.util.Random;

/**
 * Class World represents the world. It is composed of a series of Cells, and allows
 * Heroes to move throughout.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class World {
    private final Cell[][] cells;
    private int heroesRow;
    private int heroesCol;

    /**
     * Standard constructor, builds a World of the passed in size.
     * @param worldBuilder class which builds this World
     */
    public World(WorldBuilder worldBuilder) {
        this.cells = worldBuilder.run();
        heroesRow = 0;
        heroesCol = 0;
    }

    /**
     *
     * @return the number of rows in this World.
     */
    private int numRows() {
        return this.cells.length;
    }

    /**
     *
     * @return the number of columns in this World.
     */
    private int numCols() {
        return this.cells[0].length;
    }

    /**
     *
     * @return the Cells composing this World
     */
    public Cell[][] getCells() {
        return cells;
    }

    /**
     * Returns the Cell corresponding to the passed in row and col
     * @param row the requested row
     * @param col requested col
     * @return Cell at the (row/col) location
     */
    public Cell getCellAt(int row, int col) {
        checkValidity(row, col);
        return this.cells[row][col];
    }

    /**
     * Marks the location of the Heroes, by inserting the Heroes into a random
     * cell on the World.
     */
    public void placeHeroes() {
        boolean foundCommonCell = false;
        Random random = new Random();
        while(!foundCommonCell) {
            int row = random.nextInt(this.numRows());
            for(int col = 0; col < numCols(); col++) {
                Cell cell = getCellAt(row, col);
                if(cell instanceof CommonCell) {
                    foundCommonCell = true;
                    this.heroesRow = row;
                    this.heroesCol = col;
                    break;
                }
            }
        }
    }

    /**
     * Sets the Heroes location
     * @param row the new row for the Heroes
     * @param col the new col for the Heroes
     */
    public void setHeroesLocation(int row, int col) {
        checkValidity(row, col);
        this.heroesRow = row;
        this.heroesCol = col;
    }

    public void canMove(Direction direction) {
        // TODO
    }

    public void move(Direction direction) throws InvalidMoveDirection {
        // TODO
    }

    /**
     *
     * @return a string representation of this World.
     */
    public String draw() {
        return ""; // TODO
    }

    /**
     * Ensures that row and col are not negative, and within a valid range.
     * @param row the row in question
     * @param col the col in question
     */
    private void checkValidity(int row, int col) {
        Validations.nonNegative(row, "row");
        Validations.nonNegative(col, "col");
        assert row < numRows();
        assert col < numCols();
    }
}
