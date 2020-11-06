package main.attributes;

import main.utils.Validations;

/**
 * Class Mana represents a concept similar to HealthPower, but for magic. It is essentially
 * how much magical ability a Legend has accessible.
 *
 * At the moment, it is essentially a wrapper around a int value. However, similar
 * to some other classes in this game, mana cannot be negative.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Mana extends Ability implements Cloneable {

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
    public Mana(int mana) {
        super(AbilityType.MANA, mana);
        Validations.nonNegative(mana, "mana");
    }

    /**
     *
     * @return the amount of Mana
     */
    public int getManaAmount() {
        return this.getAbilityValue();
    }

    /**
     * Sets the mana amount to the passed in value
     * Throws an IllegalArgumentException is amount is negative
     * @param amount new amount of Mana
     */
    public void setMana(int amount) {
        Validations.nonNegative(amount, "amount");
        this.setAbilityValue(amount);
    }

    /**
     * Increases the amount of Mana by the passed in value
     * Throws an IllegalArgumentException if amount is negative
     * @param amount amount to increase
     */
    public void increaseManaBy(int amount) {
        Validations.nonNegative(amount, "amount");
        this.setMana(this.getManaAmount() + amount);
    }

    /**
     * Increments the Mana by a the passed in percentage.
     * For example, a client may choose to increase the Mana by 10%
     * (which is different than increaseManaBy, which increase by a
     * fixed amount)
     *
     * Throws an IllegalArgumentException if percentage is negative.
     * @param percentage the percentage to increase, on a scale of 0-100
     */
    public void increaseByPercentage(int percentage) {
        Validations.nonNegative(percentage, "percentage");
        int amountToIncrease = (int)Math.ceil(percentage/100.0 * this.getManaAmount());
        this.increaseManaBy(amountToIncrease);
    }

    /**
     * Decreases the amount of Mana by the passed in value.
     * If amount is greater than the current mana, then the mana
     * is set to zero (e.g. mana can't be negative).
     *
     * Throws an IllegalArgumentException if amount is negative
     * @param amount amount to decrease
     */
    public void decreaseManaBy(int amount) {
        Validations.nonNegative(amount, "amount");
        if(amount > this.getManaAmount()) {
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
        return this.getManaAmount() == other.getManaAmount();
    }

    /**
     *
     * @return a cloned copy of this Mana
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
