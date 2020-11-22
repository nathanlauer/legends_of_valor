package main.games;

import main.Runner;
import main.legends.Hero;
import main.legends.Legend;
import main.legends.LegendList;
import main.legends.Monster;
import main.world.ValorWorld;

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
    public static final int roundsToNewMonsters = 2;
    public static final int numMonstersToSpawn = 3;
    private final List<Hero> heroes;
    private final List<Monster> monsters;
    private TurnBasedGame turnBasedGame;
    private TurnExecutor turnExecutor;
    private int roundNum;
    private final int numRoundsToNewMonsters;
    private boolean firstRound;

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
    }

    /**
     * Standard constructor
     * @param heroes the Heroes that are involved in this game
     * @param monsters the Monsters that are involved in this game (may change as the game progresses)
     * @param roundsToNewMonsters number of rounds until new Monsters are spawned.
     */
    public LegendsOfValorRound(List<Hero> heroes, List<Monster> monsters, int roundsToNewMonsters) {
        this.heroes = heroes;
        this.monsters = monsters;
        this.firstRound = true;
        this.turnExecutor = new LegendsOfValorTurn(heroes, monsters, firstRound);
        this.turnBasedGame = new TurnBasedGame(turnExecutor);
        this.numRoundsToNewMonsters = roundsToNewMonsters;
        roundNum = 0;
    }

    /**
     * Performs any preprocessing required before the start of a round.
     */
    @Override
    public void setupNextRound() {
        this.turnExecutor = new LegendsOfValorTurn(heroes, monsters, firstRound);
        this.turnBasedGame = new TurnBasedGame(turnExecutor);
        roundNum++;
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
        removeDeadMonsters();
        this.firstRound = false;
    }

    /**
     * Helper function which process each surviving Hero.
     * Specifically, each surviving Hero regains 10% of their HealthPower
     * and 10% of their Mana.
     */
    private void processSurvivingHeroes() {
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
    }

    /**
     * Helper function which respawns all Heroes that have fainted in their
     * respective Nexus.
     */
    private void respawnFaintedHeroes() {
        ValorWorld world = (ValorWorld)Runner.getInstance().getWorld();
        for(Hero hero : heroes) {
            if(hero.hasFainted()) {
                System.out.println(hero.getName() + " has fainted, and will be respawned in their Nexus.");
                world.respawnHero(hero);
            }
        }
    }

    /**
     * Removes any dead Monsters
     */
    private void removeDeadMonsters() {
        for(Monster monster : monsters) {
            if(!monster.isAlive()) {
//                LegendList.getInstance().removeMonsterFromActive(monster);
                ValorWorld world = (ValorWorld)Runner.getInstance().getWorld();
                world.removeDeadMonster(monster);
            }
        }
    }

    /**
     * Private helper function which spawns Monsters after
     * some number of rounds have passed.
     */
    private void spawnMonstersIfNecessary() {
        if(roundNum != 0 && roundNum % numRoundsToNewMonsters == 0) {
            System.out.println("Spawning new Monsters!");
            ValorWorld world = (ValorWorld) Runner.getInstance().getWorld();
            System.out.println("New Monsters have been spawned in their Nexus!");
            for(int i = 0; i < LegendsOfValorRound.numMonstersToSpawn; i++) {
                Monster monster = LegendList.getInstance().spawnNewMonster();
                world.addNewlySpawnedMonster(monster);
            }
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
        ValorWorld world = (ValorWorld)Runner.getInstance().getWorld();
        if(world.heroInMonstersNexus()) {
            System.out.println("A Hero has made it to the Monsters nexus and so the Heroes win! Congratulations!");
            return true;
        }

        if(world.monsterInHeroesNexus()) {
            System.out.println("Oh no! A Monster has made it the Heros nexus, and so the Monsters win!");
            return true;
        }
        return false;
    }
}
