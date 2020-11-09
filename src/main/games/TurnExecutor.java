package main.games;

/**
 * Interface TurnExecutor represents the type of behaviors that a TurnBasedGame
 * has. We use the strategy pattern to implement TurnBasedGames - each TurnBasedGame
 * takes an implementing instance of this interface. The implementations of the methods
 *  * declared here are the drivers of the mechanics of the relevant game.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/4/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface TurnExecutor {
    /**
     * Performs any required setup for the next turn.
     */
    void setupNextTurn() throws InvalidNextTurnException;

    /**
     * Plays the next turn
     */
    void playNextTurn();

    /**
     * Performs any processing necessary at the completion of the previous turn.
     */
    void processEndOfTurn();

    /**
     * Indicates whether or not all turns have finished.
     * @return true if all turns have completed, false otherwise.
     */
    boolean finishedAllTurns();
}
