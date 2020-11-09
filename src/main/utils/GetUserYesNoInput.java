package main.utils;

import java.util.Scanner;

/**
 * Class GetUserYesNoInput is a class that prompts the user for a yes/no response.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class GetUserYesNoInput {
    /**
     * Prompts the user for a yes/no response
     * @param prompt the prompt
     * @return true if the user responded with yes, false otherwise
     */
    public static boolean run(String prompt) {
        prompt += " (yes/no)";
        System.out.println(prompt);

        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().toLowerCase();
        if(response.equals("yes") || response.equals("y")) {
            return true;
        }
        return false;
    }
}
