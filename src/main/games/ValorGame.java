package main.games;

import main.legends.Hero;
import main.legends.Monster;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ValorGame
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/19/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class ValorGame {
    private final RoundBasedGame game;

    /**
     * Standard constructor
     * @param heroes the Heroes that are in this game
     * @param monsters initial list of Monsters that are in this game
     */
    public ValorGame(List<Hero> heroes, List<Monster> monsters) {
        RoundExecutor roundExecutor = new LegendsOfValorRound(heroes, monsters, 8);
        game = new RoundBasedGame(roundExecutor);
    }

    /**
     * Plays the game: that is, coordinates the process of going through rounds,
     * playing Hero's and Monster's turns.
     */
    public void play() {
        game.play();
    }
}
