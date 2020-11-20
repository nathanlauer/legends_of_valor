package main.utils;

import java.util.Scanner;

/**
 * Class GetUserCommand prompts the user for their move, and returns a valid command
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class GetUserCommand extends GetUserInput {
    public GetUserCommand() {
        super(new Scanner(System.in));
    }
    /**
     * Prompts the user for their move, and returns their desired action
     * @return the action the user wants to perform
     */
    public UserCommand run() {
        boolean validInput = false;
        String input = "";
        while(!validInput) {
            System.out.println("Controls:");
            System.out.println("W/w - move up");
            System.out.println("A/a - move left");
            System.out.println("S/s - move down");
            System.out.println("D/d - move right");
            System.out.println("T/t - teleport");
            System.out.println("B/b - back to nexus");
            System.out.println("I/i - get info");
            System.out.println("Q/q - quit the game");
            System.out.println("Enter your move: (enter one of the above options)");

            if(this.getNextLine()) {
                input = this.getUserInput();
                if(inputIsValid(input)) {
                    validInput = true;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }

        switch (input.toLowerCase()) {
            case "w":
                return UserCommand.UP;
            case "a":
                return  UserCommand.LEFT;
            case "s":
                return UserCommand.DOWN;
            case "d":
                return UserCommand.RIGHT;
            case "t":
            	return UserCommand.TELEPORT;
            case "b":
            	return UserCommand.BACK;
            case "i":
                return UserCommand.INFO;
            case "q":
                return UserCommand.QUIT;
            default:

        }
        return null;
    }

    /**
     * Indicates if the passed in user input represents a valid command
     * @param input the Input from the user
     * @return true if valid, false otherwise
     */
    private boolean inputIsValid(String input) {
        input = input.toLowerCase();
        return input.equals("w") ||
                input.equals("a") ||
                input.equals("s") ||
                input.equals("d") ||
                input.equals("t") ||
                input.equals("b") ||
                input.equals("i") ||
                input.equals("q");

    }
}
