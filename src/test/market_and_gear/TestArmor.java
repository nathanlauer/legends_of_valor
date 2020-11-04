package test.market_and_gear;

import main.attributes.Level;
import main.market_and_gear.Armor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class TestArmor
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestArmor {
    private final String name = "Plate";
    private final Level minLevel = new Level(10);
    private final int price = 250;
    private final int defense = 500;
    private final Armor armor;

    public TestArmor() {
        armor = new Armor(name, price, minLevel, defense);
    }

    @Test
    public void emptyInit() {
        Armor armor = new Armor();
        assertEquals(Armor.defaultName, armor.getName());
        assertEquals(new Level(0), armor.getMinLevel());
        assertEquals(0.0, armor.getPrice());
        assertEquals(0.0, armor.getDefense());
    }

    @Test
    public void nonEmptyInit() {
        assertEquals(name, armor.getName());
        assertEquals(minLevel, armor.getMinLevel());
        assertEquals(price, armor.getPrice());
        assertEquals(defense, armor.getDefense());
    }

    @Test
    public void attributes() {
        armor.setName("Shield");
        try {
            armor.setPrice(-100);
            fail();
        } catch (IllegalArgumentException e) {
            // passed
            armor.setPrice(200);
        }
        armor.setMinLevel(new Level(34));
        try {
            armor.setDefense(-34);
            fail();
        } catch (IllegalArgumentException e) {
            // passed
            armor.setDefense(400);
        }

        assertEquals("Shield", armor.getName());
        assertEquals(new Level(34), armor.getMinLevel());
        assertEquals(200, armor.getPrice());
        assertEquals(400, armor.getDefense());
    }

    @Test
    public void testClone() {
        try {
            Armor other = (Armor)armor.clone();
            assertEquals(name, other.getName());
            assertEquals(minLevel, other.getMinLevel());
            assertEquals(price, other.getPrice());
            assertEquals(defense, other.getDefense());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            fail();
        }
    }
}
