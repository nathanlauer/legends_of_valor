package main.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Class GetUserNumericInput is a class which wraps the logic for obtaining user input,
 * specifically in the context where we prompt the user for a numeric input. That is,
 * something like "please enter a number 1 through 4"
 *
 * This class accepts a list of String options, which it outputs to the user, along
 * with a list of corresponding numbers. The numbers always begin at one, and end at
 * the length of the passed in List.
 *
 * This class will not change the order of the passed in List of options. Thus, when
 * the run() method returns an int, it is safe to assume that the value returned
 * correctly corresponds to the index of the chosen option.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/8/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class GetUserNumericInput {
    private String prompt;
    private List<String> options;
    private final Scanner scanner;

    /**
     * Empty constructor
     */
    public GetUserNumericInput(Scanner scanner) {
        this(scanner, "", new ArrayList<>());

        // Primarily for testing purposes
        String prompt = "What fight move would you like to make?";
        this.setPrompt(prompt);

        List<String> options = new ArrayList<>();
        options.add("Attack!");
        options.add("Cast a Spell!");
        options.add("Use a Potion!");
        options.add("Switch Weapons");
        options.add("Switch Armor");
        this.setOptions(options);
    }

    /**
     * Standard constructor
     * @param options the List of options to present to the user
     */
    public GetUserNumericInput(Scanner scanner, String prompt, List<String> options) {
        this.scanner = scanner;
        this.prompt = prompt;
        this.options = options;
    }

    /**
     * Sets the options to the passed in List.
     * @param newOptions new options List
     */
    public void setOptions(List<String> newOptions) {
        options = newOptions;
    }

    /**
     * Adds an available option to the List of options.
     * @param option option to add
     */
    public void addOption(String option) {
        this.options.add(option);
    }

    /**
     * Removes the passed in option
     * @param option option to remove
     */
    public void removeOption(String option) {
        this.options.remove(option);
    }

    /**
     * Sets the prompt to the passed in value
     * @param prompt the new Prompt
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    /**
     * "Main" method which prompts the user with the encapsulated options, and
     * gets their numeric input. Will continue to prompt the user until a valid
     * number if input.
     * @return the index of the chosen option
     */
    public int run() {
        boolean enteredValidNum = false;
        int selected = 0;
        while(!enteredValidNum) {
            // Output the prompt, and the available options
            System.out.println(prompt);
            for(int i = 0; i < this.options.size(); i++) {
                int outputNum = i + 1; // don't prompt the user with a 0!
                String message = outputNum + ") " + this.options.get(i); // get a copy of the option so we don't accidentally change it
                System.out.println(message);
            }
            System.out.println("Please enter a number between 1 and " + options.size());

            // Now, get their input
            try {
                String input = scanner.nextLine();
                selected = Integer.parseInt(input);
                enteredValidNum = true;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid option. Please try again");
            }
        }

        return selected - 1;
    }
}
