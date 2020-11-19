package main.world;

import main.Runner;
import main.fight.Fight;
import main.legends.Hero;
import main.legends.LegendList;
import main.legends.Monster;
import main.utils.Colors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class CommonCell is a type of spell which is "normal." There may or may
 * not be Monsters upon entering this cell.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class CommonCell extends Cell {
    public static final int defaultChangeOfMonsters = 50;
    private final int chanceOfMonsters;

    /**
     * Standard constructor. Sets the chance of Monsters to the default of 50%
     * @param row the row location of this Cell
     * @param col the col location of this Cell
     */
    public CommonCell(int row, int col) {
        this(row, col, CommonCell.defaultChangeOfMonsters);
    }

    /**
     * Constructor where the change of Monsters is specified
     * @param row row location of this Cell
     * @param col col location of this Cell
     * @param chanceOfMonsters chance of Monsters being present: on a scale of 0-100.
     */
    public CommonCell(int row, int col, int chanceOfMonsters) {
        super(row, col);
        this.chanceOfMonsters = chanceOfMonsters;
    }

    /**
     * Indicates whether or not the Heroes can enter this cell
     *
     * @return true if the Heroes can enter this cell, false otherwise
     */
    @Override
    public boolean canEnter(List<Hero> heroes) {
        return true;
    }

    /**
     * The Heroes enter this cell, and something happens
     */
    @Override
    public void enter(List<Hero> heroes) {
        int rand = new Random().nextInt(101);
        if(rand < this.chanceOfMonsters) {
            // Fight!
            List<Monster> monsters = LegendList.getInstance().getCorrespondingMonsters();
            Fight fight = new Fight(heroes, monsters);
            World world = Runner.getInstance().getWorld();
            world.setFight(fight);
            fight.fight();
            world.finishedFight();
        }
    }

    /**
     * Returns a string representation of the implementing entity. If
     * the position of the entity corresponds to (heroesRow,heroesCol), then
     * draws that the Heroes are in that location.
     */
    @Override
    public List<String> draw() {
        // TODO: add in Hero and Monster if necessary
        List<String> output = new ArrayList<>();
        String color = Colors.ANSI_RESET;
        output.add(color + "+-----+ ");
        World world = Runner.getInstance().getWorld();
        output.add(world.drawMiddleRow(this, Colors.ANSI_RESET));
        output.add(color + "+-----+ " + Colors.ANSI_RESET); // Reset the color for the next Cell
        return output;
    }
}
