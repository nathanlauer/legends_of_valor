package main.fight;

import main.games.TurnExecutor;
import main.legends.Hero;
import main.legends.Monster;

import java.util.List;

/**
 * Class HeroesVsMonstersTurn is similar to the HeroesVsMonstersRound, except that
 * it encapsulates the process of performing a single turn within a fight between
 * some Heroes and some Monsters.
 *
 * Within a turn, one of two things happens:
 * (a) It is a Hero's turn, and the Hero makes some move
 * (b) It is a Monster's turn, and the Monster attacks one of the Heroes.
 *
 * The first turn always belongs to one of the Heroes. After a turn,
 * the type of Legend switches for the next turn - if a Hero just went,
 * then a Monster goes next, and vise versa, if a Monster just went,
 * then a Hero goes next.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/4/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class HeroesVsMonstersTurn implements TurnExecutor  {
    private final List<Hero> heroes;
    private final List<Monster> monsters;

    /**
     * Standard constructor for a HeroesVsMonstersTurn instance
     * @param heroes List of Heroes participating
     * @param monsters List of Monsters participating
     */
    public HeroesVsMonstersTurn(List<Hero> heroes, List<Monster> monsters) {
        this.heroes = heroes;
        this.monsters = monsters;
    }

    /**
     * Performs any required setup for the next turn.
     */
    @Override
    public void setupNextTurn() {

    }

    /**
     * Plays the next turn
     */
    @Override
    public void playNextTurn() {

    }

    /**
     * Performs any processing necessary at the completion of the previous turn.
     */
    @Override
    public void processEndOfTurn() {

    }

    /**
     * Indicates whether or not all turns have finished.
     *
     * @return true if all turns have completed, false otherwise.
     */
    @Override
    public boolean finishedAllTurns() {
        return false;
    }
}
