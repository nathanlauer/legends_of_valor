package main.world;

import main.legends.Hero;
import main.utils.Colors;

import java.util.List;
import java.util.Random;

public class RandomWorld extends World {
    /**
     * Standard constructor, builds a World of the passed in size.
     *
     * @param worldBuilder class which builds this World
     */
    public RandomWorld(WorldBuilder worldBuilder) {
        super(worldBuilder);
    }

    /**
     * Marks the location of the Heroes, by inserting the Heroes into a random
     * cell on the World.
     */
    @Override
    protected void placeHero(Hero hero) {
        boolean foundCommonCell = false;
        Random random = new Random();
        while (!foundCommonCell) {
            int row = random.nextInt(this.numRows());
            for (int col = 0; col < numCols(); col++) {
                Cell cell = getCellAt(row, col);
                if (cell instanceof CommonCell) {
                    foundCommonCell = true;
                    setHeroLocation(hero, row, col);
                    break;
                }
            }
        }
    }

    /**
     * Given a cell, draws the middle row for the cell according to the
     * semantics of the specific World.
     *
     * @param cell The cell to be drawn
     * @return String representing the middle row of a Cell.
     */
    @Override
    public String drawMiddleRow(Cell cell, String color) {
        return color + "|     | "; // TODO: add
    }

    /**
     * Returns a string representation of the implementing entity. If
     * the position of the entity corresponds to (heroesRow,heroesCol), then
     * draws that the Heroes are in that location.
     */
    @Override
    public List<String> draw() {
        return null; // TODO: if time
    }
}
