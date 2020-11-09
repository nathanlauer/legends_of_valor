package main.attributes;

import main.utils.Validations;

/**
 * Abstract Class HealthPower represents the "amount" of health a Legend has. A fully healthy
 * Legend has healthPower of 100, while a Legend that feints/dies has a healthPower
 * of 0.
 *
 * This class is a wrapper around a int healthPower, which encapsulates the logic
 * for incrementing and decrementing health of a Legend.
 *
 * In general, the following rules apply to health power:
 * 1) Health Power cannot be negative.
 * 2) Health Power cannot be decremented or incremented by a negative amount.
 * 3) There are two types of health power:
 *  (a) Uncapped, in which the health power can exceed a max value.
 *  (b) Capped, in which the health power cannot exceed a max value.
 *  The max value is by default set to 100.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class HealthPower extends Ability {
    private double fullAmount;

    /**
     * Standard constructor
     * Throws an IllegalArgumentException if healthPower is negative
     * @param healthPower the amount of HealthPower
     * @param fullAmount the amount of HealthPower that would be considered full
     */
    public HealthPower(double healthPower, double fullAmount) {
        super(AbilityType.HEALTH, healthPower);
        Validations.nonNegative(healthPower, "healthPower");
        Validations.nonNegative(fullAmount, "fullAmount");
        this.fullAmount = fullAmount;
    }

    /**
     * Sets the healthPower to the passed in value.
     *
     * Note that healthPower can't be less than zero. Thus, if the
     * passed in healthPower is less than 0, then this method will
     * throw an IllegalArgumentException
     * @param newHealthPower new healthPower
     */
    public void setHealthPower(double newHealthPower) {
        Validations.nonNegative(newHealthPower, "newHealthPower");
        this.setAbilityValue(newHealthPower);
    }

    /**
     *
     * @return the amount of HealthPower that would be considered full
     */
    public double getFullAmount() {
        return fullAmount;
    }

    /**
     * Sets the full amount of HealthPower to the passed in value
     * @param fullAmount the full amount of HealthPower
     */
    public void setFullAmount(double fullAmount) {
        this.fullAmount = fullAmount;
    }

    /**
     * Reduces this Legend's health power by the indicated amount. If the indicated
     * amount is greater than the current healthPower of this Legend (for example,
     * if the healthPower is 75, and amount is 80), then this Legend's health
     * power is set to 0 precisely, as healthPower can't be negative.
     *
     * Throws an IllegalArgumentException if amount is less than 0.
     * @param amount the amount to reduce health power by
     */
    public void reduceHealthPowerBy(double amount) {
        Validations.nonNegative(amount, "amount");

        double currentHealthPower = this.getHealthPower();
        if((currentHealthPower - amount) <= 0) {
            this.setHealthPower(0);
        } else {
            this.setHealthPower(currentHealthPower - amount);
        }
    }

    /**
     * Increases this Legend's health power by the indicated amount.
     * Throws an IllegalArgumentException if amount is negative
     *
     * @param amount the amount to increase healthPower.
     */
    public void increaseHealthPowerBy(double amount) {
        Validations.nonNegative(amount, "amount");
        this.setHealthPower(this.getHealthPower() + amount);
    }

    /**
     * Increments the HealthPower by a the passed in percentage.
     * For example, a client may choose to increase the HealthPower by 10%
     * (which is different than increaseHealthPowerBy, which increase by a
     * fixed amount)
     *
     * Throws an IllegalArgumentException if percentage is negative.
     * @param percentage the percentage to increase, on a scale of 0-100
     */
    public void increaseByPercentage(double percentage) {
        Validations.nonNegative(percentage, "percentage");
        double amountToIncrease = percentage/100.0 * this.getHealthPower();
        if(this.getHealthPower() + amountToIncrease > this.getFullAmount()) {
            this.setHealthPower(this.getFullAmount());
        } else {
            this.increaseHealthPowerBy(amountToIncrease);
        }
    }

    /**
     *
     * @return the healthPower of this Legend
     */
    public double getHealthPower() {
        return this.getAbilityValue();
    }

    /**
     * Indicates whether or not this HealthPower has "some" health, meaning
     * that it represents a Legend that is alive.
     * @return true if healthPower > 0, false otherwise
     */
    public boolean hasSomeHealth() {
        return this.getHealthPower() > 0;
    }

    /**
     * Increase the HealthPower by a percentage of the original amount.
     * Throws an IllegalArgumentException if percentage is negative.
     */
    public abstract void increaseByPercentageOfFull(double percentage);

    /**
     * Indicates whether or not this HealthPower is full
     * @return true if this HealthPower is full, false otherwise
     */
    public boolean isFull() {
        return Double.compare(this.getFullAmount(), this.getHealthPower()) == 0;
    }

    /**
     *
     * @return String representation of this HealthPower
     */
    @Override
    public String toString() {
        return "Health Power: " + this.getHealthPower();
    }

    /**
     * Defines equality for two HealthPower objects.
     * @param o the other object in question
     * @return true if the other object is a health power, and has the same
     *          healthPower value as this. Note that we are comparing ints
     *          here, so two healthPower's are considered equivalent if
     *          their int values are within a certain round off value.
     */
    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }

        if(!(o instanceof HealthPower)) {
            return false;
        }

        HealthPower other = (HealthPower)o;
        return other.getHealthPower() == this.getHealthPower();
    }
}
