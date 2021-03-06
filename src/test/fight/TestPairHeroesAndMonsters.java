package test.fight;

import main.fight.PairHeroesAndMonsters;
import main.legends.Hero;
import main.legends.LegendList;
import main.legends.Monster;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Class TestPairHeroesAndMonsters
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/8/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestPairHeroesAndMonsters {
    private final List<Hero> heroes;
    private final List<Monster> monsters;

    public TestPairHeroesAndMonsters() {
        heroes = LegendList.getInstance().getHeroes();
        monsters = LegendList.getInstance().getMonsters();
    }

    @Test
    public void defaultInitialPairing() {
        // Select the default - system will do an automatic pairing
        String input = "1";
        Scanner scanner = new Scanner(input);

        // Select 4 heroes, and 3 monsters
        List<Hero> relevantHeroes = heroes.subList(0, 4);
        List<Monster> relevantMonsters = monsters.subList(0, 3);

        PairHeroesAndMonsters pairing = new PairHeroesAndMonsters(scanner, relevantHeroes, relevantMonsters);
        pairing.initialPairing();

        // Set the hp of each of the Monsters, since some of the other tests may have killed them
        for(Monster monster : monsters) {
            monster.getHealthPower().setHealthPower(100);
        }

        // After the pairing, the first three Heroes should each be paired with their respective Monsters
        for(int i = 0; i < 3; i++) {
            Hero hero = relevantHeroes.get(i);
            List<Monster> monstersForHero = pairing.getMonstersForHero(hero);
            assertEquals(1, monstersForHero.size());
            assertEquals(relevantMonsters.get(i), monstersForHero.get(0));
        }

        // Each Monster should be paired with their respective Hero
        for(int i = 0; i < 3; i++) {
            Monster monster = relevantMonsters.get(i);
            List<Hero> heroesForMonster = pairing.getHeroesForMonster(monster);
            assertEquals(1, heroesForMonster.size());
            assertEquals(relevantHeroes.get(i), heroesForMonster.get(0));
        }

        // The final Hero should be paired with all three Monsters
        List<Monster> pairedWithFourthHero = pairing.getMonstersForHero(relevantHeroes.get(3));
        assertEquals(3, pairedWithFourthHero.size());
        assertEquals(relevantMonsters, pairedWithFourthHero);
    }

    @Test
    public void userDefinedPairing() {
        // In this test, we'll simulate the user pairing the first Hero with both Monsters,
        // and the second Hero with the first Monster.
        String input = "2\n" + // select user defined pairing
                "1\n" + // choose option "all"
                "2\n"; // choose first Monster ("1" is option All)
        Scanner scanner = new Scanner(input);

        List<Hero> relevantHeroes = heroes.subList(0, 2);
        List<Monster> relevantMonsters = monsters.subList(0, 2);

        PairHeroesAndMonsters pairing = new PairHeroesAndMonsters(scanner, relevantHeroes, relevantMonsters);
        pairing.initialPairing();

        // After the pairing, the first Hero should be paired with both Monsters
        List<Monster> monstersForFirstHero = pairing.getMonstersForHero(relevantHeroes.get(0));
        assertEquals(2, monstersForFirstHero.size());
        assertEquals(relevantMonsters, monstersForFirstHero);

        // And the second Hero should be paired with the first Monster
        List<Monster> monstersForSecondHero = pairing.getMonstersForHero(relevantHeroes.get(1));
        assertEquals(1, monstersForSecondHero.size());
        assertEquals(relevantMonsters.get(0), monstersForSecondHero.get(0));
    }

    @Test
    public void checkHeroes() {
        // Setup a fight between three heroes and three Monsters
        // Then, "kill" one of the Monsters, and run the check method on each of the Heroes.
        String input = "1";
        Scanner scanner = new Scanner(input);

        // Select 3 heroes, and 3 monsters
        List<Hero> relevantHeroes = heroes.subList(0, 3);
        List<Monster> relevantMonsters = monsters.subList(0, 3);

        PairHeroesAndMonsters pairing = new PairHeroesAndMonsters(scanner, relevantHeroes, relevantMonsters);
        pairing.initialPairing();

        // Now, "kill" one of the Monsters.
        relevantMonsters.get(0).getHealthPower().setHealthPower(0);

        // Check the first Hero: the first Monster should not be there, and the other two should be
        pairing.checkValidityForHero(relevantHeroes.get(0));
        List<Monster> monstersForFirstHero = pairing.getMonstersForHero(relevantHeroes.get(0));
        assertEquals(2, monstersForFirstHero.size());
        assertFalse(monstersForFirstHero.contains(relevantMonsters.get(0)));
        assertEquals(relevantMonsters.subList(1, 3), monstersForFirstHero);

        // Check the other two heroes, nothing should have changed
        for(int i = 1; i < 3; i++) {
            Hero hero = relevantHeroes.get(i);
            pairing.checkValidityForHero(hero);
            List<Monster> monstersForHero = pairing.getMonstersForHero(hero);
            assertEquals(1, monstersForHero.size());
            assertEquals(relevantMonsters.get(i), monstersForHero.get(0));
        }
    }
}
