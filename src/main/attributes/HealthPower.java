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
public abstract class HealthPower {
    private int healthPower;

    /**
     * Sets the healthPower to the passed in value.
     *
     * Note that healthPower can't be less than zero. Thus, if the
     * passed in healthPower is less than 0, then this method will
     * throw an IllegalArgumentException
     * @param newHealthPower new healthPower
     */
    public void setHealthPower(int newHealthPower) {
        Validations.nonNegative(newHealthPower, "newHealthPower");
        healthPower = newHealthPower;
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
    public void reduceHealthPowerBy(int amount) {
        Validations.nonNegative(amount, "amount");

        int currentHealthPower = this.getHealthPower();
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
    public void increaseHealthPowerBy(int amount) {
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
    public void increaseByPercentage(int percentage) {
        Validations.nonNegative(percentage, "percentage");
        int amountToIncrease = (int)Math.ceil(percentage/100.0 * this.getHealthPower());
        this.increaseHealthPowerBy(amountToIncrease);
    }

    /**
     *
     * @return the healthPower of this Legend
     */
    public int getHealthPower() {
        return healthPower;
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
