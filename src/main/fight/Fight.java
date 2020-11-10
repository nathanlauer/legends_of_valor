package main.fight;

import main.games.RoundBasedGame;
import main.games.RoundExecutor;
import main.legends.Hero;
import main.legends.Monster;
import main.utils.Output;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
    private final PairHeroesAndMonsters pairing;
    private final HashMap<Hero, Integer> pairedMonsterLevels;

    /**
     * Standard constructor
     * @param heroes List of Heroes in this Fight
     * @param monsters List of Monsters in this Fight
     */
    public Fight(List<Hero> heroes, List<Monster> monsters) {
        this.heroes = heroes;
        this.monsters = monsters;
        this.pairing = new PairHeroesAndMonsters(new Scanner(System.in), this.heroes, this.monsters);
        this.pairedMonsterLevels = new HashMap<>();
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
        RoundExecutor roundExecutor = new HeroesVsMonstersRound(heroes, monsters, pairing);
        RoundBasedGame game = new RoundBasedGame(roundExecutor);

        // Set up the initial pairing
        System.out.println("Match up your Heroes vs these Monsters:");
        pairing.initialPairing();
        Output.printSeparator();

        // Extract Monster levels for Hero exp gain at end of fight.
        buildMonsterLevelMap();

        // Play the game
        game.play();
        processEndOfFight();
    }

    /**
     * Builds the map of Heroes to the Levels of Monsters they are facing.
     * We build this initially, before the fight, because the Monsters that a
     * Hero faces may change during a fight. However, should such a scenario
     * occur, we don't actually count that towards their experience. We only
     * count the Levels of the Monsters they initially face.
     */
    private void buildMonsterLevelMap() {
        for(Hero hero : heroes) {
            List<Monster> monsters = pairing.getMonstersForHero(hero);
            int levelSum = 0;
            for(Monster monster : monsters) {
                levelSum += monster.getLevel().getLevel();
            }
            pairedMonsterLevels.put(hero, levelSum);
        }
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

    /**
     * At the end of the fight, surviving Heroes gain some coins and exp, and
     * fainted Heroes are revived at half their health power level
     */
    private void processEndOfFight() {
        // Each surviving Hero gains 100 * monster level coins, and 2 exp.
        for(Hero hero : heroes) {
            if(!hero.hasFainted()) {
                int gainedCoins = 100 * pairedMonsterLevels.get(hero);
                hero.getCoffer().addCoins(gainedCoins);
            }
        }

        // Revive fainted Heroes by giving them half their health power
        for(Hero hero : heroes) {
            if(hero.hasFainted()) {
                double halfHp = hero.getHealthPower().getFullAmount() / 2.0;
                hero.getHealthPower().setHealthPower(halfHp);
            }
        }
    }
}
