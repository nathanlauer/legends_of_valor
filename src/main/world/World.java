package main.world;

import main.fight.Fight;
import main.legends.Hero;
import main.legends.Legend;
import main.legends.LegendList;
import main.utils.Colors;
import main.utils.Validations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
public abstract class World {
    private final Cell[][] cells;
    private HashMap<Hero,Position> heroPositions;

    private Fight fight;

    /**
     * Standard constructor, builds a World of the passed in size.
     *
     * @param worldBuilder class which builds this World
     */
    public World(WorldBuilder worldBuilder) {
        this.cells = worldBuilder.run();
        heroPositions = new HashMap<>();
    }

    /**
     * @return the number of rows in this World.
     */
    protected int numRows() {
        return this.cells.length;
    }

    /**
     * @return the number of columns in this World.
     */
    protected int numCols() {
        return this.cells[0].length;
    }

    /**
     * @return the Cells composing this World
     */
    protected Cell[][] getCells() {
        return cells;
    }

    /**
     * Returns the Cell corresponding to the passed in row and col
     *
     * @param row the requested row
     * @param col requested col
     * @return Cell at the (row/col) location
     */
    public Cell getCellAt(int row, int col) {
        checkValidity(row, col);
        return this.cells[row][col];
    }

    /**
     * @return the ongoing fight in this World
     */
    public Fight getFight() {
        return fight;
    }

    /**
     * Sets the ongoing fight in this World
     *
     * @param fight the current Fight
     */
    public void setFight(Fight fight) {
        this.fight = fight;
    }

    /**
     * Marks that the current fight has finished
     */
    public void finishedFight() {
        fight = null;
    }

    /**
     * Indicates whether or not a fight is currently happening
     *
     * @return true if a fight is ongoing, false otherwise.
     */
    public boolean isFighting() {
        return fight != null;
    }

    /**
     * Place hero initially
     * @param hero
     */
    protected abstract void placeHero(Hero hero);
    public void placeHeroes(List<Hero> heroes) {
        for(Hero hero:heroes){
            placeHero(hero);
        }
    }

    /**
     * Sets the Heroes location
     * @param hero - identifier for hero
     * @param row the new row for the hero
     * @param col the new col for the hero
     */
    public void setHeroLocation(Hero hero,int row, int col) {
        checkValidity(row, col);
        heroPositions.put(hero,new Position(row,col));
    }

    /**
     * @return the row of the Heroes
     */
    protected int getHeroRow(Hero hero) {
        return heroPositions.get(hero).getPositionRow();
    }

    /**
     * @return the col of the Heroes
     */
    protected int getHeroCol(Hero hero) {
        return heroPositions.get(hero).getPositionCol();
    }

    public boolean canMove(Hero hero,Direction direction) {
        boolean allowed = false;
        switch (direction) {
            case UP:
                allowed = getHeroRow(hero) > 0 && !cellIsNonAccessible(getHeroRow(hero) - 1, getHeroCol(hero));
                break;
            case DOWN:
                allowed = getHeroRow(hero) < numRows() - 1 && !cellIsNonAccessible(getHeroRow(hero) + 1, getHeroCol(hero));
                break;
            case LEFT:
                allowed = getHeroCol(hero) > 0 && !cellIsNonAccessible(getHeroRow(hero), getHeroCol(hero) - 1);
                break;
            case RIGHT:
                allowed = getHeroCol(hero) < numCols() - 1 && !cellIsNonAccessible(getHeroRow(hero), getHeroCol(hero) + 1);
                break;
            default:
                throw new RuntimeException("Unknown direction!");
        }
        return allowed;
    }

    /**
     * Returns true if all heroes can move in a direction
     * @param direction
     * @return
     */
    public boolean canMove(Direction direction){
        for(Hero hero:LegendList.getInstance().getChosenHeroes()){
            if(!canMove(hero,direction)){
                return false;
            }
        }
        return true;
    }

    /**
     * Indicates if the cell at the given spot is a NonAccessibleCell
     *
     * @param row the row in question
     * @param col the col in question
     * @return true if this cell is NonAccessible, false otherwise
     */
    private boolean cellIsNonAccessible(int row, int col) {
        Cell cell = this.getCellAt(row, col);
        return cell instanceof NonAccessibleCell;
    }

    /**
     * Moves the Heroes to their new location.
     * Enters the Cell at the new location, which may cause certain events to occur.
     *
     * @param direction the direction to move in
     * @throws InvalidMoveDirection if unable to move in the desired direction.
     */
    public void move(Hero hero,Direction direction) throws InvalidMoveDirection {
        if (!canMove(hero,direction)) {
            throw new InvalidMoveDirection("Cannot move in this direction!");
        }

        switch (direction) {
            case UP:
                this.setHeroLocation(hero,getHeroRow(hero) - 1, getHeroCol(hero));
                break;
            case DOWN:
                this.setHeroLocation(hero,getHeroRow(hero) + 1, getHeroCol(hero));
                break;
            case LEFT:
                this.setHeroLocation(hero,getHeroRow(hero), getHeroCol(hero) - 1);
                break;
            case RIGHT:
                this.setHeroLocation(hero,getHeroRow(hero), getHeroCol(hero) + 1);
                break;
            default:
                throw new InvalidMoveDirection("Unknown move direction!");
        }

        // Now, enter the new Cell
        Cell cell = getCellAt(getHeroRow(hero), getHeroCol(hero));
        List<Hero> heroList = new ArrayList<>();
        heroList.add(hero);
        if (!cell.canEnter(heroList)) {
            throw new InvalidMoveDirection("Unable to enter the cell!");
        }
        cell.enter(heroList); // may cause certain events to occur, like entering a Market or starting a Fight.
    }

    /**
     * Moves all chosen heroes in direction
     * @param direction
     * @throws InvalidMoveDirection
     */
    public void move(Direction direction) throws InvalidMoveDirection{
        for(Hero hero: LegendList.getInstance().getChosenHeroes()){
            move(hero,direction);
        }
    }

    /**
     * Each entry in the returned String is a single line to output.
     *
     * @return a string representation of this World.
     */
    public List<String> draw() {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < Cell.numDrawnRows * numRows(); i++) {
            output.add("");
        }

        for (int row = 0; row < numRows(); row++) {
            for (int col = 0; col < numCols(); col++) {
                Cell cell = getCellAt(row, col);
                List<String> drawn = cell.draw(getHeroRow(hero), getHeroCol(hero));

                // Append each element of drawn to the correct location of output
                for (int i = 0; i < drawn.size(); i++) {
                    int index = i + Cell.numDrawnRows * row;
                    String oneDrawnRow = drawn.get(i);
                    String current = output.get(index);
                    current += oneDrawnRow;
                    output.set(index, current);
                }
            }
        }

        String color = Colors.ANSI_RESET;
        StringBuilder lastRow = new StringBuilder(color + "");
        for (int i = 0; i < numCols(); i++) {
            if (getHeroRow(hero) == numRows() - 1) {
                if (getHeroRow(hero) == i) {
                    color = Colors.ANSI_GREEN;
                }
            }
            lastRow.append(color).append("+-----+");
            if (color.equals(Colors.ANSI_GREEN)) {
                color = Colors.ANSI_RESET;
            }
        }
        output.add(lastRow.toString());

        return output;
    }

    /**
     * Ensures that row and col are not negative, and within a valid range.
     *
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
