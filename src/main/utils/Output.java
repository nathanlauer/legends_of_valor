package main.utils;

import main.legends.Hero;
import main.legends.Monster;
import main.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Class Output
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Output {
    /**
     * Prints out a clear separator, for use when separating between various different sections.
     */
    public static void printSeparator() {
        System.out.println("=====================================================");
    }

    /**
     * Given a list of entities that can be printed to stdout, construct a list of Strings,
     * where each entry corresponds to the output of each outputable.
     * @param outputables the entities to be printed
     * @return List of Strings as represented by each outputable.
     */
    public static List<String> outputablesAsStrings(List<? extends Outputable> outputables) {
        List<String> output = new ArrayList<>();
        if(outputables.isEmpty()) {
            return output;
        }

        String header = outputables.get(0).getHeaderString();
        output.add(header);
        for(Outputable outputable : outputables) {
            output.add(outputable.toString());
        }
        return output;
    }

    /**
     * Prints the passed in list of outputables to stdout.
     * @param outputables the List of entities to be printed.
     */
    public static void printOutputables(List<? extends Outputable> outputables) {
        List<String> asStrings = Output.outputablesAsStrings(outputables);
        asStrings.forEach(System.out::println);
    }

    /**
     * Prints a new line
     */
    public static void newLine() {
        System.out.println();
    }

    /**
     * Given the list of outputables, prompts the user to select one of them.
     * @param items the outputables that the user should choose from
     * @param prompt the prompt
     * @param header the header row describing the output
     * @return the outputable chosen by the user
     */
    public static Outputable promptUserForChoice(List<? extends Outputable> items, String prompt, String header) {
        List<String> options = new ArrayList<>();
        options.add(header);
        items.forEach(outputable -> options.add(outputable.toString()));

        List<Integer> linesToSkip = new ArrayList<>(Collections.singletonList(0)); // skip the header line

        GetUserNumericInput getUserNumericInput = new GetUserNumericInput(new Scanner(System.in), prompt, options);
        getUserNumericInput.setLinesToSkip(linesToSkip);
        int chosen = getUserNumericInput.run();

        return items.get(chosen);
    }

    /**
     * Draws the world
     * @param world the World to be drawn
     */
    public static void drawWorld(World world) {
        List<String> drawn = world.draw();
        drawn.forEach(System.out::println);
    }

    /**
     * Displays information regarding a fight. That is, it outputs data for the Heroes and the Monsters
     * @param heroes List of Heroes in the fight
     * @param monsters List of Monsters in the fight
     */
    public static void displayFightInformation(List<Hero> heroes, List<Monster> monsters) {

    }

    /**
     * Displays nominal information for each of the Heroes in the input List
     * @param heroes List of Heroes to display info for.
     */
    public static void displayNominalInformation(List<Hero> heroes) {

    }

    /**
     * Displays info at the end of the game
     * @param heroes List of Heroes who played
     */
    public static void displayFinalInfo(List<Hero> heroes) {

    }
}
