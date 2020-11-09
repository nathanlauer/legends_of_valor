package main.market_and_gear;

import main.attributes.Level;
import main.utils.Validations;

/**
 * Class Weapon is a concrete type of GearItem. In addition to the name, price, and
 * minimum level, it also has a certain amount of damage that it can deal, and a
 * number of hands that are required for use.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Weapon extends GearItem {
    public static final String defaultName = "Weapon";
    private double damage;
    private int numHands;

    /**
     * Empty constructor for a Weapon. Sets name to Weapon,
     * and all other attributes to zero.
     */
    public Weapon() {
        this(Weapon.defaultName, 0, new Level(0), 0, 0);
    }

    /**
     * Standard constructor for a Weapon.
     * Throws an IllegalArgumentException if price, damage, or numHands are negative.
     * @param name name of this GearItem
     * @param price price of this GearItem
     * @param minLevel min Level for this Gear Item
     * @param damage damage this Weapon causes
     * @param numHands required hands to hold this Weapon
     */
    public Weapon(String name, int price, Level minLevel, double damage, int numHands) {
        super(name, price, minLevel);

        // Validate input
        Validations.nonNegative(damage, "damage");
        Validations.nonNegative(numHands, "numHands");

        // Weapon specific attributes
        this.damage = damage;
        this.numHands = numHands;
    }

    /**
     *
     * @return the amount of damage this Weapon causes
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Sets the amount of damage this Weapon causes
     * Throws an IllegalArgumentException if newDamage is negative
     * @param newDamage the amount of damage this Weapon will now cause
     */
    public void setDamage(double newDamage) {
        Validations.nonNegative(newDamage, "newDamage");
        damage = newDamage;
    }

    /**
     *
     * @return the number of hands required to hold this Weapon
     */
    public int getNumHands() {
        return numHands;
    }

    /**
     * Sets the required number of hands to hold this Weapon.
     * Throws an IllegalArgumentException if newNumHands is negative
     * @param newNumHands the number of hands now required to hold this Weapon.
     */
    public void setNumHands(int newNumHands) {
        Validations.nonNegative(newNumHands, "newNumHands");
        numHands = newNumHands;
    }

    /**
     * @return the Type of this Gear Item
     */
    @Override
    public GearItemType getType() {
        return GearItemType.WEAPON;
    }

    /**
     * @return String representation of this Weapon object.
     */
    @Override
    public String toString() {
        return super.toString() + ". Weapon! damage: " + this.getDamage() + ", numHands: " + this.getNumHands();
    }

    /**
     * Defines equality for two Weapon objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Weapon, they have the same damage, same number of hands,
     * and are the same GearItem.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Weapon)) {
            return false;
        }

        Weapon other = (Weapon) o;
        return this.getDamage() == other.getDamage() &&
            this.getNumHands() == other.getNumHands() &&
            super.equals(o);
    }
}
