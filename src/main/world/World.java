package main.world;

import main.attributes.Position;
import main.fight.Fight;
import main.legends.Hero;
import main.legends.LegendList;
import main.utils.Colors;
import main.utils.Validations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public abstract class World implements Drawable {
    private final Cell[][] cells;
    protected HashMap<Hero, Position> heroPositions;
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
        return heroPositions.get(hero).getRow();
    }

    /**
     * @return the col of the Heroes
     */
    protected int getHeroCol(Hero hero) {
        return heroPositions.get(hero).getCol();
    }

    /**
     * Detects whether a hero is in range of an input position
     * @param position
     * @param range
     * @return
     */
    protected boolean isHeroInRange(Position position,int range){

        //check up direction
        for(int i = position.getRow(); i<=Math.min(position.getRow()+range,getCells().length-1);i++){
            if(isHeroInCell(getCellAt(i,position.getCol()))){
                return true;
            }
        }
        //check down direction
        for(int i = position.getRow(); i>=Math.max(position.getRow()-range,0);i--){
            if(isHeroInCell(getCellAt(i,position.getCol()))){
                return true;
            }
        }

        //check left
        for(int i = position.getCol(); i>=Math.max(position.getCol()-range,0);i--){
            if(isHeroInCell(getCellAt(position.getRow(),i))){
                return true;
            }
        }
        //check right
        for(int i = position.getCol(); i<=Math.min(position.getCol()+range,getCells()[0].length-1);i++){
            if(isHeroInCell(getCellAt(position.getRow(),i))){
                return true;
            }
        }

        return false;
    }
    /**
     * Indicates whether or not there is a hero in the passed in Cell
     * @param cell the Cell in question
     * @return true if the Hero is in the cell, false otherwise
     */
    public boolean isHeroInCell(Cell cell) {
        for (Map.Entry<Hero,Position> entry : heroPositions.entrySet()) {
            Position position = entry.getValue();
            if(cell.hasPosition(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the Hero in the passed in Cell, or null if none
     * @param cell the Cell in question
     * @return a contained Hero in the passed Cell, or null if none
     */
    public Hero getHeroInCell(Cell cell) {
        if(!isHeroInCell(cell)) {
            return null;
        }

        for (Map.Entry<Hero,Position> entry : heroPositions.entrySet()) {
            Position position = entry.getValue();
            if(cell.hasPosition(position)) {
                return entry.getKey();
            }
        }
        return null;
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
    public boolean cellIsNonAccessible(int row, int col) {
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
     * Given a cell, draws the middle row for the cell according to the
     * semantics of the specific World.
     * @param cell The cell to be drawn
     * @return String representing the middle row of a Cell.
     */
    public abstract String drawMiddleRow(Cell cell, String color);

    /**
     * Each entry in the returned String is a single line to output.
     *
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
                List<String> drawn = cell.draw();

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
     *
     * @param row the row in question
     * @param col the col in question
     */
    protected void checkValidity(int row, int col) {
        Validations.nonNegative(row, "row");
        Validations.nonNegative(col, "col");
        assert row < numRows();
        assert col < numCols();
    }

    /**
     * Indicates whether or not the passed in Position is valid, meaning
     * that it represents an actual location in the world
     * @param position the Position in question
     * @return true if the passed in Position has a row/col pair that are valid, false otherwise.
     */
    protected boolean isPositionValid(Position position) {
        int row = position.getRow();
        int col = position.getCol();

        if(row < 0 || row >= this.numRows()) {
            return false;
        }

        return col >= 0 && col < numCols();
    }
}
