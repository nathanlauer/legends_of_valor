package main.fight;

import main.attributes.HigherLevelComparator;
import main.games.RoundExecutor;
import main.games.TurnBasedGame;
import main.games.TurnExecutor;
import main.legends.Hero;
import main.legends.Monster;
import test.utils.Output;

import java.util.List;
import java.util.Scanner;

/**
 * Class HeroesVsMonstersRound is a RoundExecutor, and contains the logic for progressing
 * through a single round of a fight between some Heroes and some Monsters.
 *
 * Every round is actually itself a TurnBasedGame. Thus, before each round starts,
 * this class instantiates a new TurnBasedGame, and uses that class to progress
 * through the process of playing a round.
 *
 * When a round completes, any Hero that has not fainted regains some of
 * their HealthPower and Mana.
 *
 * Note: this class is not intended to be instantiated to perform a single round
 * of play. Rather, it is expected to be used within the strategy pattern -
 * a single instance of this class is instantiated for some RoundBasedGame,
 * and that RoundBasedGame then uses the logic in each of the methods here
 * to actually progress through all the rounds in the Game.
 *
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/4/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class HeroesVsMonstersRound implements RoundExecutor {
    private final List<Hero> heroes;
    private final List<Monster> monsters;
    private final TurnBasedGame turnBasedGame;
    private final PairHeroesAndMonsters pairing;
    private boolean firstRound;
    private int roundNum;
    private final TurnExecutor turnExecutor;

    /**
     * Standard constructor for a HeroVsMonsterRound
     * @param heroes List of Heroes that are in this fight
     * @param monsters List of Monsters that are in this fight
     */
    public HeroesVsMonstersRound(List<Hero> heroes, List<Monster> monsters) {
        this.heroes = heroes;
        this.monsters = monsters;
        this.pairing = new PairHeroesAndMonsters(new Scanner(System.in), this.heroes, this.monsters);
        this.firstRound = true;
        roundNum = 1;
        turnExecutor = new HeroesVsMonstersTurn(this.heroes, this.monsters, this.pairing);
        this.turnBasedGame = new TurnBasedGame(turnExecutor);
    }

    /**
     * Performs any preprocessing required before the start of a round.
     */
    @Override
    public void setupNextRound() {
        // Setup a Heroes vs Monsters turn based game for the next round.
        turnExecutor.reset();

        displayStatus();

        if(firstRound) {
            // Prompt the user to setup a pairing between Heroes and Monsters
            System.out.println("Match up your Heroes vs these Monsters:");
            pairing.initialPairing();
            Output.printSeparator();

            firstRound = false;
        }
    }

    /**
     * Outputs the status of the Heroes and Monsters left in this Fight
     */
    private void displayStatus() {
        // Sort Heroes and Monsters so the ones with higher Level appear at the top
        heroes.sort(new HigherLevelComparator());
        monsters.sort(new HigherLevelComparator());

        // Display the Heroes and the Monsters
        Output.printSeparator();
        System.out.println("Start of round " + roundNum);
        System.out.println("Legend Status");
        System.out.println();
        Output.printOutputables(heroes);
        System.out.println();
        Output.printOutputables(monsters);
        Output.printSeparator();
    }

    /**
     * Method which encapsulates the logic for proceeding through a single round of the game.
     */
    @Override
    public void playRound() {
        // Since each round is effectively just a turn based game, we defer the playing of
        // this round to the turn based game.
        this.turnBasedGame.play();
    }

    /**
     * Performs any required processing at the conclusion of a round of play.
     */
    @Override
    public void processEndOfRound() {
        System.out.println("End of round " + roundNum);
        for(Hero hero : this.heroes) {
            if(!hero.hasFainted()) {
                System.out.println(hero.getName() + " is still alive!");

                // Surviving Heroes regain 10% of their health power
                if(hero.getHealthPower().isFull()) {
                    System.out.println(hero.getName() + "'s health power is full.");
                } else {
                    System.out.println(hero.getName() + " has regained 10% of their health power.");
                    hero.getHealthPower().increaseByPercentageOfFull(10);
                }

                // And 10% of their Mana
                if(hero.getMana().isFull()) {
                    System.out.println(hero.getName() + "'s mana is full.");
                } else {
                    System.out.println(hero.getName() + " has regained 10% of their mana.");
                    hero.getMana().increaseByPercentageOfFull(10);
                }
            }
        }

        roundNum++;
    }

    /**
     * Indicates whether or not the Game has ended. Implementing subclasses are expected
     * to build the appropriate logic into this method, such that when RoundBasedGame
     * invokes this method after invoking playRound, the implementing class will know
     * if the last played round represents the end of the Game.
     *
     * @return true if the last call to playRound() resulted in a situation where the Game
     * has finished, false otherwise.
     */
    @Override
    public boolean finishedGame() {
        // The Game has finished if either all Heroes have fainted,
        boolean heroesAlive = false;
        for(Hero hero : this.heroes) {
            if(!hero.hasFainted()) {
                heroesAlive = true;
                break;
            }
        }

        // or all Monsters have no HealthPower
        boolean monstersAlive = false;
        for(Monster monster : this.monsters) {
            if(monster.getHealthPower().getHealthPower() > 0) {
                monstersAlive = true;
                break;
            }
        }

        return !heroesAlive || !monstersAlive;
    }
}
