package test.attributes;

import main.attributes.HigherLevelComparator;
import main.attributes.Level;
import main.legends.Legend;
import main.legends.LegendList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    @Test
    public void compare() {
        Level lower = new Level(12);
        Level higher = new Level(14);
        assertTrue(lower.isLessThan(higher));
        assertTrue(lower.compareTo(higher) < 0);

        // Test higher level comparator
        Legend first = LegendList.getInstance().getHeroes().get(0);
        Legend second = LegendList.getInstance().getHeroes().get(1);
        second.getLevel().setLevel(first.getLevel().getLevel() + 1); // second has higher level
        List<Legend> levels = new ArrayList<>(Arrays.asList(first, second));

        Collections.sort(levels, new HigherLevelComparator());
        assertEquals(levels.get(0), second);
        assertEquals(levels.get(1), first);
    }
}
