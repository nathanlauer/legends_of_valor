package test.market_and_gear;

import main.market_and_gear.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class TestMarketInventory
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/4/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestMarketInventory {
    private long initialReadTime;

    @BeforeAll
    public void init() {
        // This should read in all data from disk
        long startTime = System.nanoTime();
        MarketInventory.getInstance();
        long endTime = System.nanoTime();
        initialReadTime = (endTime - startTime);
    }

    @Test
    public void allGearItems() {
        long startTime = System.nanoTime();
        MarketInventory instance = MarketInventory.getInstance(); // at least second time calling getInstance
        List<GearItem> items = instance.getAllGearItems();
        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime);
        assert(executionTime < initialReadTime); // ensure that we only read from disk once.

        // There are 6 potions, 6 weapons, 6 armors, 5 fire spells, 4 ice spells,
        // and 4 lightning spells. That is a total of 31 GearItems
        assertEquals(31, items.size());
    }

    @Test
    public void allWeapons() {
        MarketInventory instance = MarketInventory.getInstance(); // at least second time calling getInstance
        List<GearItem> weapons = instance.getAllWeapons();
        assertEquals(6, weapons.size());
        for(GearItem weapon : weapons) {
            assert(weapon instanceof Weapon);
        }
    }

    @Test
    public void allArmor() {
        MarketInventory instance = MarketInventory.getInstance(); // at least second time calling getInstance
        List<GearItem> armors = instance.getAllArmor();
        assertEquals(6, armors.size());
        for(GearItem armor : armors) {
            assert(armor instanceof Armor);
        }
    }

    @Test
    public void allPotions() {
        MarketInventory instance = MarketInventory.getInstance(); // at least second time calling getInstance
        List<GearItem> potions = instance.getAllPotions();
        assertEquals(6, potions.size());
        for(GearItem potion : potions) {
            assert(potion instanceof Potion);
        }
    }

    @Test
    public void allSpells() {
        MarketInventory instance = MarketInventory.getInstance(); // at least second time calling getInstance
        List<GearItem> spells = instance.getAllSpells();
        assertEquals(13, spells.size());
        for(GearItem spell : spells) {
            assert(spell instanceof Spell);
        }
    }
}
