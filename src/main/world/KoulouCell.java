package main.world;

import main.Runner;
import main.legends.Hero;
import main.utils.Colors;

import java.util.ArrayList;
import java.util.List;

/**
 * Koulou cells buff the strength of any hero who is inside them by 10%. 
 * This boost persists for each round that the hero stays in this cell. 
 * @author Sandra Zhen
 */
public class KoulouCell extends Cell{
    /**
     * Standard constructor
     * Throws an IllegalArgumentException if either row or col are negative
     *
     * @param row the row for this Cell
     * @param col the col for this Cell
     */
    public KoulouCell(int row, int col) {
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
    		hero.getStrength().increaseAbilityByPercentage(10);
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
        output.add(color + "K-----K ");
        World world = Runner.getInstance().getWorld();
        output.add(world.drawMiddleRow(this, Colors.ANSI_RESET));
        output.add(color + "K-----K " + Colors.ANSI_RESET); // Reset the color for the next Cell
        return output;
    }

	@Override
	public void exit(List<Hero> heroes) {
		// TODO Auto-generated method stub
		for(Hero hero: heroes) {
			double amount = hero.getStrength().getAbilityValue()/1.1;
			hero.getStrength().decreaseAbilityBy(amount);
		}
		
	}
}
