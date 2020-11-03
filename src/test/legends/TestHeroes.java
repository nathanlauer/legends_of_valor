package test.legends;

import main.attributes.*;
import main.legends.Paladin;
import main.legends.Sorcerer;
import main.legends.Warrior;
import main.utils.Coffer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Class TestHeroes
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestHeroes {
    private final Ability agility;
    private final Ability dexterity;
    private final Ability strength;
    private final Mana mana;
    private final Coffer coffer;

    public TestHeroes() {
        agility = new Ability("Agility", 1000);
        dexterity = new Ability("Dexterity", 900);
        strength = new Ability("Strength", 200);
        coffer = new Coffer(50);
        mana = new Mana(300);
    }

    @Test
    public void testSorcerers() {
        Sorcerer gandalf = new Sorcerer();
        assertEquals(Sorcerer.defaultName, gandalf.getName());
        assertEquals(new Level(0), gandalf.getLevel());
        assertEquals(new UncappedHealthPower(0), gandalf.getHealthPower());
        assertEquals(AbilityBuilder.baseAgilityAbility(), gandalf.getAgility());
        assertEquals(AbilityBuilder.baseDexterityAbility(), gandalf.getDexterity());
        assertEquals(AbilityBuilder.baseStrengthAbility(), gandalf.getStrength());
        assertEquals(new Coffer(0), gandalf.getCoffer());
        assertEquals(new Mana(0), gandalf.getMana());

        gandalf = new Sorcerer("Gandalf", new Level(34), new UncappedHealthPower(100), mana, coffer, agility, dexterity, strength);
        assertEquals("Gandalf", gandalf.getName());
        assertEquals(new Level(34), gandalf.getLevel());
        assertEquals(new UncappedHealthPower(100), gandalf.getHealthPower());
        assertEquals(agility, gandalf.getAgility());
        assertEquals(dexterity, gandalf.getDexterity());
        assertEquals(strength, gandalf.getStrength());
        assertEquals(mana, gandalf.getMana());
        assertEquals(coffer, gandalf.getCoffer());
    }

    @Test
    public void testWarriors() {
        Warrior gimli = new Warrior();
        assertEquals(Warrior.defaultName, gimli.getName());
        assertEquals(new Level(0), gimli.getLevel());
        assertEquals(new UncappedHealthPower(0), gimli.getHealthPower());
        assertEquals(AbilityBuilder.baseAgilityAbility(), gimli.getAgility());
        assertEquals(AbilityBuilder.baseDexterityAbility(), gimli.getDexterity());
        assertEquals(AbilityBuilder.baseStrengthAbility(), gimli.getStrength());
        assertEquals(new Coffer(0), gimli.getCoffer());
        assertEquals(new Mana(0), gimli.getMana());

        gimli = new Warrior("Gimli", new Level(34), new UncappedHealthPower(100), mana, coffer, agility, dexterity, strength);
        assertEquals("Gimli", gimli.getName());
        assertEquals(new Level(34), gimli.getLevel());
        assertEquals(new UncappedHealthPower(100), gimli.getHealthPower());
        assertEquals(agility, gimli.getAgility());
        assertEquals(dexterity, gimli.getDexterity());
        assertEquals(strength, gimli.getStrength());
        assertEquals(mana, gimli.getMana());
        assertEquals(coffer, gimli.getCoffer());
    }

    @Test
    public void testPaladins() {
        Paladin aragorn = new Paladin();
        assertEquals(Paladin.defaultName, aragorn.getName());
        assertEquals(new Level(0), aragorn.getLevel());
        assertEquals(new UncappedHealthPower(0), aragorn.getHealthPower());
        assertEquals(AbilityBuilder.baseAgilityAbility(), aragorn.getAgility());
        assertEquals(AbilityBuilder.baseDexterityAbility(), aragorn.getDexterity());
        assertEquals(AbilityBuilder.baseStrengthAbility(), aragorn.getStrength());
        assertEquals(new Coffer(0), aragorn.getCoffer());
        assertEquals(new Mana(0), aragorn.getMana());

        aragorn = new Paladin("Aragorn", new Level(34), new UncappedHealthPower(100), mana, coffer, agility, dexterity, strength);
        assertEquals("Aragorn", aragorn.getName());
        assertEquals(new Level(34), aragorn.getLevel());
        assertEquals(new UncappedHealthPower(100), aragorn.getHealthPower());
        assertEquals(agility, aragorn.getAgility());
        assertEquals(dexterity, aragorn.getDexterity());
        assertEquals(strength, aragorn.getStrength());
        assertEquals(mana, aragorn.getMana());
        assertEquals(coffer, aragorn.getCoffer());
    }

    @Test
    public void testEquality() {
        Sorcerer gandalf = new Sorcerer("Gandalf", new Level(34), new UncappedHealthPower(100), mana, coffer, agility, dexterity, strength);
        Warrior gimli = new Warrior("Gimli", new Level(34), new UncappedHealthPower(100), mana, coffer, agility, dexterity, strength);
        Paladin aragorn = new Paladin("Aragorn", new Level(34), new UncappedHealthPower(100), mana, coffer, agility, dexterity, strength);
        assertNotEquals(gandalf, gimli);
        assertNotEquals(gandalf, aragorn);
        assertNotEquals(gimli, aragorn);

        Sorcerer otherSorcerer = new Sorcerer("Gandalf", new Level(34), new UncappedHealthPower(100), mana, coffer, agility, dexterity, strength);
        Warrior otherWarrior = new Warrior("Gimli", new Level(34), new UncappedHealthPower(100), mana, coffer, agility, dexterity, strength);
        Paladin otherPaladin = new Paladin("Aragorn", new Level(34), new UncappedHealthPower(100), mana, coffer, agility, dexterity, strength);
        assertEquals(gandalf, otherSorcerer);
        assertEquals(gimli, otherWarrior);
        assertEquals(aragorn, otherPaladin);
    }
}
