package test.utils;

import main.utils.Validations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class TestValidations contains a series of tests which collectively test
 * the functionality in main.utils.Validations.java
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestValidations {
    @Test
    public void intNonNegative() {
        int intValue = -1;
        try {
            Validations.nonNegative(intValue, "Value");
            fail("Should have thrown an exception!");
        } catch (IllegalArgumentException e) {
            // succeeded
        }

        intValue = 0;
        try {
            Validations.nonNegative(intValue, "Value");
            // succeeded
        } catch (IllegalArgumentException e) {
            fail("Should not have thrown an exception!");
        }

        intValue = 1;
        try {
            Validations.nonNegative(intValue, "Value");
            // succeeded
        } catch (IllegalArgumentException e) {
            fail("Should not have thrown an exception!");
        }
    }

    @Test
    public void doubleNonNegative() {
        double doubleValue = -1.0;
        try {
            Validations.nonNegative(doubleValue, "Value");
            fail("Should have thrown an exception!");
        } catch (IllegalArgumentException e) {
            // succeeded
        }

        doubleValue = 0.0;
        try {
            Validations.nonNegative(doubleValue, "Value");
            // succeeded
        } catch (IllegalArgumentException e) {
            fail("Should not have thrown an exception!");
        }

        doubleValue = 1.0;
        try {
            Validations.nonNegative(doubleValue, "Value");
            // succeeded
        } catch (IllegalArgumentException e) {
            fail("Should not have thrown an exception!");
        }
    }
}
