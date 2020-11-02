package test.attributes;

import main.attributes.CappedHealthPower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class TestCappedHealthPower
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestCappedHealthPower {
    @Test
    public void initialization() {
        CappedHealthPower first = new CappedHealthPower();
        assertEquals(CappedHealthPower.defaultMax, first.getMaxHealthValue());
        assertEquals(CappedHealthPower.defaultMax, first.getHealthPower());

        CappedHealthPower second = new CappedHealthPower(50);
        assertEquals(50, second.getMaxHealthValue());
        assertEquals(50, second.getHealthPower());

        CappedHealthPower third = new CappedHealthPower(75, 100);
        assertEquals(75, third.getHealthPower());
        assertEquals(100, third.getMaxHealthValue());
    }

    @Test
    public void increaseHealthPower() {
        CappedHealthPower chp = new CappedHealthPower(75, 100);
        try {
            chp.increaseHealthPowerBy(-10);
            fail("Should have thrown an IllegalArgumentException!");
        } catch (IllegalArgumentException e) {
            // passed
        }

        chp.increaseHealthPowerBy(15);
        assertEquals(90, chp.getHealthPower());
        chp.increaseHealthPowerBy(15); // would go to 105 if uncapped
        assertEquals(100, chp.getHealthPower());
    }

    @Test
    public void setHealthPower() {
        CappedHealthPower chp = new CappedHealthPower(75, 100);
        chp.setHealthPower(110);
        assertEquals(100, chp.getHealthPower());
    }

    @Test
    public void setMaxHealthPower() {
        CappedHealthPower chp = new CappedHealthPower(75, 100);
        chp.setMaxHealthValue(50);
        assertEquals(50, chp.getHealthPower());
        assertEquals(50, chp.getMaxHealthValue());
    }
}
