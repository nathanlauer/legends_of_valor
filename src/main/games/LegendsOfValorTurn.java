package main.games;

import main.Runner;
import main.fight.Attack;
import main.fight.FightMove;
import main.fight.InvalidFightMoveException;
import main.fight.PairHeroesAndMonsters;
import main.legends.Hero;
import main.legends.Legend;
import main.legends.Monster;
import main.world.Direction;

import java.util.ArrayList;
import java.util.Collections;
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
        current = null; // will be set in setupNextTurn method
        firstTurn = true;
        finished = false;
    }

    /**
     * Resets this TurnExecutor to the beginning
     */
    @Override
    public void reset() {
        // Don't use this method for Valor, since Monsters are added along the way, and
        // we don't want to worry about concurrent modification exceptions.
        // Instead, the LegendsOfValorRound class should create a new instance of this
        // class for each round, passing in whichever Heroes and Monsters are
        // currently in existence.
        throw new RuntimeException("Don't use this method in Legends of Valor!");
    }

    /**
     * Indicates whether or not the current legend is a Monster
     * @return true if current is a Monster, false otherwise
     */
    private boolean currentIsMonster() {
        return current instanceof Monster;
    }

    /**
     * Indicates whether or not the current legend is a Hero
     * @return true is current is a Hero, false otherwise
     */
    private boolean currentIsHero() {
        return !currentIsMonster();
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
     *
     * There is a possibility that there are no valid Monsters in the World,
     * and hence, in such a situation, Heroes should move consecutively. For
     * example, it's plausible that all Monsters have been killed, and only
     * Heroes remain.
     *
     * In the semantics of Legends of Valor, however, the opposite is not possible.
     * Heroes are respawned in their Nexus as soon as they faint, and hence
     * there is not actually a possibility that there may be no Heroes on the board.
     */
    private void setupLaterTurns() throws InvalidNextTurnException {
        // Not the first turn. Check to see if a Hero or a Monster went previously.
        if(monsterShouldGoNext()) {
            monsterGoesNext();
        } else {
            heroGoesNext();
        }
    }

    /**
     * Indicates whether or not the next Turn should be taken by a Monster.
     * @return true if the last Legend to go was a Hero, false otherwise.
     */
    private boolean monsterShouldGoNext() {
        return currentIsHero();
    }

    /**
     * Helper function which handles the situation where a Monster is
     * supposed to go next. As noted in the above function, there is
     * a possible scenario where there are actually no living Monsters.
     */
    private void monsterGoesNext() throws InvalidNextTurnException {
        boolean found = false;
        while(monsterIterator.hasNext() && !found) {
            Monster monster = monsterIterator.next();
            if(monster.isAlive()) {
                current = monster;
                found = true;
            }
        }

        // If there are no living Monsters, then go to the next Hero
        if(!found) {
            heroGoesNext();
        }
    }

    /**
     * Private helper function which handles the situation where a Hero is
     * supposed to go next.
     */
    private void heroGoesNext() throws InvalidNextTurnException {
        boolean found = false;
        while(heroIterator.hasNext() && !found) {
            Hero hero = heroIterator.next();
            if(!hero.hasFainted()) {
                current = hero;
                found = true;
            }
        }

        // If we were unable to find a valid Hero, then this turn based game has finished
        if(!found) {
            finished = true;
        }
    }

    /**
     * Plays the next turn
     */
    @Override
    public void playNextTurn() {
        // Do not execute a move if we are in a completed state
        if(finished) { return; }

        if(currentIsMonster()) {
            playNextMonstersTurn();
        } else {
            // TODO
        }
    }

    /**
     * Helper function which plays the next Monster's turn.
     * A Monster's turn follows simple logic: if there is a Hero in range, attack it.
     * Otherwise, move forward (forward for Monsters is down)
     */
    private void playNextMonstersTurn() {
        // TODO: List<Hero> heroesInRange = Runner.getInstance().getWorld().heroesInRange(current);
        List<Hero> heroesInRange = new ArrayList<>();
        if(heroesInRange.size() > 0){
            Hero toAttack = heroesInRange.get(0);
            FightMove attack = new Attack(current, Collections.singletonList(toAttack));
            try {
                attack.execute();
            } catch (InvalidFightMoveException e) {
                e.printStackTrace();
                // Shouldn't happen
            }
        } else {
            // Build a downward move for this Monster
            // TODO:
            // Move downwards = new DirectionalMove(current, Direction.DOWN);
            // downwards.execute();
        }
    }

    /**
     * Performs any processing necessary at the completion of the previous turn.
     */
    @Override
    public void processEndOfTurn() {
        // TODO: after a turn, check if current has reached a nexus
//        if(Runner.getInstance().getWorld().legendReachedNexus(current)) {
//            finished = true;
//        }
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
