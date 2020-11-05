package main.fight;

import main.games.RoundExecutor;
import main.games.TurnBasedGame;
import main.games.TurnExecutor;
import main.legends.Hero;
import main.legends.Monster;

import java.util.List;

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
    private TurnBasedGame turnBasedGame;

    /**
     * Standard constructor for a HeroVsMonsterRound
     * @param heroes List of Heroes that are in this fight
     * @param monsters List of Monsters that are in this fight
     */
    public HeroesVsMonstersRound(List<Hero> heroes, List<Monster> monsters) {
        this.heroes = heroes;
        this.monsters = monsters;
    }

    /**
     * Performs any preprocessing required before the start of a round.
     */
    @Override
    public void setupNextRound() {
        // Setup a Heroes vs Monsters turn based game for the next round.
        TurnExecutor turnExecutor = new HeroesVsMonstersTurn(this.heroes, this.monsters);
        this.turnBasedGame = new TurnBasedGame(turnExecutor);
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
        for(Hero hero : this.heroes) {
            if(!hero.hasFainted()) {
                // Surviving Heroes regain 10% of their health power and 10% of their Mana
                hero.getHealthPower().increaseByPercentage(10);
                hero.getMana().increaseByPercentage(10);
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
        // The Game has finished if either all Heroes have fainted,
        for(Hero hero : this.heroes) {
            if(!hero.hasFainted()) {
                return false;
            }
        }

        // or all Monsters have no HealthPower
        for(Monster monster : this.monsters) {
            if(monster.getHealthPower().getHealthPower() > 0) {
                return false;
            }
        }
        return true;
    }
}
