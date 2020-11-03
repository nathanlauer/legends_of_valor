package main.market_and_gear;

import main.attributes.Level;
import main.utils.Validations;

/**
 * Class Armor is a concrete type of GearItem, which protects its wearer. That is,
 * it has a defense value.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Armor extends GearItem {
    public static final String defaultName = "Armor";
    private double defense;

    /**
     * Empty constructor for an Armor. Sets name to "Armor" and
     * all other attributes to zero.
     */
    public Armor() {
        this(Armor.defaultName, 0.0, new Level(0), 0.0);
    }

    /**
     * Standard constructor for an Armor.
     * Throws an IllegalArgumentException if price or defense are negative.
     * @param name name of this GearItem
     * @param price price of this GearItem
     * @param minLevel min Level for this GearItem
     * @param defense defense value of this Armor.
     */
    public Armor(String name, double price, Level minLevel, double defense) {
        super(name, price, minLevel);

        Validations.nonNegative(defense, "defense");
        this.defense = defense;
    }

    /**
     *
     * @return the defense value for this Armor
     */
    public double getDefense() {
        return defense;
    }

    /**
     * Sets the defense value for this Armor to the passed in value.
     * @param newDefense the new value for the defense of this Armor.
     */
    public void setDefense(double newDefense) {
        Validations.nonNegative(newDefense, "newDefense");
        defense = newDefense;
    }

    /**
     * @return String representation of this Armor object.
     */
    @Override
    public String toString() {
        return super.toString() + ". Armor! defense: " + this.getDefense();
    }

    /**
     * Defines equality for two Armor objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Armor, and TODO
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Armor)) {
            return false;
        }

        Armor other = (Armor) o;
        return Double.compare(this.getDefense(), other.getDefense()) == 0 &&
                super.equals(o);
    }
}
