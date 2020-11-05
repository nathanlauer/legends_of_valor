package main.market_and_gear;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class RandomGearItemFactory is a Factory class which produces GearItems.
 * As its name suggests, it produces a random number of GearItems.
 *
 * When called, it internally chooses a random number of Weapons, Armor,
 * Potions, and Spells.
 *
 * In the context of this game, this class is not quite a true factory, in
 * that it is not creating these GearItems from scratch - rather, it obtains
 * a list of the available GearItems from the MarketInventory, selects
 * a random subset, and clones of the items in the subset. That cloned
 * subset is returned to the client.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/3/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class RandomGearItemFactory implements GearItemFactoryCreator {
    private final MarketInventory inventory;
    private final Random random;

    /**
     * Constructs a RandomGearItemFactory, and initializes its private state.
     */
    public RandomGearItemFactory() {
        inventory = MarketInventory.getInstance();
        random = new Random();
    }

    /**
     * Factory method which creates a random List of GearItems.
     * The returned List will include some Weapons, Armor, Potions, and Spells.
     * @return Random List of GearItems.
     */
    @Override
    public List<GearItem> createGearItems() {
        // Obtain the available options from the MarketInventory
        List<GearItem> weapons = gearItemSelector(GearItemType.WEAPON);
        List<GearItem> armor = gearItemSelector(GearItemType.ARMOR);
        List<GearItem> potions = gearItemSelector(GearItemType.POTION);
        List<GearItem> spells = gearItemSelector(GearItemType.SPELL);

        List<GearItem> allSelectedItems = new ArrayList<>(weapons);
        allSelectedItems.addAll(armor);
        allSelectedItems.addAll(potions);
        allSelectedItems.addAll(spells);

        // Clone each of the selected items, so that the client has their own copy
        List<GearItem> output = new ArrayList<>();
        for(GearItem item : allSelectedItems) {
            try {
                output.add((GearItem)item.clone());
            } catch (CloneNotSupportedException e) {
                // Shouldn't happen
                e.printStackTrace();
            }
        }

        return output;
    }

    /**
     * Helper function which returns a random number of a certain type of GearItem.
     * @param type GearItem type.
     * @return List of random GearItems of the indicated types.
     */
    private List<GearItem> gearItemSelector(GearItemType type) {
        List<GearItem> output;
        switch (type) {
            case WEAPON:
                output = inventory.getAllWeapons();
                break;
            case ARMOR:
                output = inventory.getAllArmor();
                break;
            case POTION:
                output = inventory.getAllPotions();
                break;
            case SPELL:
                output = inventory.getAllSpells();
                break;
            default:
                throw new IllegalArgumentException("Unknown GearItem type!");
        }

        int numSelected = random.nextInt(3) + 1; // choose between 1 and 3 GearItems
        if (numSelected >= output.size()) {
            return output;
        } else {
            Collections.shuffle(output);
            return output.subList(0, numSelected);
        }
    }
}
