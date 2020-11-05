package main.legends;

/**
 * Interface Observer defines the behavior of an Observer in the Observer pattern.
 * In particular, we use the Observer pattern in this game to implement a fight
 * between Heroes and Monsters. For example, if a Hero is the subject, and a
 * Monster is an Observer, then when the Hero attacks the Monster, the Hero (the subject)
 * notifies the Monster (the observer) of the attack, and the Monster updates
 * accordingly.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/5/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface Observer {
    /**
     * When an event occurs, and the subject updates, the Observer updates
     * its internal state.
     */
    void update();
}
