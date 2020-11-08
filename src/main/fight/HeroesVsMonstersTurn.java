package main.fight;

import main.games.InvalidNextTurnException;
import main.games.TurnExecutor;
import main.legends.Hero;
import main.legends.Legend;
import main.legends.Monster;

import java.util.Iterator;
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
//    private final List<Hero> heroes;
//    private final List<Monster> monsters;
    private Legend current;
    private boolean firstTurn;
    private final Iterator<Hero> heroIterator;
    private final Iterator<Monster> monsterIterator;
    private boolean finished;
    // TODO: add PairHeroesAndMonsters to this class

    /**
     * Standard constructor for a HeroesVsMonstersTurn instance
     * @param heroes List of Heroes participating
     * @param monsters List of Monsters participating
     */
    public HeroesVsMonstersTurn(List<Hero> heroes, List<Monster> monsters) {
//        this.heroes = heroes;
//        this.monsters = monsters;
        heroIterator = heroes.iterator();
        monsterIterator = monsters.iterator();
        firstTurn = true;
        finished = false;
    }

    /**
     * Performs any required setup for the next turn.
     */
    @Override
    public void setupNextTurn() throws InvalidNextTurnException {
        if(firstTurn) {
            setupFirstTurn();
            firstTurn = false;
        } else {
            setupLaterTurns();
        }
    }

    /**
     * Private helper function which sets up the first turn
     * @throws InvalidNextTurnException if no Heroes have any health power
     */
    private void setupFirstTurn() throws InvalidNextTurnException {
        boolean found = false;
        while(heroIterator.hasNext() && !found) {
            Hero hero = heroIterator.next();
            if(!hero.hasFainted()) {
                current = hero;
                found = true;
            }
        }
        if(!found) {
            throw new InvalidNextTurnException("No valid Hero for the first move!");
        }
        firstTurn = false;
    }

    /**
     * Private helper function which sets up a turn that is not the first turn.
     * Checks whether the previous Legend was a Monster or a Hero, and then
     * marks current as the next opposite Legend (e.g. if a Monster just went,
     * chooses the next Hero, and vise versa).
     */
    private void setupLaterTurns() {
        // Not the first turn. Check to see if a Hero or a Monster went previously.
        boolean found = false;
        if(current instanceof Hero) { // Monster goes next
            while(monsterIterator.hasNext() && !found) {
                Monster monster = monsterIterator.next();
                if(monster.getHealthPower().hasSomeHealth()) {
                    current = monster;
                    found = true;
                }
            }
            if(!found) { // If there are no living Monsters, then the fight has been completed.
                finished = true;
            }
        } else { // Hero goes next
            while(heroIterator.hasNext() && !found) {
                Hero hero = heroIterator.next();
                if(!hero.hasFainted()) {
                    current = hero;
                    found = true;
                }
            }
            if(!found) { // All Heroes have fainted, Monsters win this fight.
                finished = true;
            }
        }
    }

    /**
     * Plays the next turn
     */
    @Override
    public void playNextTurn() {
        // Ensure we don't take any action if we are in a completed state
        if(finished) {
            return;
        }

        // If current is a Monster, execute an attack move

        // If current is a Hero, prompt the user for their desired move, then execute it.
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
        return finished;
    }
}
