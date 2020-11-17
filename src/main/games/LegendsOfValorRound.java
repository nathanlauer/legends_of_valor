package main.games;

import main.legends.Hero;
import main.legends.Monster;

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
    private List<Hero> heroes;
    private List<Monster> monsters;
    private final TurnBasedGame turnBasedGame;
    private int roundNum;
    private final TurnExecutor turnExecutor;

    public LegendsOfValorRound() {
        roundNum = 0;
        this.turnExecutor = new LegendsOfValorTurn(); // TODO: arguments?
        this.turnBasedGame = new TurnBasedGame(turnExecutor);
    }

    /**
     * Performs any preprocessing required before the start of a round.
     */
    @Override
    public void setupNextRound() {
        // TODO:
    }

    /**
     * Method which encapsulates the logic for proceeding through a single round of the game.
     */
    @Override
    public void playRound() {
        // TODO:
    }

    /**
     * Performs any required processing at the conclusion of a round of play.
     */
    @Override
    public void processEndOfRound() {
        // TODO:
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
