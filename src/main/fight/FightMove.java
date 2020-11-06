package main.fight;

import main.legends.Legend;

/**
 * Class FightMove is an abstract class which represents a type of Move that can be
 * made during a fight between a Hero and a Monster.
 *
 * There are five types of Moves:
 * 1) Attack
 * 2) Cast a Spell
 * 3) Use a Potion
 * 4) Switch Armor
 * 5) Switch Weapons
 *
 * These actions are further divided into two, in two abstract subclasses:
 * ExternalMoves, and InternalMoves. ExternalMoves are moves which affect some
 * Legend that is not the executor. For example, attacking or using a spell.
 * InternalMoves are moves that only affect the executor. For example, using a
 * spell, switching armor, or switching weapons - these moves don't actually
 * affect the Observer of the subject executing the move.
 *
 * We group these two types of classes into one hierarchy, for simplicity, and
 * also because they both share at least one common attribute: the Legend executing
 * the move.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/5/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class FightMove {
    private final Legend executor;

    /**
     * Standard constructor
     * @param executor Legend that is making this Move.
     */
    public FightMove(Legend executor) {
        this.executor = executor;
    }

    /**
     *
     * @return the Legend that made this Move.
     */
    public Legend getExecutor() {
        return executor;
    }

    /**
     * Executes this FightMove
     */
    public abstract void execute();
}
