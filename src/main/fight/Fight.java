package main.fight;

import main.games.RoundBasedGame;
import main.games.RoundExecutor;
import main.legends.Hero;
import main.legends.Monster;

import java.util.List;

/**
 * Class Fight is the class that coordinates a fight between Heroes and Monsters
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/8/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Fight {
    private final List<Hero> heroes;
    private final List<Monster> monsters;

    /**
     * Standard constructor
     * @param heroes List of Heroes in this Fight
     * @param monsters List of Monsters in this Fight
     */
    public Fight(List<Hero> heroes, List<Monster> monsters) {
        this.heroes = heroes;
        this.monsters = monsters;
    }

    /**
     * Executes the fight
     */
    public void fight() {
        RoundExecutor roundExecutor = new HeroesVsMonstersRound(heroes, monsters);
        RoundBasedGame game = new RoundBasedGame(roundExecutor);
        game.play();
        processEndOfFight();
    }

    private void processEndOfFight() {
        // TODO
    }
}
