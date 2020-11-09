package main.fight;

import main.legends.Legend;

import java.util.List;

/**
 * Class ExternalMove extends FightMove, and represents a type of
 * Move that the executor is performing on at least one other Legend.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/5/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class ExternalMove extends FightMove {
    private final List<Legend> receivers;

    public ExternalMove(Legend executor, List<Legend> receivers) {
        super(executor);
        this.receivers = receivers;
    }

    /**
     * The Legends that are on the receiving end of this Move.
     * @return List of affected Legends
     */
    public List<Legend> getReceivers() {
        return receivers;
    }
}
