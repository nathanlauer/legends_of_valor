package test.market_and_gear;

import main.attributes.Level;
import main.market_and_gear.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class TestWeapon
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestWeapon {
    @Test
    public void emptyInit() {
        Weapon weapon = new Weapon();
        assertEquals(Weapon.defaultName, weapon.getName());
        assertEquals(new Level(0), weapon.getMinLevel());
        assertEquals(0.0, weapon.getPrice());
        assertEquals(0.0, weapon.getDamage());
        assertEquals(0, weapon.getNumHands());
    }

    @Test
    public void nonEmptyInit() {
        String name = "Sword";
        Level minLevel = new Level(10);
        double price = 250;
        double damage = 500;
        int numHands = 2;

        Weapon weapon = new Weapon(name, price, minLevel, damage, numHands);
        assertEquals("Sword", weapon.getName());
        assertEquals(new Level(10), weapon.getMinLevel());
        assertEquals(price, weapon.getPrice());
        assertEquals(damage, weapon.getDamage());
        assertEquals(numHands, weapon.getNumHands());
    }

    @Test
    public void attributes() {
        String name = "Sword";
        Level minLevel = new Level(10);
        double price = 250;
        double damage = 500;
        int numHands = 2;

        Weapon weapon = new Weapon(name, price, minLevel, damage, numHands);

        weapon.setName("Axe");
        try {
            weapon.setPrice(-100);
            fail();
        } catch (IllegalArgumentException e) {
            // passed
            weapon.setPrice(200);
        }
        weapon.setMinLevel(new Level(34));
        try {
            weapon.setDamage(-34);
            fail();
        } catch (IllegalArgumentException e) {
            // passed
            weapon.setDamage(400);
        }
        try {
            weapon.setNumHands(-1);
            fail();
        } catch (IllegalArgumentException e) {
            // passed
            weapon.setNumHands(1);
        }

        assertEquals("Axe", weapon.getName());
        assertEquals(new Level(34), weapon.getMinLevel());
        assertEquals(200, weapon.getPrice());
        assertEquals(400, weapon.getDamage());
        assertEquals(1, weapon.getNumHands());
    }
}
