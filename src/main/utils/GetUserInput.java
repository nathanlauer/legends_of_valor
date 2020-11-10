package main.utils;

import main.Runner;
import main.fight.Fight;
import main.legends.Hero;
import main.legends.LegendList;
import main.legends.Monster;
import main.world.World;

import java.util.List;
import java.util.Scanner;

/**
 * Class GetUserInput is a class that sits at the top of the hierarchy of classes that are responsible for
 * getting user input. In particular, it provides a method to call scanner.nextLine, and checks to see if the value
 * returned was either "q" or "i".
 *
 * If so, the game either quits or relevant information is displayed, respectively. If not, it returns value the user
 * input.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class GetUserInput {
    private String userInput;
    private final Scanner scanner;

    /**
     * Standard constructor
     * @param scanner the Scanner to read in user input
     */
    public GetUserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     *
     * @return the string representation of the user input
     */
    public String getUserInput() {
        return userInput.toLowerCase();
    }

    /**
     * Encapsulates the action of getting input from the user. If the user entered "q" or "i", quits the
     * game, or displays relevant information. In such a situation, returns false - as in, the input the user
     * actually entered was not the desired input of the calling class. Otherwise, returns true,
     * meaning that the user entered something which the calling class is presumably expecting.
     * @return true if the user did not enter "Q/q" or "I/i", false otherwise.
     */
    public boolean getNextLine() {
        userInput = scanner.nextLine();
        if(userInput.toLowerCase().equals("q")) {
            quitGame();
            return false; // won't really be reached. quitGame() calls System.exit
        }

        if(userInput.toLowerCase().equals("i")) {
            // Display some information
            World world = Runner.getInstance().getWorld();
            if(world.isFighting()) {
                Fight fight = world.getFight();
                List<Hero> heroes = fight.getHeroes();
                List<Monster> monsters = fight.getMonsters();
                Output.displayFightInformation(heroes, monsters);
            } else {
                List<Hero> heroes = LegendList.getInstance().getChosenHeroes();
                Output.displayNominalInformation(heroes);
            }
            waitForUserToContinue();
            return false;
        }

        return true;
    }

    /**
     * Waits for the user to press "C/c" or "I/i" again to quit the information display.
     */
    private void waitForUserToContinue() {
        System.out.println("Enter C/c or I/i to continue playing the game");
        boolean userCompleted = false;
        while(!userCompleted) {
            String input = scanner.nextLine();
            if(input.toLowerCase().equals("q")) {
                quitGame();
            }
            if(input.toLowerCase().equals("c") || input.toLowerCase().equals("i")) {
                userCompleted = true;
            } else {
                System.out.println("Please enter C/c or I/i to continue playing the game.");
            }
        }
    }

    private void quitGame() {
        List<Hero> heroes = LegendList.getInstance().getChosenHeroes();
        Output.displayFinalInfo(heroes);
        System.exit(0);
    }
}
