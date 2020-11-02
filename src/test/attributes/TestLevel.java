package test.attributes;

import main.attributes.Level;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class Level
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestLevel {
    @Test
    public void incrementLevel() {
        Level level = new Level();
        assertEquals(0, level.getLevel());

        level.incrementLevel();
        assertEquals(1, level.getLevel());
    }

    @Test
    public void setLevel() {
        Level level = new Level(17);
        assertEquals(17, level.getLevel());

        level.setLevel(24);
        assertEquals(24, level.getLevel());

        level.setLevel(5);
        assertEquals(5, level.getLevel());

        level.setLevel(0);
        assertEquals(0, level.getLevel());

        try {
            level.setLevel(-21);
            fail("Should have thrown an Illegal argument exception!");
        } catch (IllegalArgumentException e) {
            // passed: level should still be 0 from before
            assertEquals(0, level.getLevel());
        }
    }

    @Test
    public void equals() {
        Level first = new Level(23);
        Level second = new Level(22);

        assertNotEquals(first, second);
        second.incrementLevel();
        assertEquals(first, second);
    }
}
