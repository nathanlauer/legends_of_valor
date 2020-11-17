package main.games;

import main.Runner;
import main.legends.Hero;
import main.legends.Legend;
import main.legends.Monster;

import java.util.ArrayList;
import java.util.List;

/**
 * Class LegendsOfValorRound implements RoundExecutor,
 * making it a RoundBasedGame, and contains the logic for
 * executing a round of the game Legends of Valor. Unlike the previous game
 * of Monsters and Heroes, this game is not limited to just a fight. In fact,
 * this game is "the entire game." That is, with Monsters and Legends we implemented
 * a part of the overall structure with a RoundBasedGame, that being the fight, but
 * here, we implement all actions within Valor as part of this RoundBasedGame.
 *
 * Each round proceeds as a TurnBasedGame, which switches between actions for
 * Heroes and for Monsters. At the end of the round, surviving Heroes regain
 * 10% of their HP and Mana, and fainted heroes are respawned in their Nexus.
 *
 * Further, when a multiple of 8 rounds has been played, 3 new Monsters are
 * spawned in their Nexus.
 *
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/17/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class LegendsOfValorRound implements RoundExecutor {
    public static final int roundsToNewMonsters = 8;
    private final List<Legend> heroes;
    private final List<Legend> monsters;
    private final TurnBasedGame turnBasedGame;
    private final TurnExecutor turnExecutor;
    private int roundNum;
    private int numRoundsToNewMonsters;

    /**
     * Empty constructor
     * Obtains the list of Heroes and Monsters from the World, and
     * uses the default number of rounds.
     */
    public LegendsOfValorRound() {
        this(LegendsOfValorRound.roundsToNewMonsters);
    }

    /**
     * Constructs a LegendsOfValorRound with the specified number of rounds to new Monsters.
     * Heroes and Monsters are obtained from the world.
     * @param roundsToNewMonsters number of rounds until new Monsters are spawned.
     */
    public LegendsOfValorRound(int roundsToNewMonsters) {
        this(new ArrayList<>(), new ArrayList<>(), roundsToNewMonsters);

        // TODO: once world or world interaction built for valor, call these methods
        //heroes = Runner.getInstance().getWorld().getHeroes();
        //monsters = Runner.getInstance().getWorld().getMonsters();
    }

    /**
     * Standard constructor
     * @param heroes the Heroes that are involved in this game
     * @param monsters the Monsters that are involved in this game (may change as the game progresses)
     * @param roundsToNewMonsters number of rounds until new Monsters are spawned.
     */
    public LegendsOfValorRound(List<Legend> heroes, List<Legend> monsters, int roundsToNewMonsters) {
        this.heroes = heroes;
        this.monsters = monsters;
        this.turnExecutor = new LegendsOfValorTurn(); // TODO: arguments?
        this.turnBasedGame = new TurnBasedGame(turnExecutor);
        this.numRoundsToNewMonsters = roundsToNewMonsters;
        roundNum = 0;
    }

    /**
     * Performs any preprocessing required before the start of a round.
     */
    @Override
    public void setupNextRound() {
        turnExecutor.reset();
        roundNum++;
        // TODO: some status to display?
    }

    /**
     * Method which encapsulates the logic for proceeding through a single round of the game.
     */
    @Override
    public void playRound() {
        // Each round is a turn based game, so paying a round is as simple as
        // calling the play() method on the turn based game defined here.
        turnBasedGame.play();
    }

    /**
     * Performs any required processing at the conclusion of a round of play.
     */
    @Override
    public void processEndOfRound() {
        System.out.println("End of round " + roundNum);
        processSurvivingHeroes();
        respawnFaintedHeroes();
        spawnMonstersIfNecessary();
    }

    /**
     * Helper function which process each surviving Hero.
     * Specifically, each surviving Hero regains 10% of their HealthPower
     * and 10% of their Mana.
     */
    private void processSurvivingHeroes() {
        for(Legend legend : this.heroes) {
            Hero hero = (Hero)legend;
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
    }

    /**
     * Helper function which respawns all Heroes that have fainted in their
     * respective Nexus.
     */
    private void respawnFaintedHeroes() {
        for(Legend legend : heroes) {
            Hero hero = (Hero)legend;
            if(hero.hasFainted()) {
                System.out.println(hero.getName() + " has fainted, and will be respawned in their Nexus.");
                // TODO: respawn in Nexus
            }
        }
    }

    /**
     * Private helper function which spawns Monsters after
     * some number of rounds have passed.
     */
    private void spawnMonstersIfNecessary() {
        if(roundNum % numRoundsToNewMonsters == 0) {
            System.out.println("New Monsters have been spawned in their Nexus!");
            // TODO: spawn new Monsters in each Nexus
        }
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
        // TODO:
        return false;
    }
}
