package main.attributes;

import main.utils.Validations;

/**
 * Class Mana represents a concept similar to HealthPower, but for magic. It is essentially
 * how much magical ability a Legend has accessible.
 *
 * At the moment, it is essentially a wrapper around a double value. However, similar
 * to some other classes in this game, mana cannot be negative.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Mana {
    private double mana;

    /**
     * Empty constructor, sets mana to zero.
     */
    public Mana() {
        this(0);
    }

    /**
     * Standard constructor, sets mana to the passed in value
     * Throws an IllegalArgumentException if mana is negative
     * @param mana amount of mana as initial value.
     */
    public Mana(double mana) {
        Validations.nonNegative(mana, "mana");
        this.mana = mana;
    }

    /**
     *
     * @return the amount of Mana
     */
    public double getManaAmount() {
        return mana;
    }

    /**
     * Sets the mana amount to the passed in value
     * Throws an IllegalArgumentException is amount is negative
     * @param amount new amount of Mana
     */
    public void setMana(double amount) {
        Validations.nonNegative(amount, "amount");
        mana = amount;
    }

    /**
     * Increases the amount of Mana by the passed in value
     * Throws an IllegalArgumentException if amount is negative
     * @param amount amount to increase
     */
    public void increaseManaBy(double amount) {
        Validations.nonNegative(amount, "amount");
        this.setMana(this.getManaAmount() + amount);
    }

    /**
     * Decreases the amount of Mana by the passed in value.
     * If amount is greater than the current mana, then the mana
     * is set to zero (e.g. mana can't be negative).
     *
     * Throws an IllegalArgumentException if amount is negative
     * @param amount amount to decrease
     */
    public void decreaseManaBy(double amount) {
        Validations.nonNegative(amount, "amount");
        if(amount > mana) {
            this.setMana(0);
        } else {
            this.setMana(this.getManaAmount() - amount);
        }
    }

    /**
     * @return String representation of this Mana object.
     */
    @Override
    public String toString() {
        return "Mana, amount: " + this.getManaAmount();
    }

    /**
     * Defines equality for two Mana objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Mana, and they have the same amounts
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Mana)) {
            return false;
        }

        Mana other = (Mana) o;
        return Double.compare(this.getManaAmount(), other.getManaAmount()) == 0;
    }
}