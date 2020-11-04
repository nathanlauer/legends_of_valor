package main.market_and_gear;

import main.attributes.Level;
import main.legends.ActiveGearItems;
import main.legends.Hero;
import main.legends.NonOwnedGearItemException;
import main.utils.BeneathLevelException;
import main.utils.NotEnoughCoinsException;
import main.utils.Validations;

/**
 * Class GearItem is an abstract class that sits at the top of the Gear hierarchy.
 * It represents an item that can be used in the game.
 *
 * GearItems are both Sellable and Buyable: they can be sold from a Legend
 * to the Market, and they can be bought from the Market to a Hero, respectively.
 *
 * Every GearItem has a name, a price, and a minimum level. Legends with levels that
 * are less than an item's min level cannot be used or bought by a Legend.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class GearItem implements Cloneable, Buyable, Sellable {
    public static final int mostExpensiveGearItem = 1400; // TSwords are 1400
    private String name;
    private int price;
    private Level minLevel;

    /**
     * Standard constructor for a GearItem. Must be called from a subclass.
     * Throws an IllegalArgumentException if price is negative.
     * @param name name of this GearItem
     * @param price price of this GearItem.
     * @param minLevel min Level for this GearItem.
     */
    public GearItem(String name, int price, Level minLevel) {
        Validations.nonNegative(price, "price");
        this.name = name;
        this.price = price;
        this.minLevel = minLevel;
    }

    /**
     *
     * @return the name of this GearItem
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this GearItem to the passed in String
     * @param newName the new name for this GearItem
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     *
     * @return the price of this GearItem
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price for this GearItem.
     * Throws an IllegalArgumentException if newPrice is negative.
     * @param newPrice the new price for this GearItem
     */
    public void setPrice(int newPrice) {
        Validations.nonNegative(newPrice, "newPrice");
        price = newPrice;
    }

    /**
     *
     * @return the minimum level for this GearItem
     */
    public Level getMinLevel() {
        return minLevel;
    }

    /**
     * Sets the minimum level for this GearItem.
     * @param newLevel the minimum level for this GearItem.
     */
    public void setMinLevel(Level newLevel) {
        minLevel = newLevel;
    }

    /**
     *
     * @return the Type of this Gear Item
     */
    public abstract GearItemType getType();

    /**
     * @return String representation of this GearItem object.
     */
    @Override
    public String toString() {
        return "Gear Item. Name: " + name + ", price: " + price + ", minLevel: " + minLevel.toString();
    }

    /**
     * Defines equality for two GearItem objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of GearItem, and they have the same name, price, and minimum level
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof GearItem)) {
            return false;
        }

        GearItem other = (GearItem) o;
        return this.getName().equals(other.getName()) &&
                this.getPrice() == other.getPrice() &&
                this.getMinLevel().equals(other.getMinLevel());
    }

    /**
     * Clones a GearItem object, that is, returns a deep copy of this GearItem.
     * @return deep copy of this GearItem
     * @throws CloneNotSupportedException if this Object can't be cloned
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        GearItem cloned = (GearItem)super.clone();
        Level copiedLevel = (Level)this.getMinLevel().clone();
        cloned.setMinLevel(copiedLevel);

        return cloned;
    }

    /**
     * The passed in Hero buys this GearItem from the passed in Market.
     * @param market Market that owns this GearItem
     * @param hero Hero buying this GearItem
     * @throws NonOwnedGearItemException if the Market does not own this GearItem
     * @throws NotEnoughCoinsException if the Hero does not have enough coins to purchase this GearItem
     * @throws BeneathLevelException if the Hero's Level is not advanced enough for this GearItem
     */
    @Override
    public void buy(Market market, Hero hero) throws NonOwnedGearItemException, NotEnoughCoinsException, BeneathLevelException {
        if(!market.hasGearItem(this)) {
            throw new NonOwnedGearItemException("The market does not own this GearItem!");
        }

        if(!hero.getCoffer().hasEnoughCoins(this.price)) {
            throw new NotEnoughCoinsException("Hero does not have enough coins to purchase this GearItem!");
        }

        if(hero.getLevel().isLessThan(this.getMinLevel())) {
            throw new BeneathLevelException("Hero is not advanced enough to purchase this GearItem!");
        }

        // If we've made it past all the previous hurdles, then we can actually purchase this GearItem
        // There are a few steps in performing this:
        // 1) Extract the coins from the Hero, and transfer to the Market
        hero.getCoffer().removeCoins(this.price);
        market.getCoffer().addCoins(this.price);

        // 2) Remove the GearItem from the Market, and transfer it to the Hero
        market.getGearItems().remove(this);
        hero.getGearItemList().addGearItem(this);

        // 3) Convenience step: auto add this GearItem to the active list within certain bounds:
        ActiveGearItems activeGearItems = hero.getActiveGearItems();
        switch (this.getType()) {
            case WEAPON:
                if(!activeGearItems.hasActiveWeapon()) {
                    activeGearItems.activateWeapon((Weapon)this);
                }
                break;
            case ARMOR:
                if(!activeGearItems.hasActiveArmor()) {
                    activeGearItems.putOnArmor((Armor)this);
                }
                break;
            case POTION:
                activeGearItems.addPotion((Potion)this);
                break;
            case SPELL:
                activeGearItems.addSpell((Spell)this);
                break;
            default:
                throw new UnknownGearItemTypeException("Unknown GearItem type");
        }
    }

    /**
     * Passed in Hero sells this GearItem back to the Market, for half the original price.
     * @param market Market to sell this GearItem to
     * @param hero Hero who is selling this GearItem
     * @throws NonOwnedGearItemException if the Hero is selling a GearItem they don't actually own
     */
    @Override
    public void sell(Market market, Hero hero) throws NonOwnedGearItemException {
        if(!hero.getGearItemList().containsGearItem(this)) {
            throw new NonOwnedGearItemException("Hero does not own this GearItem to sell!");
        }

        // The actual selling requires a few steps:
        // 1) Transfer the coins from the Market to the Hero
        int sellPrice = (int)Math.ceil(this.getPrice() / 2.0); // round up, in the Hero's favor
        market.getCoffer().removeCoins(sellPrice);
        hero.getCoffer().addCoins(sellPrice);

        // 2) remove this GearItem from the Hero's inventory
        hero.getGearItemList().removeGearItem(this);

        // 3) remove this GearItem from the Hero's active list
        hero.getActiveGearItems().removeGearItem(this);
    }
}
