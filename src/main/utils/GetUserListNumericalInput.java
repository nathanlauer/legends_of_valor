package main.utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class GetUserListNumericalInput is similar to GetUserNumericInput, but instead of
 * returning a single value, prompts the user to choose multiple values.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/8/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class GetUserListNumericalInput {
    private String prompt;
    private List<String> options;

    /**
     * Empty constructor
     */
    public GetUserListNumericalInput() {
        this("", new ArrayList<>());

        // Primarily for testing purposes
        String prompt = "Which Monsters should be paired with your Hero?";
        this.setPrompt(prompt);

        List<String> options = new ArrayList<>();
        options.add("Desghidorrah");
        options.add("Chrysophylax");
        options.add("BunsenBurner");
        options.add("Natsunomeryu");
        options.add("TheScaleless");
        this.setOptions(options);
    }

    /**
     * Standard constructor
     * @param options the List of options to present to the user
     */
    public GetUserListNumericalInput(String prompt, List<String> options) {
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
    public List<Integer> run() {
        boolean enteredValidSequence = false;
        List<Integer> selected = new ArrayList<>();
        Scanner scanner = ScannerInstance.getInstance().getScanner();
        while(!enteredValidSequence) {
            // Output the prompt, and the available options
            System.out.println(prompt);
            for(int i = 0; i < this.options.size(); i++) {
                int outputNum = i + 1; // don't prompt the user with a 0!
                String message = outputNum + ") " + this.options.get(i); // get a copy of the option so we don't accidentally change it
            }
            System.out.println("Please enter a comma separated list of numbers. For example: 2,3");

            // Now, get their input
            try {
                String input = scanner.nextLine();
                String[] values = input.split(",\\s*");
                for(String value : values) {
                    try {
                        selected.add(Integer.parseInt(value));
                    } catch (NumberFormatException e) {
                        throw new InputMismatchException(e.getMessage());
                    }
                }
                enteredValidSequence = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Please try again");
            }
        }

        return selected;
    }
}
