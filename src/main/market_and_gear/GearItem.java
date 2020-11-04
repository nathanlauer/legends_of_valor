package main.market_and_gear;

import main.attributes.Level;
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
public abstract class GearItem implements Cloneable {
    public static final int mostExpensiveGearItem = 1400; // TSwords are 1400
    private String name;
    private double price;
    private Level minLevel;

    /**
     * Standard constructor for a GearItem. Must be called from a subclass.
     * Throws an IllegalArgumentException if price is negative.
     * @param name name of this GearItem
     * @param price price of this GearItem.
     * @param minLevel min Level for this GearItem.
     */
    public GearItem(String name, double price, Level minLevel) {
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
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price for this GearItem.
     * Throws an IllegalArgumentException if newPrice is negative.
     * @param newPrice the new price for this GearItem
     */
    public void setPrice(double newPrice) {
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
                Double.compare(this.getPrice(), other.getPrice()) == 0 &&
                this.getMinLevel().equals(other.getMinLevel());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (GearItem)super.clone();
    }
}
