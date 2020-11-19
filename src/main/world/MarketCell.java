package main.world;

import main.Runner;
import main.legends.Hero;
import main.legends.LegendList;
import main.market_and_gear.Market;
import main.market_and_gear.MarketInteraction;
import main.utils.Colors;

import java.util.ArrayList;
import java.util.List;

/**
 * Class MarketCell is a type of cell that contains a Market
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class MarketCell extends Cell {
    /**
     * Standard constructor
     * @param row the row location of this Cell
     * @param col the col location of this Cell
     */
    public MarketCell(int row, int col) {
        super(row, col);
    }

    /**
     * Indicates whether or not the Heroes can enter this cell
     *
     * @return true if the Heroes can enter this cell, false otherwise
     */
    @Override
    public boolean canEnter(List<Hero>heroes) {
        return true;
    }

    /**
     * The Heroes enter this cell, and something happens
     */
    @Override
    public void enter(List<Hero> heroes) {
        Market market = new Market();
        MarketInteraction marketInteraction = new MarketInteraction(market, heroes);
        marketInteraction.run();
    }

    /**
     * Returns a string representation of the implementing entity. If
     * the position of the entity corresponds to (heroesRow,heroesCol), then
     * draws that the Heroes are in that location.
     */
    @Override
    public List<String> draw() {
        List<String> output = new ArrayList<>();
        String color = Colors.ANSI_BLUE;
        output.add(color + "M-----M");
        World world = Runner.getInstance().getWorld();
        output.add(world.drawMiddleRow(this, Colors.ANSI_BLUE));
        output.add(color + "M-----M " + Colors.ANSI_RESET); // Reset the color for the next Cell
        return output;
    }
}
