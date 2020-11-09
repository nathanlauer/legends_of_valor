package main.market_and_gear;

import main.legends.GearItemList;
import main.utils.Coffer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class Market is a class that represents a place in the game where Heroes
 * can buy and sell GearItems. Within a Market, there are four general
 * types of GearItems that can be purchased or sold: Weapons, Armor,
 * Potions, and Spells. Every GearItem has an associated price, and when
 * purchased, that amount of coins are extracted from the relevant Hero's
 * Coffer.
 *
 * Every Market has a subset of the total GearItems available. In other words,
 * there exists a MarketInventory which contains all possible GearItems. Then,
 * an individual Market will contain a random percentage of those items. That
 * percentage is a random value between 30% and 80% (Note: I've chosen this
 * arbitrarily, just to simulate some variation in available items between Markets).
 *
 * Buying GearItems: When an GearItem is bought from the Market, coins are transferred
 * from a Hero to the Market, and the Market transfers the GearItem to the Hero. That
 * GearItem will no longer be available for purchase.
 *
 * Selling GearItems: Heroes can choose to sell GearItems they currently own
 * back to the Market. When doing so, every Market will pay 50% of the GearItem's
 * original price. Note: as coins can't be split in two in this game, if a
 * GearItem was originally priced with an odd number, then Market will buy it
 * back at the ceiling of price/2. Once a GearItem has been sold back to the
 * Market, it becomes available again for purchase -- even to the Hero that
 * sold said item.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/3/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Market {
    public static final int startingCofferMultiplier = 5;
    private List<GearItem> gearItems;
    private final Coffer coffer;

    /**
     * The only constructor available for a Market. We structure this class in
     * this fashion to control which GearItems are initially available to the
     * Market (see notes above). Additionally, we also want to guarantee that
     * the Market has a Coffer with enough money to allow Heroes to sell their
     * GearItems back to a Market.
     */
    public Market() {
        GearItemFactoryCreator factory = new RandomGearItemFactory();
        gearItems = factory.createGearItems();
        int numCoins = GearItem.mostExpensiveGearItem * Market.startingCofferMultiplier; // Markets should have plenty of coins for this Game.
        coffer = new Coffer(numCoins);
    }

    /**
     * Regenerates the list of available GearItems in this Market, according
     * to the semantics of the passed in factory.
     * @param factory GearItemFactoryCreator, responsible for creating the relevant GearItems.
     */
    public void regenerateGearItems(GearItemFactoryCreator factory) {
        gearItems = factory.createGearItems();
    }

    /**
     *
     * @return a List of the available GearItems in this Market.
     */
    public List<GearItem> getGearItems() {
        return gearItems;
    }

    /**
     *
     * @return the list of GearItems in this market wrapped in a GearItemList object.
     */
    public GearItemList getGearItemList() {
        return new GearItemList(getGearItems());
    }

    /**
     * Adds the passed in gearItem to this Market.
     * @param gearItem the GearItem to be added
     */
    public void addGearItem(GearItem gearItem) {
        gearItems.add(gearItem);
    }

    /**
     *
     * @return a List of all Weapons in this Market
     */
    public List<GearItem> getWeapons() {
        Stream<GearItem> weapons = getGearItems().stream().filter(gearItem -> gearItem instanceof Weapon);
        return weapons.collect(Collectors.toList());
    }

    /**
     *
     * @return List of all Armor in this Market
     */
    public List<GearItem> getArmor() {
        Stream<GearItem> armor = getGearItems().stream().filter(gearItem -> gearItem instanceof Armor);
        return armor.collect(Collectors.toList());
    }

    /**
     *
     * @return List of all Potions in this Market
     */
    public List<GearItem> getPotions() {
        Stream<GearItem> potions = getGearItems().stream().filter(gearItem -> gearItem instanceof Potion);
        return potions.collect(Collectors.toList());
    }

    /**
     *
     * @return List of all Spells in this Market
     */
    public List<GearItem> getSpells() {
        Stream<GearItem> spells = getGearItems().stream().filter(gearItem -> gearItem instanceof Spell);
        return spells.collect(Collectors.toList());
    }

    /**
     *
     * @return the Coffer containing the coins this Market owns.
     */
    public Coffer getCoffer() {
        return coffer;
    }

    /**
     * Indicates whether or not this Market has the passed in GearItem
     * @param gearItem the GearItem in question
     * @return true if this Market has the GearItem, false otherwise
     */
    public boolean hasGearItem(GearItem gearItem) {
        return this.getGearItems().contains(gearItem);
    }

    /**
     * @return String representation of this Market object.
     */
    @Override
    public String toString() {
        return "Market with GearItems: " + getGearItems();
    }

    /**
     * Defines equality for two Market objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Market, they have the same GearItems and the same coffer
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Market)) {
            return false;
        }

        Market other = (Market) o;
        return this.getGearItems().equals(other.getGearItems()) && this.getCoffer().equals(other.getCoffer());
    }
}
