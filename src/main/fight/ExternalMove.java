package main.fight;

import main.legends.Legend;

import java.util.List;

/**
 * Class ExternalMove extends FightMove, and represents a type of
 * Move that the executor is performing on at least one other Legend.
 *
 * In this game, every one of these types of Moves deals some amount
 * of damage to the relevant parties.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/5/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class ExternalMove extends FightMove {
    private final int damage;
    private final List<Legend> receivers;

    public ExternalMove(Legend executor, List<Legend> receivers, int damage) {
        super(executor);
        this.receivers = receivers;
        this.damage = damage;
    }

    /**
     * The Legends that are on the receiving end of this Move.
     * @return List of affected Legends
     */
    public List<Legend> getReceivers() {
        return receivers;
    }

    /**
     *
     * @return the amount of damage done by this Move
     */
    public int getDamage() {
        return damage;
    }
}
