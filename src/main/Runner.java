package main;

import main.utils.Output;
import main.world.*;

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
        WorldBuilder builder = new RandomWorldBuilder(8, 8, 50, 20, 30);
        world = new ValorWorld(builder);
        world.placeHeroes();
        worldInteraction = new WorldInteraction(world);
    }
    private void buildRandomWorld(){

        WorldBuilder builder = new RandomWorldBuilder(8, 8, 50, 20, 30);
        world = new World(builder);
        world.placeHeroes();
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
        worldInteraction.run();
    }

}
