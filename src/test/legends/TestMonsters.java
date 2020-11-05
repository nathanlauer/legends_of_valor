package test.legends;

import main.attributes.Ability;
import main.attributes.AbilityBuilder;
import main.attributes.Level;
import main.attributes.UncappedHealthPower;
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
        assertEquals(AbilityBuilder.baseDamageAbility(), rhaegal.getDamage());
        assertEquals(AbilityBuilder.baseDefenseAbility(), rhaegal.getDefense());
        assertEquals(AbilityBuilder.baseDodgeChanceAbility(), rhaegal.getDodgeChance());

        Ability damage = new Ability("Damage", 1000);
        Ability defense = new Ability("Defense", 900);
        Ability dodgeChance = new Ability("DodgeChance", 200);

        Dragon drogon = new Dragon("Drogon", new Level(34), new UncappedHealthPower(100), damage, defense, dodgeChance);
        assertEquals("Drogon", drogon.getName());
        assertEquals(new Level(34), drogon.getLevel());
        assertEquals(new UncappedHealthPower(100), drogon.getHealthPower());
        assertEquals(damage, drogon.getDamage());
        assertEquals(defense, drogon.getDefense());
        assertEquals(dodgeChance, drogon.getDodgeChance());
    }

    @Test
    public void testExoskeletons() {
        Exoskeleton beetle = new Exoskeleton(); // beetles are monsters indeed.
        assertEquals(Exoskeleton.defaultName, beetle.getName());
        assertEquals(new Level(0), beetle.getLevel());
        assertEquals(new UncappedHealthPower(0), beetle.getHealthPower());
        assertEquals(AbilityBuilder.baseDamageAbility(), beetle.getDamage());
        assertEquals(AbilityBuilder.baseDefenseAbility(), beetle.getDefense());
        assertEquals(AbilityBuilder.baseDodgeChanceAbility(), beetle.getDodgeChance());

        Ability damage = new Ability("Damage", 1000);
        Ability defense = new Ability("Defense", 900);
        Ability dodgeChance = new Ability("DodgeChance", 200);

        Exoskeleton rancor = new Exoskeleton("Rancor", new Level(34), new UncappedHealthPower(100), damage, defense, dodgeChance);
        assertEquals("Rancor", rancor.getName());
        assertEquals(new Level(34), rancor.getLevel());
        assertEquals(new UncappedHealthPower(100), rancor.getHealthPower());
        assertEquals(damage, rancor.getDamage());
        assertEquals(defense, rancor.getDefense());
        assertEquals(dodgeChance, rancor.getDodgeChance());
    }

    @Test
    public void testSpirits() {
        Spirit ghost = new Spirit(); // beetles are monsters indeed.
        assertEquals(Spirit.defaultName, ghost.getName());
        assertEquals(new Level(0), ghost.getLevel());
        assertEquals(new UncappedHealthPower(0), ghost.getHealthPower());
        assertEquals(AbilityBuilder.baseDamageAbility(), ghost.getDamage());
        assertEquals(AbilityBuilder.baseDefenseAbility(), ghost.getDefense());
        assertEquals(AbilityBuilder.baseDodgeChanceAbility(), ghost.getDodgeChance());

        Ability damage = new Ability("Damage", 1000);
        Ability defense = new Ability("Defense", 900);
        Ability dodgeChance = new Ability("DodgeChance", 200);

        Spirit dementor = new Spirit("Dementor", new Level(34), new UncappedHealthPower(100), damage, defense, dodgeChance);
        assertEquals("Dementor", dementor.getName());
        assertEquals(new Level(34), dementor.getLevel());
        assertEquals(new UncappedHealthPower(100), dementor.getHealthPower());
        assertEquals(damage, dementor.getDamage());
        assertEquals(defense, dementor.getDefense());
        assertEquals(dodgeChance, dementor.getDodgeChance());
    }

    @Test
    public void testEquality() {
        Ability damage = new Ability("Damage", 1000);
        Ability defense = new Ability("Defense", 900);
        Ability dodgeChance = new Ability("DodgeChance", 200);

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
