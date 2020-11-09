package test.legends;

import main.attributes.*;
import main.legends.Dragon;
import main.legends.Exoskeleton;
import main.legends.Spirit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Class TestMonsters
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestMonsters {
    @Test
    public void testDragons() {
        Dragon rhaegal = new Dragon(); // rhaegal is sad :(
        assertEquals(Dragon.defaultName, rhaegal.getName());
        assertEquals(new Level(0), rhaegal.getLevel());
        assertEquals(new UncappedHealthPower(0), rhaegal.getHealthPower());
        assertEquals(AbilityBuilder.baseStrengthAbility(), rhaegal.getStrength());
        assertEquals(AbilityBuilder.baseDefenseAbility(), rhaegal.getDefense());
        assertEquals(0, rhaegal.getDodgeChance());

        Ability damage = new Ability(AbilityType.STRENGTH, 1000);
        Ability defense = new Ability(AbilityType.DEFENSE, 900);
        Ability dodgeChance = new Ability(AbilityType.AGILITY, 65);

        Dragon drogon = new Dragon("Drogon", new Level(34), new UncappedHealthPower(100), damage, defense, dodgeChance);
        assertEquals("Drogon", drogon.getName());
        assertEquals(new Level(34), drogon.getLevel());
        assertEquals(new UncappedHealthPower(100), drogon.getHealthPower());
        assertEquals(damage, drogon.getStrength());
        assertEquals(defense, drogon.getDefense());
        assertEquals(65 * 0.01, drogon.getDodgeChance());
    }

    @Test
    public void testExoskeletons() {
        Exoskeleton beetle = new Exoskeleton(); // beetles are monsters indeed.
        assertEquals(Exoskeleton.defaultName, beetle.getName());
        assertEquals(new Level(0), beetle.getLevel());
        assertEquals(new UncappedHealthPower(0), beetle.getHealthPower());
        assertEquals(AbilityBuilder.baseStrengthAbility(), beetle.getStrength());
        assertEquals(AbilityBuilder.baseDefenseAbility(), beetle.getDefense());
        assertEquals(0, beetle.getDodgeChance());

        Ability damage = new Ability(AbilityType.STRENGTH, 1000);
        Ability defense = new Ability(AbilityType.DEFENSE, 900);
        Ability dodgeChance = new Ability(AbilityType.AGILITY, 65);

        Exoskeleton rancor = new Exoskeleton("Rancor", new Level(34), new UncappedHealthPower(100), damage, defense, dodgeChance);
        assertEquals("Rancor", rancor.getName());
        assertEquals(new Level(34), rancor.getLevel());
        assertEquals(new UncappedHealthPower(100), rancor.getHealthPower());
        assertEquals(damage, rancor.getStrength());
        assertEquals(defense, rancor.getDefense());
        assertEquals(65 * 0.01, rancor.getDodgeChance());
    }

    @Test
    public void testSpirits() {
        Spirit ghost = new Spirit(); // beetles are monsters indeed.
        assertEquals(Spirit.defaultName, ghost.getName());
        assertEquals(new Level(0), ghost.getLevel());
        assertEquals(new UncappedHealthPower(0), ghost.getHealthPower());
        assertEquals(AbilityBuilder.baseStrengthAbility(), ghost.getStrength());
        assertEquals(AbilityBuilder.baseDefenseAbility(), ghost.getDefense());
        assertEquals(0, ghost.getDodgeChance());

        Ability damage = new Ability(AbilityType.STRENGTH, 1000);
        Ability defense = new Ability(AbilityType.DEFENSE, 900);
        Ability dodgeChance = new Ability(AbilityType.AGILITY, 65);

        Spirit dementor = new Spirit("Dementor", new Level(34), new UncappedHealthPower(100), damage, defense, dodgeChance);
        assertEquals("Dementor", dementor.getName());
        assertEquals(new Level(34), dementor.getLevel());
        assertEquals(new UncappedHealthPower(100), dementor.getHealthPower());
        assertEquals(damage, dementor.getStrength());
        assertEquals(defense, dementor.getDefense());
        assertEquals(65 * 0.01, dementor.getDodgeChance());
    }

    @Test
    public void testEquality() {
        Ability damage = new Ability(AbilityType.STRENGTH, 1000);
        Ability defense = new Ability(AbilityType.DEFENSE, 900);
        Ability dodgeChance = new Ability(AbilityType.AGILITY, 65);

        Spirit dementor = new Spirit("Dementor", new Level(34), new UncappedHealthPower(100), damage, defense, dodgeChance);
        Exoskeleton rancor = new Exoskeleton("Rancor", new Level(34), new UncappedHealthPower(100), damage, defense, dodgeChance);
        Dragon drogon = new Dragon("Drogon", new Level(34), new UncappedHealthPower(100), damage, defense, dodgeChance);
        assertNotEquals(dementor, rancor);
        assertNotEquals(dementor, drogon);
        assertNotEquals(drogon, rancor);

        Spirit otherSpirit = new Spirit("Dementor", new Level(34), new UncappedHealthPower(100), damage, defense, dodgeChance);
        Exoskeleton otherExo = new Exoskeleton("Rancor", new Level(34), new UncappedHealthPower(100), damage, defense, dodgeChance);
        Dragon otherDragon = new Dragon("Drogon", new Level(34), new UncappedHealthPower(100), damage, defense, dodgeChance);
        assertEquals(dementor, otherSpirit);
        assertEquals(rancor, otherExo);
        assertEquals(drogon, otherDragon);
    }
}
