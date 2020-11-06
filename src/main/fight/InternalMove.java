package main.fight;

import main.legends.Legend;

/**
 * Class InternalMove is a type of FightMove where the executor performs
 * some kind of action on itself. For example, a Hero may choose
 * to switch Armor.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/5/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class InternalMove extends FightMove {
    /**
     * Standard constructor
     * @param executor the Legend executing this Move
     */
    public InternalMove(Legend executor) {
        super(executor);
    }
}
// TODO: I'm not sure if the Observer pattern actually makes sense here.
// A simple inheritance tree with an execute move is simple, and works
// just fine here I think. It seems that the Observer pattern may actually
// make this more complicated.