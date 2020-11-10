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
        Output.printSeparator();
        System.out.println("Fight Status Information:");
        System.out.println("Number of Heroes: " + heroes.size());
        System.out.println("Number of Monsters: " + monsters.size());
        Output.printSeparator();
        System.out.println("Detailed Information");
        System.out.println("Heroes: ");
        Output.printOutputables(heroes);
        Output.newLine();
        System.out.println("Monsters: ");
        Output.printOutputables(monsters);
    }

    /**
     * Displays nominal information for each of the Heroes in the input List
     * @param heroes List of Heroes to display info for.
     */
    public static void displayNominalInformation(List<Hero> heroes) {
        Output.printSeparator();
        System.out.println("Information Menu:");
        System.out.println("Number of heroes: " + heroes.size());
        System.out.println("Hero names: ");
        for(Hero hero : heroes) {
            System.out.println(hero.getName());
        }
        Output.printSeparator();
        System.out.println("Detailed Information");
        Output.printOutputables(heroes);
    }

    /**
     * Displays info at the end of the game
     * @param heroes List of Heroes who played
     */
    public static void displayFinalInfo(List<Hero> heroes) {
        Output.printSeparator();
        System.out.println("Game Completed. Final Information:");
        Output.printOutputables(heroes);
        System.out.println("Thanks for playing!");
    }

    public static void printWelcomeInformation() {
        System.out.println("Welcome to Legends: Heroes and Monsters!");
        System.out.println("In this game, you will lead a number of Heroes throughout the World, fighting Monsters and gaining expereince as you go!");
        System.out.println("In this World, there are a few different types of places:");
        System.out.println("You can bring your heroes to a market, where you can buy and sell items.");
        System.out.println("There are certain places that are are inaccessible.");
        System.out.println("There are empty places as well. Beware! Some of these will contain Monsters!");
        System.out.println("If you encounter monsters, a fight will begin. Should you win, your heroes will gain money and experience.");
        System.out.println("If you lose, however, your heroes will be revived at half health power, without gaining any money or experience.");
        System.out.println("At any time you may press I/i to obtain game information.");
        System.out.println("The game continues until you press Q/q to quit the game.");
        System.out.println("Enjoy!");
        Output.printSeparator();
    }
}
