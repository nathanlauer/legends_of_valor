package main;

import main.fight.Fight;
import main.legends.Hero;
import main.legends.LegendList;
import main.legends.Monster;

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
        List<Hero> allHeroes = LegendList.getInstance().getHeroes();
        List<Monster> allMonsters = LegendList.getInstance().getMonsters();
        List<Hero> heroes = allHeroes.subList(0, 3);
        List<Monster> monsters = allMonsters.subList(0, 3);

        // Perform a fight
        Fight fight = new Fight(heroes, monsters);
        fight.fight();
    }
}
