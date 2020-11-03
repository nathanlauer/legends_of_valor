package test.market_and_gear;

import main.attributes.Ability;
import main.attributes.Level;
import main.market_and_gear.Armor;
import main.market_and_gear.Potion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class TestPotion
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestPotion {
    private final String name;
    private final Level minLevel;
    private final double price;
    private final double incrementAmount;
    private final Ability dexterity;
    private final Potion potion;

    public TestPotion() {
        name = "PolyJuice Potion";
        minLevel = new Level(10);
        price = 250;
        incrementAmount = 100;
        dexterity = new Ability("Dexterity", 300);
        potion = new Potion(name, price, minLevel, dexterity, incrementAmount);
    }

    @Test
    public void emptyInit() {
        Potion polyJuice = new Potion();
        assertEquals(Potion.defaultName, polyJuice.getName());
        assertEquals(new Level(0), polyJuice.getMinLevel());
        assertEquals(0.0, polyJuice.getPrice());
        assertEquals(0.0, polyJuice.getIncrementAmount());
        assertEquals(new Ability(), polyJuice.getAbility());
    }

    @Test
    public void nonEmptyInit() {
        assertEquals(name, potion.getName());
        assertEquals(minLevel, potion.getMinLevel());
        assertEquals(price, potion.getPrice());
        assertEquals(incrementAmount, potion.getIncrementAmount());
        assertEquals(dexterity, potion.getAbility());
    }

    @Test
    public void attributes() {
        potion.setName("Felix Felicis");
        try {
            potion.setPrice(-100);
            fail();
        } catch (IllegalArgumentException e) {
            // passed
            potion.setPrice(200);
        }
        potion.setMinLevel(new Level(34));
        try {
            potion.setIncrementAmount(-34);
            fail();
        } catch (IllegalArgumentException e) {
            // passed
            potion.setIncrementAmount(400);
        }
        potion.setAbility(new Ability("Agility", 10));

        assertEquals("Felix Felicis", potion.getName());
        assertEquals(new Level(34), potion.getMinLevel());
        assertEquals(200, potion.getPrice());
        assertEquals(400, potion.getIncrementAmount());
        assertEquals(new Ability("Agility", 10), potion.getAbility());
    }
}
