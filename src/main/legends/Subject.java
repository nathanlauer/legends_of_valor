package main.legends;

/**
 * Interface Subject is the Subject in the Observer pattern. In particular,
 * we use the Observer pattern to implement fights between Heroes and Monsters.
 * In this case, the subject is the attacker, and the Observer is the
 * defender.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/5/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface Subject {
    /**
     * Registers an Observer for this Subject
     * @param observer the Observer to register for the Subject
     */
    void registerObserver(Observer observer);

    /**
     * Unregisters the passed in Observer
     * @param observer Observer to unregister
     */
    void unregisterObserver(Observer observer);

    /**
     * Subject notifies its observers when some event occurs.
     */
    void notifyObservers();
}
