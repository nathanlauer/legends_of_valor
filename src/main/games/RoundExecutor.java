package main.games;

/**
 * Interface RoundExecutor represents the types of behaviors that RoundBasedGames have.
 * We use the strategy pattern to implement RoundBasedGames - each RoundBasedGame takes
 * an implementing instance of this interface. The implementations of the methods
 * declared here are the drivers of the mechanics of the relevant game.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/4/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface RoundExecutor {
    /**
     * Performs any preprocessing required before the start of a round.
     */
    void setupNextRound();

    /**
     * Method which encapsulates the logic for proceeding through a single round of the game.
     */
    void playRound();

    /**
     * Performs any required processing at the conclusion of a round of play.
     */
    void processEndOfRound();

    /**
     * Indicates whether or not the Game has ended. Implementing subclasses are expected
     * to build the appropriate logic into this method, such that when RoundBasedGame
     * invokes this method after invoking playRound, the implementing class will know
     * if the last played round represents the end of the Game.
     * @return true if the last call to playRound() resulted in a situation where the Game
     * has finished, false otherwise.
     */
    boolean finishedGame();
}
