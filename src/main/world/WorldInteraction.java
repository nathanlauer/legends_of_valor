package main.world;

import main.utils.GetUserCommand;
import main.utils.Output;
import main.utils.UserCommand;

/**
 * Class WorldInteraction is a class which allows the user to indicate where they would like to move
 * throughout the board.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class WorldInteraction {
    private final World world;

    public WorldInteraction(World world) {
        this.world = world;
    }

    /**
     * "Main" method which retrieves the user's desired action, and continues until the
     * user quits.
     */
    public void run() {
        boolean finished = false;
        while(!finished) {
            Output.drawWorld(world);
            UserCommand command = new GetUserCommand().run();
            switch (command) {
                case INFO:
                    System.out.println("TODO");
                case UP:
                    attemptMove(Direction.UP);
                    break;
                case DOWN:
                    attemptMove(Direction.DOWN);
                    break;
                case LEFT:
                    attemptMove(Direction.LEFT);
                    break;
                case RIGHT:
                    attemptMove(Direction.RIGHT);
                    break;
                case QUIT:
                    finished = true;
                    break;
                default:
                    throw new RuntimeException("Unknown command!");
            }
        }
        System.out.println("Thanks for playing!");
    }

    /**
     * Attempts to move in the given direction
     * @param direction the Direction to move
     */
    private void attemptMove(Direction direction) {
        String failure = "Unable to move " + direction + "! Please enter a different move.";
        if(world.canMove(direction)) {
            try {
                world.move(direction);
            } catch (InvalidMoveDirection e) {
                System.out.println(failure);
            }
        } else {
            System.out.println(failure);
        }
    }
}
