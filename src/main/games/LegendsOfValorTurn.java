package main.games;

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
    /**
     * Resets this TurnExecutor to the beginning
     */
    @Override
    public void reset() {
        // TODO:
    }

    /**
     * Performs any required setup for the next turn.
     */
    @Override
    public void setupNextTurn() throws InvalidNextTurnException {
        // TODO:
    }

    /**
     * Plays the next turn
     */
    @Override
    public void playNextTurn() {
        // TODO:
    }

    /**
     * Performs any processing necessary at the completion of the previous turn.
     */
    @Override
    public void processEndOfTurn() {
        // TODO:
    }

    /**
     * Indicates whether or not all turns have finished.
     *
     * @return true if all turns have completed, false otherwise.
     */
    @Override
    public boolean finishedAllTurns() {
        // TODO:
        return false;
    }
}
