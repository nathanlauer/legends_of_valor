package main.attributes;

import main.utils.Validations;

/**
 * Class CappedHealthPower is a concrete type of HealthPower, where there is
 * a maximum value for healthPower. That is, the healthPower is capped.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class CappedHealthPower extends HealthPower {
    private double max;
    public static final double defaultMax = 100.0;

    /**
     * Empty constructor for CappedHealthPower, which initialized
     * the max and health power to the default value (currently 100).
     */
    public CappedHealthPower() {
        this(CappedHealthPower.defaultMax);
    }

    /**
     * Constructor for CappedHealthPower as indicated by max.
     * By default, the initial value for healthPower is initialized
     * to max as well.
     * @param max maximum value for healthPower.
     */
    public CappedHealthPower(double max) {
        this(max, max); // sets max to max, and initial healthPower to max.
    }

    /**
     * Standard Constructor for CappedHealthPower, where the healthPower and
     * max are set. There are a couple rules here:
     * 1) throws an IllegalArgumentException if healthPower is negative
     * 2) throws an IllegalArgumentException if max is negative
     * 3) throws an IllegalArgumentException is healthPower is greater than max
     *
     * @param healthPower initial value for healthPower
     * @param max max value for healthPower
     */
    public CappedHealthPower(double healthPower, double max) {
        super(healthPower);
        Validations.nonNegative(healthPower, "healthPower");
        Validations.nonNegative(max, "max");

        if(healthPower > max) {
            throw new IllegalArgumentException("healthPower can't be greater than the max health power!");
        }

        this.max = max;
    }

    /**
     * Sets the healthPower to the passed in value. For CappedHealthPower,
     * this means that if the newHealthPower is greater than the max,
     * we simply set the new HealthPower to the max value.
     *
     *
     * Note that healthPower can't be less than zero. Thus, if the
     * passed in healthPower is less than 0, then this method will
     * throw an IllegalArgumentException
     * @param newHealthPower new healthPower
     */
    @Override
    public void setHealthPower(double newHealthPower) {
        Validations.nonNegative(newHealthPower, "newHealthPower");

        double healthPower = newHealthPower;
        if(healthPower > this.getMaxHealthValue()) {
            healthPower = this.getMaxHealthValue();
        }
        super.setHealthPower(healthPower);
    }

    /**
     * Increases this Legend's health power by the indicated amount.
     * For CappedHealthPower, if amount would exceed the max, then
     * we simply set the healthPower to the max value.
     * Throws an IllegalArgumentException if amount is negative
     *
     * @param amount the amount to increase healthPower.
     */
    @Override
    public void increaseHealthPowerBy(double amount) {
        Validations.nonNegative(amount, "amount");

        double healthPower = this.getHealthPower() + amount;
        if(healthPower > this.getMaxHealthValue()) {
            healthPower = this.getMaxHealthValue();
        }

        this.setHealthPower(healthPower);
    }

    /**
     * Sets the maximum value that healthPower can have
     * Throws an illegal argument exception if max is negative.
     * @param newMax the new maximum value.
     */
    public void setMaxHealthValue(double newMax) {
        Validations.nonNegative(newMax, "newMax");

        max = newMax;
        if(max < this.getHealthPower()) { // reset current health power if necessary
            this.setHealthPower(max);
        }
    }

    /**
     *
     * @return the maximum healthValue for this HealthValue
     */
    public double getMaxHealthValue() {
        return max;
    }

    /**
     * @return String representation of this CappedHealthPower object.
     */
    @Override
    public String toString() {
        return "Capped Health Power: " + this.getHealthPower();
    }

    /**
     * Defines equality for two CappedHealthPower objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of CappedHealthPower, they have the same max, and the same health power
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof CappedHealthPower)) {
            return false;
        }

        CappedHealthPower other = (CappedHealthPower) o;
        return this.getMaxHealthValue() == other.getMaxHealthValue() &&
                super.equals(o);
    }
}
