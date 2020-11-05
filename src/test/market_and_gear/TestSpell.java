package test.market_and_gear;

import main.attributes.Ability;
import main.attributes.AbilityBuilder;
import main.attributes.Level;
import main.attributes.Mana;
import main.market_and_gear.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    private final int price = 100;
    private final int minLevelNum = 10;
    private final Level minLevel = new Level(minLevelNum);
    private final int manaNum = 100;
    private final Mana mana = new Mana(manaNum);
    private final int damage = 20;

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

            // Make some changes to the cloned object
            other.getMana().decreaseManaBy(10);
            other.getAbility().increaseAbilityBy(50);
            other.getMinLevel().incrementLevel();

            // Ensure these changes are reflected
            assertEquals(name, other.getName());
            assertEquals(price, other.getPrice());
            assertEquals(damage, other.getDamage());
            assertNotEquals(minLevel, other.getMinLevel());
            assertNotEquals(mana, other.getMana());
            assertNotEquals(lightningSpell.getAbility(), other.getAbility());
            assertEquals(new Mana(manaNum - 10), other.getMana());
            assertEquals(new Level(minLevelNum + 1), other.getMinLevel());
            Ability expected = AbilityBuilder.baseDodgeChanceAbility();
            expected.increaseAbilityBy(50);
            assertEquals(expected, other.getAbility());


            // but the original is still the same
            testAttributes(lightningSpell);
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

    @Test
    public void type() {
        assertTrue(new FireSpell().getType().equals(GearItemType.SPELL));
        assertTrue(new IceSpell().getType().equals(GearItemType.SPELL));
        assertTrue(new LightningSpell().getType().equals(GearItemType.SPELL));
    }
}
