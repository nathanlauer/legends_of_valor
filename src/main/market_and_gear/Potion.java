package main.market_and_gear;

import main.attributes.Ability;
import main.attributes.Level;
import main.utils.Validations;

/**
 * Class Potion is a concrete type of GearItem. In addition to a name, price, and
 * minimum level, it also can increase an Ability by a certain amount, and can only
 * be used once.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Potion extends GearItem {
    public static final String defaultName = "Potion";
    private Ability ability;
    private double incrementAmount;
    private boolean used;

    /**
     * Empty constructor. Sets the name to Potion, and everything else
     * to zero values.
     */
    public Potion() {
        this(Potion.defaultName, 0.0, new Level(0), new Ability(0.0), 0.0);
    }

    /**
     * Standard constructor for a Potion.
     * Throws an IllegalArgumentException if incrementAmount is negative.
     *
     * By default, a Potion has not been used.
     *
     * @param name name of this GearItem
     * @param price price of this GearItem
     * @param minLevel min Level of this GearItem
     * @param ability Ability of this Potion
     * @param incrementAmount amount to increment ability of this Potion
     */
    public Potion(String name, double price, Level minLevel, Ability ability, double incrementAmount) {
        super(name, price, minLevel);

        Validations.nonNegative(incrementAmount, "incrementAmount");
        this.ability = ability;
        this.incrementAmount = incrementAmount;
        this.used = false;
    }

    /**
     *
     * @return the Ability associated with this Potion
     */
    public Ability getAbility() {
        return ability;
    }

    /**
     * Sets the ability of this Potion to the passed in Ability.
     * @param ability new Ability for this Potion
     */
    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    /**
     *
     * @return the amount to increment the associated Ability by
     */
    public double getIncrementAmount() {
        return incrementAmount;
    }

    /**
     * Sets the increment amount for this Potion
     * Throws an IllegalArgumentException if newAmount is negative
     * @param newAmount the new amount that this Potion will increase its Ability.
     */
    public void setIncrementAmount(double newAmount) {
        Validations.nonNegative(newAmount, "newAmount");
        incrementAmount = newAmount;
    }

    /**
     * Indicates whether or not this Potion was used.
     * @return true if this Potion was used, false otherwise
     */
    public boolean wasUsed() {
        return used;
    }

    /**
     * Sets whether or not this Potion was used.
     * @param newUsed whether or not this Potion was used
     */
    public void setUsed(boolean newUsed) {
        used = newUsed;
    }

    /**
     * @return String representation of this Potion object.
     */
    @Override
    public String toString() {
        return super.toString() + ". Potion! Ability: " + this.getAbility() + ", increment amount: " + this.getIncrementAmount() + ", used: " + this.wasUsed();
    }

    /**
     * Defines equality for two Potion objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Potion, they share the same Potion attributes, and the
     * same GearItem attributes.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Potion)) {
            return false;
        }

        Potion other = (Potion) o;
        return this.getAbility().equals(other.getAbility()) &&
                Double.compare(this.getIncrementAmount(), other.getIncrementAmount()) == 0 &&
                this.wasUsed() == other.wasUsed() &&
                super.equals(o);
    }
}
