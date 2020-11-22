package main;

import main.games.ValorGame;
import main.legends.Hero;
import main.legends.Legend;
import main.legends.LegendList;
import main.legends.Monster;
import main.utils.Output;
import main.world.*;

import java.util.List;

/**
 * Class Runner is the class which actually runs the game, by creating a World and a WorldInteraction.
 * It is extracted from Main primarily to provide access to the World through the getWorld() method.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Runner {
    private static Runner instance;
    private World world;
    private WorldInteraction worldInteraction;

    /**
     *
     * @return the Singleton instance of this class.
     */
    public static Runner getInstance() {
        if(instance == null) {
            instance = new Runner();
        }
        return instance;
    }

    /**
     * Private constructor. Builds the World and the World Interaction.
     */
    private Runner() {
        buildValorWorld();
    }

    private void buildValorWorld(){
        ValorWorldBuilder.ValorWorldDistribution distribution = new ValorWorldBuilder.ValorWorldDistribution();
        WorldBuilder builder = new ValorWorldBuilder(8, 8,distribution);
        world = new ValorWorld(builder);
        world.placeHeroes(LegendList.getInstance().getChosenHeroes());
        worldInteraction = new WorldInteraction(world);
    }
    private void buildRandomWorld(){

        WorldBuilder builder = new RandomWorldBuilder(8, 8, 50, 20, 30);
        world = new RandomWorld(builder);
        world.placeHeroes(LegendList.getInstance().getChosenHeroes());
        worldInteraction = new WorldInteraction(world);
    }

    /**
     *
     * @return a handle to the World in this Game.
     */
    public World getWorld() {
        return world;
    }

    public void run() {
        Output.printWelcomeInformation();
        LegendList.getInstance(); // prompts the user to choose their Hero's
        List<Hero> heroes = LegendList.getInstance().getChosenHeroes();
        List<Monster> monsters = LegendList.getInstance().getActiveMonsters();
        new ValorGame(heroes, monsters).play();
    }

}
