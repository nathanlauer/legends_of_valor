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
//        // Create some Heroes and Monsters
//        List<Hero> allHeroes = LegendList.getInstance().getHeroes();
//        List<Monster> allMonsters = LegendList.getInstance().getMonsters();
//        List<Hero> heroes = allHeroes.subList(0, 1);
//        List<Monster> monsters = allMonsters.subList(0, 1);
//
//        // For simplicity, add 1000 to the strength of the first hero
//        heroes.get(0).getStrength().increaseAbilityBy(4000);
//
//        Market market = new Market();
//        MarketInteraction marketInteraction = new MarketInteraction(market, heroes);
//        marketInteraction.run();
//
//        // Perform a fight
//        Fight fight = new Fight(heroes, monsters);
//        fight.fight();

        WorldBuilder builder = new RandomWorldBuilder(8, 8, 50, 20, 30);
        World world = new World(builder);
        world.placeHeroes();
        List<String> drawn = world.draw();
        drawn.forEach(System.out::println);
    }
}
