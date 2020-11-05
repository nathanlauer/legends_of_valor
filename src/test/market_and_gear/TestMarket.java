package test.market_and_gear;

import main.market_and_gear.GearItem;
import main.market_and_gear.Market;
import main.utils.Coffer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class TestMarket
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/4/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestMarket {
    @Test
    public void availableItems() {
        // Be default, this should create a Market with a random assortment
        // of GearItems. There will be between 1 and 3 of each of weapons,
        // armor, potions, and spells.
        Market market = new Market();
        List<GearItem> gearItems = market.getGearItems();
        assertTrue(gearItems.size() >= 4); // at least one of each type
        assertTrue(gearItems.size() <= 12); // maximally three of each type
        boolean foundWeapon = false;
        boolean foundArmor = false;
        boolean foundPotion = false;
        boolean foundSpell = false;
        for(GearItem item : gearItems) {
            switch (item.getType()) {
                case WEAPON:
                    foundWeapon = true;
                    break;
                case POTION:
                    foundPotion = true;
                    break;
                case ARMOR:
                    foundArmor = true;
                    break;
                case SPELL:
                    foundSpell = true;
                    break;
                default:
                    fail();
            }
        }
        assertTrue(foundWeapon);
        assertTrue(foundPotion);
        assertTrue(foundSpell);
        assertTrue(foundArmor);
    }

    @Test
    public void coffer() {
        Market market = new Market();
        Coffer coffer = market.getCoffer();

        // Coffers for Market should start with five times the most expensive GearItem
        int expectedAmount = GearItem.mostExpensiveGearItem * Market.startingCofferMultiplier;
        assertTrue(coffer.hasEnoughCoins(expectedAmount));
    }
}
