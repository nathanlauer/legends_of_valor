package main;

import main.fight.Fight;
import main.legends.Hero;
import main.legends.LegendList;
import main.legends.Monster;
import main.market_and_gear.Market;
import main.market_and_gear.MarketInteraction;
import main.world.RandomWorldBuilder;
import main.world.World;
import main.world.WorldBuilder;
import main.world.WorldInteraction;

import java.util.Collections;
import java.util.List;

/**
 * Class Main
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/8/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Main {
    public static void main(String[] args) {
        // Create some Heroes and Monsters
//        List<Hero> allHeroes = LegendList.getInstance().getHeroes();
//        List<Monster> allMonsters = LegendList.getInstance().getMonsters();
//        List<Hero> heroes = allHeroes.subList(0, 2);
//        List<Monster> monsters = allMonsters.subList(0, 2);
//
//        // Perform a fight
//        Fight fight = new Fight(heroes, monsters);
//        fight.fight();

        WorldBuilder builder = new RandomWorldBuilder(8, 8, 50, 20, 30);
        World world = new World(builder);
        world.placeHeroes();
        WorldInteraction worldInteraction = new WorldInteraction(world);
        worldInteraction.run();
    }
}
