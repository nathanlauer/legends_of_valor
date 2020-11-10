package main.world;

import main.legends.Hero;
import main.legends.Legend;
import main.legends.LegendList;
import main.legends.Monster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class Heroes is a singleton class that holds the Heroes that are playing this game.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class HeroesAndMonsters {
    private static HeroesAndMonsters instance;
    private final List<Hero> heroes;
    private final List<Monster> monsters;

    /**
     *
     * @return the Singleton instance of this class
     */
    public static HeroesAndMonsters getInstance() {
        if(instance == null) {
            instance = new HeroesAndMonsters();
            instance.getUserNumHeroes();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private HeroesAndMonsters() {
        heroes = LegendList.getInstance().getHeroes().subList(0, 2);
        monsters = LegendList.getInstance().getMonsters();
    }

    /**
     * Prompts the user to choose how many Heroes and Monsters to play with
     */
    private void getUserNumHeroes() {
        // TODO
    }

    /**
     *
     * @return the Heroes in this game
     */
    public List<Hero> getHeroes() {
        return heroes;
    }

    /**
     *
     * @return a list of random Monsters, where the number returned are equal to the number of Heroes
     * For example, if there are three Heroes, returns a random list of three Monsters.
     */
    public List<Monster> getRandomMonsters() {
        Collections.shuffle(monsters);
        List<Monster> output = new ArrayList<>();
        for(int i = 0; i < getHeroes().size(); i++) {
            output.add(monsters.get(i));
        }

        return output;
    }
}
