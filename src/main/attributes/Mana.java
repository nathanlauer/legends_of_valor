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
    private double originalAmount;

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
        super(AbilityType.MANA, mana);
        Validations.nonNegative(mana, "mana");
        originalAmount = mana;
    }

    /**
     *
     * @return the amount of Mana
     */
    public double getManaAmount() {
        return this.getAbilityValue();
    }

    /**
     *
     * @return the original amount of Mana
     */
    public double getOriginalAmount() {
        return this.originalAmount;
    }

    /**
     * Sets the original amount of Mana to the passed in value
     * @param originalAmount new original amount of Mana
     */
    public void setOriginalAmount(double originalAmount) {
        this.originalAmount = originalAmount;
    }

    /**
     * Sets the mana amount to the passed in value
     * Throws an IllegalArgumentException is amount is negative
     * @param amount new amount of Mana
     */
    public void setMana(double amount) {
        Validations.nonNegative(amount, "amount");
        this.setAbilityValue(amount);
    }

    /**
     * Checks to see if this Mana has enough, as relative to the amount passed in
     * @param amount the amount in question
     * @return true if Mana >= amount, false otherwise
     */
    public boolean hasEnoughMana(double amount) {
        Validations.nonNegative(amount, "amount");
        return this.getManaAmount() >= amount;
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
     * Increments the Mana by a the passed in percentage.
     * For example, a client may choose to increase the Mana by 10%
     * (which is different than increaseManaBy, which increase by a
     * fixed amount)
     *
     * Throws an IllegalArgumentException if percentage is negative.
     * @param percentage the percentage to increase, on a scale of 0-100
     */
    public void increaseByPercentage(double percentage) {
        Validations.nonNegative(percentage, "percentage");
        double amountToIncrease = percentage/100.0 * this.getManaAmount();
        this.increaseManaBy(amountToIncrease);
    }

    /**
     * Increases the Mana by a percentage of the original amount.
     * For example: gains 10% of their original Mana.
     *
     * Throws an IllegalArgumentException if percentage is negative.s
     * @param percentage the percentage to increase, on a scale of 0-100
     */
    public void increaseByPercentageOfOriginal(double percentage) {
        Validations.nonNegative(percentage, "percentage");
        double amountToIncrease = percentage / 100.0 * this.getOriginalAmount();
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
    public void decreaseManaBy(double amount) {
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
