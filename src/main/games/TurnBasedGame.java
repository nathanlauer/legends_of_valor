package main.games;

/**
 * Class TurnBasedGame
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/4/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TurnBasedGame extends Game {
    TurnExecutor executor;

    /**
     * Constructor for a TurnBasedGame.
     * @param executor The TurnExecutor containing the logic for playing this Game.
     */
    public TurnBasedGame(TurnExecutor executor) {
        this.executor = executor;
    }

    /**
     * Sets the TurnExecutor to the passed in value.
     * This allows a TurnBasedGame to have its executor changed dynamically.
     * @param newExecutor the new TurnExecutor for this Game
     */
    public void setExecutor(TurnExecutor newExecutor) {
        executor = newExecutor;
    }

    /**
     * Plays the Game.
     */
    @Override
    public void play() {
        boolean finished = executor.finishedAllTurns();
        while(!finished) {
            executor.setupNextTurn();
            executor.playNextTurn();
            executor.processEndOfTurn();
            finished = executor.finishedAllTurns();
        }
    }
}
