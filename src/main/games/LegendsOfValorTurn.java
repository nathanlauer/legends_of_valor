package main.games;

import main.Runner;
import main.fight.Attack;
import main.fight.FightMove;
import main.fight.InvalidFightMoveException;
import main.fight.PairHeroesAndMonsters;
import main.legends.Hero;
import main.legends.Legend;
import main.legends.LegendList;
import main.legends.Monster;
import main.utils.Colors;
import main.utils.GetUserCommand;
import main.utils.Output;
import main.utils.UserCommand;
import main.world.Direction;
import main.world.InvalidMoveDirection;
import main.world.ValorWorld;
import main.world.World;

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

        // And draw the World, so the user has a sense of what is going on
        Output.printSeparator();
        Output.drawWorld(Runner.getInstance().getWorld());
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
            playNextHeroesTurn();
        }
    }

    /**
     * Helper function which plays the next Monster's turn.
     * A Monster's turn follows simple logic: if there is a Hero in range, attack it.
     * Otherwise, move forward (forward for Monsters is down)
     */
    private void playNextMonstersTurn() {
        displayCurrentMonsterStatus();
        ValorWorld world = (ValorWorld)Runner.getInstance().getWorld();
        List<Hero> heroesInRange = world.getHeroesInRange((Monster)current);
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
            try {
                world.move((Monster)current, Direction.DOWN);
            } catch (InvalidMoveDirection invalidMoveDirection) {
                invalidMoveDirection.printStackTrace();
                // Shouldn't happen
            }
        }
    }

    /**
     * Helper function which outputs the status of the current Monster
     */
    private void displayCurrentMonsterStatus() {
        Output.printSeparator();
        Monster monster = (Monster)current;
        String firstTwoLetters = Colors.ANSI_RED + monster.getName().substring(0, 2) + Colors.ANSI_RESET;
        System.out.println("It is " + monster.getName() + " turn. Identified by (" + firstTwoLetters + ") on the map. Status:");
        Output.printOutputables(Collections.singletonList(monster));
    }

    /**
     * Helper function which plays the next Hero's turn.
     * For a Hero, we prompt the user for their desired action for the relevant Hero,
     * and then execute it if possible.
     */
    private void playNextHeroesTurn() {
        displayCurrentHeroStatus();
        Hero hero = (Hero)current;
        boolean enteredLegalMove = false;
        // TODO: prompt user for attack or move first, then if choose move, proceed as below
        while(!enteredLegalMove) {
            UserCommand command = new GetUserCommand().run();
            switch (command) {
                case UP:
                    enteredLegalMove = attemptMoveIfPossible(hero, Direction.UP);
                    break;
                case DOWN:
                    enteredLegalMove = attemptMoveIfPossible(hero, Direction.DOWN);
                    break;
                case LEFT:
                    enteredLegalMove = attemptMoveIfPossible(hero, Direction.LEFT);
                    break;
                case RIGHT:
                    enteredLegalMove = attemptMoveIfPossible(hero, Direction.RIGHT);
                    break;
                case TELEPORT:
                    enteredLegalMove = attemptMoveIfPossible(hero, Direction.TELEPORT);
                    break;
                case BACK:
	                enteredLegalMove = attemptMoveIfPossible(hero,Direction.BACK);
                    break;
                default:
                    throw new RuntimeException("Unknown command!");
            }
        }
    }

    private void displayCurrentHeroStatus() {
        Output.printSeparator();
        Hero hero = (Hero)current;
        String firstTwoLetters = Colors.ANSI_GREEN + hero.getName().substring(0, 2) + Colors.ANSI_RESET;
        System.out.println("It is " + hero.getName() + " turn! Identified by (" + firstTwoLetters + ") on the map. Status:");
        Output.printOutputables(Collections.singletonList(hero));
    }

    /**
     * Attempts to move in the Direction indicated
     * @param hero Hero whose move it is
     * @param direction Direction to move the Hero
     */
    private boolean attemptMoveIfPossible(Hero hero, Direction direction) {
        String failure = "Unable to move " + direction + "! Please enter a different move.";
        World world = Runner.getInstance().getWorld();
        try {
            world.move(hero, direction);
        } catch (InvalidMoveDirection e) {
            System.out.println(failure);
            return false;
        }
        return true;
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
