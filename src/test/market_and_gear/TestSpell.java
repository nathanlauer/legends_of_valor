package test.market_and_gear;

import main.attributes.Ability;
import main.attributes.AbilityBuilder;
import main.attributes.Level;
import main.attributes.Mana;
import main.market_and_gear.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class TestSpell
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestSpell {
    private final String name = "Wingardium Leviooooosa";
    private final double price = 100;
    private final Level minLevel = new Level(10);
    private final Mana mana = new Mana(100);
    private final double damage = 20;

    @Test
    public void testIceSpell() {
        Spell iceSpell = new IceSpell(name, price, minLevel, mana, damage);
        testAttributes(iceSpell);
        assertEquals(AbilityBuilder.baseDamageAbility(), iceSpell.getAbility());
    }

    @Test
    public void testFireSpell() {
        Spell fireSpell = new FireSpell(name, price, minLevel, mana, damage);
        testAttributes(fireSpell);
        assertEquals(AbilityBuilder.baseDefenseAbility(), fireSpell.getAbility());
    }

    @Test
    public void testLightningSpell() {
        Spell lightningSpell = new LightningSpell(name, price, minLevel, mana, damage);
        testAttributes(lightningSpell);
        assertEquals(AbilityBuilder.baseDodgeChanceAbility(), lightningSpell.getAbility());
    }

    @Test
    public void testClone() {
        Spell lightningSpell = new LightningSpell(name, price, minLevel, mana, damage);
        try {
            Spell other = (LightningSpell)lightningSpell.clone();
            testAttributes(other);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            fail();
        }
    }

    public void testAttributes(Spell spell) {
        assertEquals(name, spell.getName());
        assertEquals(price, spell.getPrice());
        assertEquals(minLevel, spell.getMinLevel());
        assertEquals(mana, spell.getMana());
        assertEquals(damage, spell.getDamage());
    }
}
