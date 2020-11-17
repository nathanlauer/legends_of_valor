package main.games;

import main.fight.FightMove;
import main.fight.PairHeroesAndMonsters;
import main.legends.Hero;
import main.legends.Legend;
import main.legends.Monster;

import java.util.List;
import java.util.ListIterator;

/**
 * Class LegendsOfValorTurn implements TurnExecutor, and is therefore a TurnBasedGame,
 * and represents a single move within the game Legends of Valor. Within a turn,
 * a Monster may attack a Hero or move forward, or a Hero may choose some type
 * of Move or fight action against a Monster in range.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/17/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class LegendsOfValorTurn implements TurnExecutor {
    private Legend current;
    private boolean firstTurn;
    private final ListIterator<Hero> heroIterator;
    private final ListIterator<Monster> monsterIterator;
    private boolean finished;
    private FightMove fightMove;
    // private Move move; TODO when Moves have been built

    /**
     * Standard constructor
     * @param heroes the Heroes playing in this Turn
     * @param monsters the Monsters playing in this Turn
     */
    public LegendsOfValorTurn(List<Hero> heroes, List<Monster> monsters) {
        heroIterator = heroes.listIterator();
        monsterIterator = monsters.listIterator();
    }

    /**
     * Resets this TurnExecutor to the beginning
     */
    @Override
    public void reset() {
        // TODO:
    }

    /**
     * Performs any required setup for the next turn.
     */
    @Override
    public void setupNextTurn() throws InvalidNextTurnException {
        // TODO:
    }

    /**
     * Plays the next turn
     */
    @Override
    public void playNextTurn() {
        // TODO:
    }

    /**
     * Performs any processing necessary at the completion of the previous turn.
     */
    @Override
    public void processEndOfTurn() {
        // TODO:
    }

    /**
     * Indicates whether or not all turns have finished.
     *
     * @return true if all turns have completed, false otherwise.
     */
    @Override
    public boolean finishedAllTurns() {
        // TODO:
        return false;
    }
}
