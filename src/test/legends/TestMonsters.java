package test.legends;

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
        assertEquals("Dragon Monster!", rhaegal.getName());
        assertEquals(new Level(0), rhaegal.getLevel());
        assertEquals(new UncappedHealthPower(0), rhaegal.getHealthPower());
        assertEquals(0, rhaegal.getAttackDamage());
        assertEquals(0, rhaegal.getDefense());
        assertEquals(0, rhaegal.getDodgeChance());

        Dragon drogon = new Dragon("Drogon", new Level(34), new UncappedHealthPower(100), 1000, 900, 0.75);
        assertEquals("Drogon", drogon.getName());
        assertEquals(new Level(34), drogon.getLevel());
        assertEquals(new UncappedHealthPower(100), drogon.getHealthPower());
        assertEquals(1000, drogon.getAttackDamage());
        assertEquals(900, drogon.getDefense());
        assertEquals(0.75, drogon.getDodgeChance());
    }

    @Test
    public void testExoskeletons() {
        Exoskeleton beetle = new Exoskeleton(); // beetles are monsters indeed.
        assertEquals("Exoskeleton Monster!", beetle.getName());
        assertEquals(new Level(0), beetle.getLevel());
        assertEquals(new UncappedHealthPower(0), beetle.getHealthPower());
        assertEquals(0, beetle.getAttackDamage());
        assertEquals(0, beetle.getDefense());
        assertEquals(0, beetle.getDodgeChance());

        Exoskeleton rancor = new Exoskeleton("Rancor", new Level(34), new UncappedHealthPower(100), 1000, 900, 0.75);
        assertEquals("Rancor", rancor.getName());
        assertEquals(new Level(34), rancor.getLevel());
        assertEquals(new UncappedHealthPower(100), rancor.getHealthPower());
        assertEquals(1000, rancor.getAttackDamage());
        assertEquals(900, rancor.getDefense());
        assertEquals(0.75, rancor.getDodgeChance());
    }

    @Test
    public void testSpirits() {
        Spirit ghost = new Spirit(); // beetles are monsters indeed.
        assertEquals("Spirit Monster!", ghost.getName());
        assertEquals(new Level(0), ghost.getLevel());
        assertEquals(new UncappedHealthPower(0), ghost.getHealthPower());
        assertEquals(0, ghost.getAttackDamage());
        assertEquals(0, ghost.getDefense());
        assertEquals(0, ghost.getDodgeChance());

        Spirit dementor = new Spirit("Dementor", new Level(34), new UncappedHealthPower(100), 1000, 900, 0.75);
        assertEquals("Dementor", dementor.getName());
        assertEquals(new Level(34), dementor.getLevel());
        assertEquals(new UncappedHealthPower(100), dementor.getHealthPower());
        assertEquals(1000, dementor.getAttackDamage());
        assertEquals(900, dementor.getDefense());
        assertEquals(0.75, dementor.getDodgeChance());
    }

    @Test
    public void testEquality() {
        Spirit dementor = new Spirit("Dementor", new Level(34), new UncappedHealthPower(100), 1000, 900, 0.75);
        Exoskeleton rancor = new Exoskeleton("Rancor", new Level(34), new UncappedHealthPower(100), 1000, 900, 0.75);
        Dragon drogon = new Dragon("Drogon", new Level(34), new UncappedHealthPower(100), 1000, 900, 0.75);
        assertNotEquals(dementor, rancor);
        assertNotEquals(dementor, drogon);
        assertNotEquals(drogon, rancor);

        Spirit otherSpirit = new Spirit("Dementor", new Level(34), new UncappedHealthPower(100), 1000, 900, 0.75);
        Exoskeleton otherExo = new Exoskeleton("Rancor", new Level(34), new UncappedHealthPower(100), 1000, 900, 0.75);
        Dragon otherDragon = new Dragon("Drogon", new Level(34), new UncappedHealthPower(100), 1000, 900, 0.75);
        assertEquals(dementor, otherSpirit);
        assertEquals(rancor, otherExo);
        assertEquals(drogon, otherDragon);
    }
}
