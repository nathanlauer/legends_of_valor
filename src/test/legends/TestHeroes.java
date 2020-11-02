package test.legends;

import main.attributes.Ability;
import main.attributes.Level;
import main.attributes.UncappedHealthPower;
import main.legends.Paladin;
import main.legends.Sorcerer;
import main.legends.Warrior;
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
    @Test
    public void testSorcerers() {
        Sorcerer gandalf = new Sorcerer();
        assertEquals(Sorcerer.defaultName, gandalf.getName());
        assertEquals(new Level(0), gandalf.getLevel());
        assertEquals(new UncappedHealthPower(0), gandalf.getHealthPower());
        assertEquals(new Ability("Agility", 0), gandalf.getAgility());
        assertEquals(new Ability("Dexterity", 0), gandalf.getDexterity());
        assertEquals(new Ability("Strength", 0), gandalf.getStrength());

        Ability agility = new Ability("Agility", 1000);
        Ability dexterity = new Ability("Dexterity", 900);
        Ability strength = new Ability("Strength", 200);
        gandalf = new Sorcerer("Gandalf", new Level(34), new UncappedHealthPower(100), agility, dexterity, strength);
        assertEquals("Gandalf", gandalf.getName());
        assertEquals(new Level(34), gandalf.getLevel());
        assertEquals(new UncappedHealthPower(100), gandalf.getHealthPower());
        assertEquals(agility, gandalf.getAgility());
        assertEquals(dexterity, gandalf.getDexterity());
        assertEquals(strength, gandalf.getStrength());
    }

    @Test
    public void testWarriors() {
        Warrior gimli = new Warrior();
        assertEquals(Warrior.defaultName, gimli.getName());
        assertEquals(new Level(0), gimli.getLevel());
        assertEquals(new UncappedHealthPower(0), gimli.getHealthPower());
        assertEquals(new Ability("Agility", 0), gimli.getAgility());
        assertEquals(new Ability("Dexterity", 0), gimli.getDexterity());
        assertEquals(new Ability("Strength", 0), gimli.getStrength());

        Ability agility = new Ability("Agility", 1000);
        Ability dexterity = new Ability("Dexterity", 900);
        Ability strength = new Ability("Strength", 200);
        gimli = new Warrior("Gimli", new Level(34), new UncappedHealthPower(100), agility, dexterity, strength);
        assertEquals("Gimli", gimli.getName());
        assertEquals(new Level(34), gimli.getLevel());
        assertEquals(new UncappedHealthPower(100), gimli.getHealthPower());
        assertEquals(agility, gimli.getAgility());
        assertEquals(dexterity, gimli.getDexterity());
        assertEquals(strength, gimli.getStrength());
    }

    @Test
    public void testPaladins() {
        Paladin aragorn = new Paladin();
        assertEquals(Paladin.defaultName, aragorn.getName());
        assertEquals(new Level(0), aragorn.getLevel());
        assertEquals(new UncappedHealthPower(0), aragorn.getHealthPower());
        assertEquals(new Ability("Agility", 0), aragorn.getAgility());
        assertEquals(new Ability("Dexterity", 0), aragorn.getDexterity());
        assertEquals(new Ability("Strength", 0), aragorn.getStrength());

        Ability agility = new Ability("Agility", 1000);
        Ability dexterity = new Ability("Dexterity", 900);
        Ability strength = new Ability("Strength", 200);
        aragorn = new Paladin("Aragorn", new Level(34), new UncappedHealthPower(100), agility, dexterity, strength);
        assertEquals("Aragorn", aragorn.getName());
        assertEquals(new Level(34), aragorn.getLevel());
        assertEquals(new UncappedHealthPower(100), aragorn.getHealthPower());
        assertEquals(agility, aragorn.getAgility());
        assertEquals(dexterity, aragorn.getDexterity());
        assertEquals(strength, aragorn.getStrength());
    }

    @Test
    public void testEquality() {
        Ability agility = new Ability("Agility", 1000);
        Ability dexterity = new Ability("Dexterity", 900);
        Ability strength = new Ability("Strength", 200);

        Sorcerer gandalf = new Sorcerer("Gandalf", new Level(34), new UncappedHealthPower(100), agility, dexterity, strength);
        Warrior gimli = new Warrior("Gimli", new Level(34), new UncappedHealthPower(100), agility, dexterity, strength);
        Paladin aragorn = new Paladin("Aragorn", new Level(34), new UncappedHealthPower(100), agility, dexterity, strength);
        assertNotEquals(gandalf, gimli);
        assertNotEquals(gandalf, aragorn);
        assertNotEquals(gimli, aragorn);

        Sorcerer otherSorcerer = new Sorcerer("Gandalf", new Level(34), new UncappedHealthPower(100), agility, dexterity, strength);
        Warrior otherWarrior = new Warrior("Gimli", new Level(34), new UncappedHealthPower(100), agility, dexterity, strength);
        Paladin otherPaladin = new Paladin("Aragorn", new Level(34), new UncappedHealthPower(100), agility, dexterity, strength);
        assertEquals(gandalf, otherSorcerer);
        assertEquals(gimli, otherWarrior);
        assertEquals(aragorn, otherPaladin);
    }
}
