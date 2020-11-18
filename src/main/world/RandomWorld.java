package main.world;

import main.legends.Hero;

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

}
