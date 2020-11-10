package test.legends;

import main.legends.Hero;
import main.legends.LegendList;
import main.legends.Monster;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class TestLegendsList
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/8/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestLegendsList {
    @Test
    public void readLegends() {
        LegendList legendList = LegendList.getInstance();
        List<Hero> heroes = legendList.getHeroes();
        List<Monster> monsters = legendList.getMonsters();

        assertEquals(18, heroes.size());
        assertEquals(11 + 12 + 12, monsters.size());
    }

    @Test
    public void findHeroByName() {
        LegendList legendList = LegendList.getInstance();
        Hero hero = legendList.findHeroByName("Undefeated_Yoj");
        assertEquals(400, hero.getMana().getManaAmount());
        assertEquals(800, hero.getStrength().getAbilityValue());
        assertEquals(400, hero.getAgility().getAbilityValue());
        assertEquals(700, hero.getDexterity().getAbilityValue());
        assertEquals(2500, hero.getCoffer().getNumCoins());
        assertEquals(1, hero.getLevel().getLevel());
        assertEquals(7, hero.getExperience().getExperience());
        assertEquals(100, hero.getHealthPower().getHealthPower());
    }

    @Test
    public void findMonsterByName() {
        LegendList legendList = LegendList.getInstance();
        Monster monster = legendList.findMonsterByName("Chrysophylax");
        assertEquals(2, monster.getLevel().getLevel());
        assertEquals(200, monster.getDamageAmount());
        assertEquals(500, monster.getDefenseAmount());
        assertEquals(20, monster.getAgility().getAbilityValue());
        assertEquals(200, monster.getHealthPower().getHealthPower());
    }
}
