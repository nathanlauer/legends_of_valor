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
    public static void nonNegative(double value, String name) {
        if(value < 0.0) {
            throw new IllegalArgumentException(name + " can't be negative!");
        }
    }

    /**
     * Throws an IllegalArgumentException if value is negative.
     * @param value the value in question
     * @param name variable name that wraps value
     */
    public static void nonNegative(int value, String name) {
        Validations.nonNegative((double)value, name);
    }

    /**
     * Throws an IllegalArgumentException if value is less than 0 or greater than 1.
     * That is, value must be a percentage.
     * @param value the value in question.
     * @param name name of the variable in question.
     */
    public static void percentage(double value, String name) {
        if(value < 0.0 || value > 1.0) {
            throw new IllegalArgumentException(name + " must be a percentage! Valid range is [0, 1]");
        }
    }
}
