package main;

import main.world.RandomWorldBuilder;
import main.world.World;
import main.world.WorldBuilder;
import main.world.WorldInteraction;

/**
 * Class Main
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/8/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Main {
    public static void main(String[] args) {
        WorldBuilder builder = new RandomWorldBuilder(8, 8, 50, 20, 30);
        World world = new World(builder);
        world.placeHeroes();
        WorldInteraction worldInteraction = new WorldInteraction(world);
        worldInteraction.run();
    }
}
