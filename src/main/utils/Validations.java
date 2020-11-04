package main.utils;

/**
 * Class Validations is a static class that offers a couple helper methods,
 * which throw IllegalArgumentException if a relevant condition fails.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Validations {
    /**
     * Throws an IllegalArgumentException if value is negative.
     * @param value the value in question
     * @param name variable name that wraps value
     */
    public static void nonNegative(int value, String name) {
        if(value < 0) {
            throw new IllegalArgumentException(name + " can't be negative!");
        }
    }
}
