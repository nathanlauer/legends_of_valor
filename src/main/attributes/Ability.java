package main.attributes;

import main.utils.Validations;

/**
 * Class Ability is a wrapper around a double, which represents some ability that a Monster
 * or a Hero have. There are two key points here:
 * 1) These are extracted out to a class, to clearly represent the concept of an ability.
 * Even though this class is just a wrapper around a double, the naming convention makes
 * it clear to clients precisely what this double represents.
 * 2) Abilities cannot be negative.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Ability {
    private final String name;
    private double abilityValue;
    public static final String defaultName = "Ability";

    /**
     * Empty constructor for an Ability. Name is set to "Ability" and value is 0.
     */
    public Ability() {
        this(Ability.defaultName, 0);
    }

    /**
     * Constructor with specified initial abilityValue. Name is set to "Ability"
     * Throws IllegalArgumentException if abilityValue is negative
     * @param abilityValue initial value for abilityValue.
     */
    public Ability(double abilityValue) {
        this(Ability.defaultName, abilityValue);
    }

    /**
     * Standard constructor for an Ability.
     * Throws IllegalArgumentException if abilityValue is negative
     * @param name name of this Ability.
     * @param abilityValue the value for this ability
     */
    public Ability(String name, double abilityValue) {
        Validations.nonNegative(abilityValue, "abilityValue");
        this.name = name;
        this.abilityValue = abilityValue;
    }

    public String getName() {
        return name;
    }

    /**
     *
     * @return the abilityValue of this Ability
     */
    public double getAbilityValue() {
        return abilityValue;
    }

    /**
     * Sets the abilityValue for this Ability to the passed in value.
     * Throws an IllegalArgumentException if newAbilityValue is negative.
     * @param newAbilityValue new value for this Ability.
     */
    public void setAbilityValue(double newAbilityValue) {
        Validations.nonNegative(newAbilityValue, "newAbilityValue");
        abilityValue = newAbilityValue;
    }

    /**
     * Increases the abilityValue by the passed in amount.
     * Throws an IllegalArgumentException if amount is negative.
     * @param amount amount to increase abilityValue by
     */
    public void increaseAbilityBy(double amount) {
        Validations.nonNegative(amount, "amount");
        this.setAbilityValue(this.getAbilityValue() + amount);
    }

    /**
     * Decreases the abilityValue by the specified amount. If amount
     * is greater than the current abilityValue, then abilityValue is set
     * to zero. (e.g. there is no overflow to a negative value)
     * Throws an IllegalArgumentException if amount is negative.
     * @param amount amount to decrease this ability by.
     */
    public void decreaseAbilityBy(double amount) {
        Validations.nonNegative(amount, "amount");
        if(amount > this.getAbilityValue()) {
            this.setAbilityValue(0);
        } else {
            this.setAbilityValue(this.getAbilityValue() - amount);
        }
    }

    /**
     * @return String representation of this Ability object.
     */
    @Override
    public String toString() {
        return "Ability " + name + ", value: " + this.getAbilityValue();
    }

    /**
     * Defines equality for two Ability objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Ability, they have the same name, and the same abilityValue
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Ability)) {
            return false;
        }

        Ability other = (Ability) o;
        return this.getName().equals(other.getName()) &&
                Double.compare(this.getAbilityValue(), other.getAbilityValue()) == 0;
    }
}
