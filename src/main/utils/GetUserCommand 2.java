package main.utils;

/**
 * Class GetUserCommand prompts the user for their move, and returns a valid command
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class GetUserCommand {
    /**
     * Prompts the user for their move, and returns their desired action
     * @return the action the user wants to perform
     */
    public static UserCommand run() {
        System.out.println("Controls:");
        System.out.println("W/w - move up");
        System.out.println("A/a - move left");
        System.out.println("S/s - move down");
        System.out.println("D/d - move right");
        System.out.println("I/i - get info");
        System.out.println("Q/q - quit the game");
        System.out.println("Enter your move: (enter one of the above options)");
    }
}
