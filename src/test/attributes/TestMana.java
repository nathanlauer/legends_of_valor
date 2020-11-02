package test.attributes;

import main.attributes.Mana;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class TestMana
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestMana {
    @Test
    public void setMana() {
        Mana mana = new Mana(10);
        assertEquals(10, mana.getManaAmount());

        mana.setMana(50);
        assertEquals(50, mana.getManaAmount());

        try {
            mana.setMana(-340);
            fail();
        } catch (IllegalArgumentException e) {
            // passed
            assertEquals(50, mana.getManaAmount());
        }
    }

    @Test
    public void increaseMana() {
        Mana mana = new Mana(10);
        assertEquals(10, mana.getManaAmount());

        mana.increaseManaBy(50);
        assertEquals(60, mana.getManaAmount());

        try {
            mana.increaseManaBy(-10);
            fail();
        } catch (IllegalArgumentException e) {
            // passed
            assertEquals(60, mana.getManaAmount());
        }
    }

    @Test
    public void decreaseMana() {
        Mana mana = new Mana(100);
        assertEquals(100, mana.getManaAmount());

        mana.decreaseManaBy(50);
        assertEquals(50, mana.getManaAmount());

        try {
            mana.decreaseManaBy(-10);
            fail();
        } catch (IllegalArgumentException e) {
            // passed
            assertEquals(50, mana.getManaAmount());
        }
    }
}
