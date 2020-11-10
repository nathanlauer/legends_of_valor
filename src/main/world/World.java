package main.world;

import main.utils.Validations;

import java.util.ArrayList;
import java.util.List;
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

    /**
     *
     * @return the row of the Heroes
     */
    public int getHeroesRow() {
        return heroesRow;
    }

    /**
     *
     * @return the col of the Heroes
     */
    public int getHeroesCol() {
        return heroesCol;
    }

    public boolean canMove(Direction direction) {
        boolean allowed = false;
        switch (direction) {
            case UP:
                allowed = getHeroesRow() > 0;
                break;
            case DOWN:
                allowed = getHeroesRow() < numRows() - 1;
                break;
            case LEFT:
                allowed = getHeroesCol() > 0;
                break;
            case RIGHT:
                allowed = getHeroesCol() < numCols() - 1;
                break;
            default:
                throw new RuntimeException("Unknown direction!");
        }
        return allowed;
    }

    /**
     * Moves the Heroes to their new location.
     * Enters the Cell at the new location, which may cause certain events to occur.
     * @param direction the direction to move in
     * @throws InvalidMoveDirection if unable to move in the desired direction.
     */
    public void move(Direction direction) throws InvalidMoveDirection {
        if(!canMove(direction)) {
            throw new InvalidMoveDirection("Cannot move in this direction!");
        }

        switch (direction) {
            case UP:
                this.setHeroesLocation(getHeroesRow() - 1, getHeroesCol());
                break;
            case DOWN:
                this.setHeroesLocation(getHeroesRow() + 1, getHeroesCol());
                break;
            case LEFT:
                this.setHeroesLocation(getHeroesRow(), getHeroesCol() - 1);
                break;
            case RIGHT:
                this.setHeroesLocation(getHeroesRow(), getHeroesCol() + 1);
                break;
            default:
                throw new InvalidMoveDirection("Unknown move direction!");
        }

        // Now, enter the new Cell
        Cell cell = getCellAt(getHeroesRow(), getHeroesCol());
        if(!cell.canEnter()) {
            throw new InvalidMoveDirection("Unable to enter the cell!");
        }
        cell.enter(); // may cause certain events to occur, like entering a Market or starting a Fight.
    }

    /**
     * Each entry in the returned String is a single line to output.
     * @return a string representation of this World.
     */
    public List<String> draw() {
        List<String> output = new ArrayList<>();
        for(int i = 0; i < Cell.numDrawnRows * numRows(); i++) {
            output.add("");
        }

        for(int row = 0; row < numRows(); row++) {
            for(int col = 0; col < numCols(); col++) {
                Cell cell = getCellAt(row, col);
                List<String> drawn = cell.draw(getHeroesRow(), getHeroesCol());

                // Append each element of drawn to the correct location of output
                for(int i = 0; i < drawn.size(); i++) {
                    int index = i + Cell.numDrawnRows * row;
                    String oneDrawnRow = drawn.get(i);
                    String current = output.get(index);
                    current += oneDrawnRow;
                    output.set(index, current);
                }
            }
        }

        return output;
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
