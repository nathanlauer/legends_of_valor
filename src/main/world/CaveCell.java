package main.world;

/**
 * Cave cells boost the agility of any hero who is inside them by 10%, which also
 * persists for each round that the hero stays in this cell. 
 * @author Sandra Zhen
 */
import main.Runner;
import main.legends.Hero;
import main.utils.Colors;

import java.util.ArrayList;
import java.util.List;

public class CaveCell extends Cell{
    /**
     * Standard constructor
     * Throws an IllegalArgumentException if either row or col are negative
     *
     * @param row the row for this Cell
     * @param col the col for this Cell
     */
    public CaveCell(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean canEnter(List<Hero> heroes) {
        // TODO: check if there is another Hero in this cell already
        return true;
    }

    @Override
    public void enter(List<Hero> heroes) {
    	for(Hero hero: heroes) {
    		hero.getAgility().increaseAbilityByPercentage(10);
    	}
    }

    /**
     * Returns a string representation of the implementing entity. If
     * the position of the entity corresponds to (heroesRow,heroesCol), then
     * draws that the Heroes are in that location.
     */
    @Override
    public List<String> draw() {
        List<String> output = new ArrayList<>();
        String color = Colors.ANSI_RESET;
        output.add(color + "C-----C ");
        World world = Runner.getInstance().getWorld();
        output.add(world.drawMiddleRow(this, Colors.ANSI_RESET));
        output.add(color + "C-----C " + Colors.ANSI_RESET); // Reset the color for the next Cell
        return output;
    }

	@Override
	public void exit(List<Hero> heroes) {
		for(Hero hero: heroes) {
			double originalVal = hero.getAgility().getAbilityValue()/1.1;
            hero.getAgility().setAbilityValue(originalVal);
    	}
	}
}
