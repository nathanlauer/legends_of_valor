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
     *
     * @return the List of Heroes in this Fight
     */
    public List<Hero> getHeroes() {
        return heroes;
    }

    /**
     *
     * @return the List of Monsters in this Fight
     */
    public List<Monster> getMonsters() {
        return monsters;
    }

    /**
     * Executes the fight
     */
    public void fight() {
        printWelcomeMessage();
        RoundExecutor roundExecutor = new HeroesVsMonstersRound(heroes, monsters);
        RoundBasedGame game = new RoundBasedGame(roundExecutor);
        game.play();
        processEndOfFight();
    }

    /**
     * Private function which prints a welcome message to the user, and explains how the
     * fight will proceed.
     */
    private void printWelcomeMessage() {
        String output = "Oh no! You have encountered some Monsters! It's time to fight them.\n";
        output += "Here is how a fight works:\n";
        output += "Fights proceed in rounds. In each round, every one of your Heroes will \n";
        output += "have an opportunity to make a move. After each move, one of the Monsters \n";
        output += "will in turn attack one of your Heroes. When it is one of your Hero's turns, \n";
        output += "you can choose between attacking, casting a spell, using a potion, switching\n";
        output += "weapons, and switching Armor. Finally, at the beginning of the first round,\n";
        output += "you can match up your Heroes vs these Monsters.\n";
        output += "At the end of each round, all surviving Heroes regain 10% of their Health Power\n";
        output += "and Mana.";
        System.out.println(output);
    }

    private void processEndOfFight() {
        // TODO
    }
}
