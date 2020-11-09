package main.attributes;

import main.utils.Validations;

/**
 * Class UncappedHealthPower is a concrete type of HealthPower, where the total
 * healthPower has no maximum value.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class UncappedHealthPower extends HealthPower {
    private double originalAmount;

    /**
     * Empty constructor for UncappedHealthPower, which initializes the
     * healthPower to zero.
     */
    public UncappedHealthPower() {
        this(0);
    }

    /**
     * Standard constructor for an UncappedHealthPower, which initializes
     * healthPower to the passed in value.
     *
     * Throws an IllegalArgumentException if healthPower is negative.
     * @param healthPower the initial value of healthPower
     */
    public UncappedHealthPower(double healthPower) {
        super(healthPower);
        this.originalAmount = healthPower;
    }

    /**
     * Increase the HealthPower by a percentage of the original amount.
     * Throws an IllegalArgumentException if percentage is negative.
     *
     * @param percentage Percentage to increase, on a scale of 0 to 100
     */
    @Override
    public void increaseByPercentageOfOriginal(double percentage) {
        Validations.nonNegative(percentage, "percentage");
        double amountToIncrease = (percentage / 100.0) * originalAmount;
        this.increaseHealthPowerBy(amountToIncrease);
    }

    /**
     * @return String representation of this UncappedHealthPower object.
     */
    @Override
    public String toString() {
        return "Uncapped Health Power: " + this.getHealthPower();
    }

    /**
     * Defines equality for two UncappedHealthPower objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of UncappedHealthPower, and they
     *          are equal according to the equality definition of super
     *          class HealthPower.
     *          See { @link main.attributes.HealthPower#equals(Object o) equals } method
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof UncappedHealthPower)) {
            return false;
        }

        return super.equals(o);
    }
}
