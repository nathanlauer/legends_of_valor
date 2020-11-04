package main.market_and_gear;

import java.util.ArrayList;
import java.util.List;

/**
 * Class MarketInventory is a Singleton class that contains every possible
 * GearItem that can be bought or sold in the game.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/3/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class MarketInventory {
    public static final int maxSubsetSize = 80;
    public static final int minSubsetSize = 30;
    private static MarketInventory instance = null;
    private final List<GearItem> gearItems;

    /**
     *
     * @return Singleton instance of the MarketInventory class.
     */
    public static MarketInventory getInstance() {
        if(instance == null) {
            instance = new MarketInventory();
        }
        return instance;
    }

    /**
     * Private constructor. Initializes the MarketInventory with
     * all GearItems.
     */
    private MarketInventory() {
        gearItems = new ArrayList<>();
        // TODO: read all GearItems from file
        // TODO: assert required files can be found
    }

    /**
     *
     * @return a List of every GearItem contained in the game.
     */
    public List<GearItem> getAllGearItems() {
        // TODO:
        return new ArrayList<>();
    }

    /**
     *
     * @return all GearItems that are Weapons
     */
    public List<GearItem> getAllWeapons() {
        // TODO:
        return new ArrayList<>();
    }

    /**
     *
     * @return all GearItems that are Armor
     */
    public List<GearItem> getAllArmor() {
        // TODO:
        return new ArrayList<>();
    }

    /**
     *
     * @return all GearItems that are Potions
     */
    public List<GearItem> getAllPotions() {
        // TODO:
        return new ArrayList<>();
    }

    /**
     *
     * @return all GearItems that are Spells
     */
    public List<GearItem> getAllSpells() {
        // TODO:
        return new ArrayList<>();
    }
}
