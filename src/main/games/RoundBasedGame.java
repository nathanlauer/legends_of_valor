package main.games;

/**
 * Class RoundBasedGame is a type of Game that proceeds in rounds.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/4/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class RoundBasedGame extends Game {
    private RoundExecutor executor;

    /**
     * Standard constructor for a RoundBasedGame.
     * @param executor the RoundExecutor which implements the logic for progressing through a round of this Game.
     */
    public RoundBasedGame(RoundExecutor executor) {
        this.executor = executor;
    }

    /**
     * Sets the RoundExecutor for this Game to the passed in value.
     * This method allows a RoundBasedGame to switch RoundExecutors dynamically
     * @param newExecutor new RoundExecutor for this Game.
     */
    public void setExecutor(RoundExecutor newExecutor) {
        executor = newExecutor;
    }

    /**
     * Plays the Game.
     */
    @Override
    public void play() {
        boolean finished = executor.finishedGame();
        while(!finished) {
            executor.playRound();
            finished = executor.finishedGame();
        }
    }
}
