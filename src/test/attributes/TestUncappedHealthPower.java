package test.attributes;

import main.attributes.CappedHealthPower;
import main.attributes.UncappedHealthPower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class TestUncappedHealthPower
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestUncappedHealthPower {
    @Test
    public void incrementHealthPower() {
        UncappedHealthPower uhp = new UncappedHealthPower();
        assertEquals(0.0, uhp.getHealthPower());

        try {
            uhp.increaseHealthPowerBy(-10);
            fail("Should have thrown an IllegalArgumentException!");
        } catch (IllegalArgumentException e) {
            // passed
        }

        uhp.increaseHealthPowerBy(45);
        assertEquals(45, uhp.getHealthPower());

        uhp.increaseHealthPowerBy(10);
        assertEquals(55, uhp.getHealthPower());

        uhp.increaseHealthPowerBy(200); // exceed the max, which shouldn't matter
        assertEquals(255, uhp.getHealthPower());

        uhp.setHealthPower(14);
        assertEquals(14, uhp.getHealthPower());
    }

    @Test
    public void reduceHealthPower() {
        UncappedHealthPower uhp = new UncappedHealthPower(100);
        try {
            uhp.reduceHealthPowerBy(-10);
            fail("Should have thrown an IllegalArgumentException!");
        } catch (IllegalArgumentException e) {
            // passed
        }

        uhp.reduceHealthPowerBy(30);
        assertEquals(70, uhp.getHealthPower());
        uhp.reduceHealthPowerBy(140);
        assertEquals(0, uhp.getHealthPower()); //can't have less than 0 health power
    }

    @Test
    public void equals() {
        UncappedHealthPower first = new UncappedHealthPower(50);
        UncappedHealthPower second = new UncappedHealthPower(100);
        assertNotEquals(first, second);
        first.increaseHealthPowerBy(50);
        assertEquals(first, second);

        CappedHealthPower capped = new CappedHealthPower(100);
        assertNotEquals(first, capped);
        assertNotEquals(second, capped);
    }

    @Test
    public void percentage() {
        UncappedHealthPower uhp = new UncappedHealthPower(50);
        uhp.increaseByPercentage(10);
        assertEquals(55, uhp.getHealthPower());
        uhp.increaseByPercentage(20);
        assertEquals(66, uhp.getHealthPower()); // round up to ceiling
    }
}
