package test.attributes;

import main.attributes.Ability;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class TestAbility
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestAbility {
    @Test
    public void initialization() {
        Ability strength = new Ability();
        assertEquals(Ability.defaultName, strength.getName());
        assertEquals(0, strength.getAbilityValue());

        strength = new Ability(100);
        assertEquals(Ability.defaultName, strength.getName());
        assertEquals(100, strength.getAbilityValue());

        strength = new Ability("Strength", 100);
        assertEquals("Strength", strength.getName());
        assertEquals(100, strength.getAbilityValue());
    }

    @Test
    public void setAbility() {
        Ability strength = new Ability("Strength", 100);
        assertEquals("Strength", strength.getName());
        assertEquals(100, strength.getAbilityValue());

        strength.setAbilityValue(730);
        assertEquals(730, strength.getAbilityValue());

        try {
            strength.setAbilityValue(-110);
            fail();
        } catch (IllegalArgumentException e) {
            //passed
        }
    }

    @Test
    public void increaseAbility() {
        Ability strength = new Ability("Strength", 100);
        assertEquals("Strength", strength.getName());
        assertEquals(100, strength.getAbilityValue());

        strength.increaseAbilityBy(40);
        assertEquals(140, strength.getAbilityValue());

        try {
            strength.increaseAbilityBy(-110);
            fail();
        } catch (IllegalArgumentException e) {
            //passed, shouldn't have changed value
            assertEquals(140, strength.getAbilityValue());
        }
    }

    @Test
    public void decreaseAbility() {
        Ability strength = new Ability("Strength", 100);
        assertEquals("Strength", strength.getName());
        assertEquals(100, strength.getAbilityValue());

        strength.decreaseAbilityBy(40);
        assertEquals(60, strength.getAbilityValue());

        try {
            strength.decreaseAbilityBy(-5);
            fail();
        } catch (IllegalArgumentException e) {
            //passed, shouldn't have changed value
            assertEquals(60, strength.getAbilityValue());
        }
    }
}
