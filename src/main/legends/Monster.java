package main.legends;

import main.attributes.Ability;
import main.attributes.HealthPower;
import main.attributes.Level;

/**
 * Abstract Class Monster extends Legend, and represents a Monster. There are
 * a number of different types of Monster, thus this class is an abstract class.
 * The structure of this class and its subclasses is similar to that of Hero.
 *
 * Every monster has a name, some HealthPower, and a Level: these are common
 * to all Legends, and hence these attributes are inherited from Legend.
 * In addition, every Monster has some statistics, including attackDamage,
 * defense, and dodgeChance. None of these can be negative, and dodgeChance
 * is a percentage (between 0 and 1, inclusive)
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class Monster extends Legend {


    /**
     * Standard constructor for a Monster
     * @param name Name of this Monster
     * @param level Level of this Monster
     * @param healthPower HealthPower of this Monster
     * @param strength Strength (amount of Damage) of this Monster
     * @param defense Defense of this Monster
     * @param agility Agility (dodge change) of this Monster
     */
    public Monster(String name, Level level, HealthPower healthPower,
                   Ability strength, Ability defense, Ability agility) {
        super(name, level, healthPower, strength, defense, agility);
    }

    /**
     * Calculates the amount of Damage this Legend would cause if it attacked.
     * Note: this is not just the Strength ability - a Legend may wield some
     * other GearItem which increases their damage amount.
     *
     * For a Monster, the damage amount is the strength of the Monster.
     * @return the amount of Damage in an attack
     */
    @Override
    public double getDamageAmount() {
        return this.getStrength().getAbilityValue();
    }

    /**
     * Calculates the amount of Defense this Legend has if it were attacked.
     * Note: this is not just the Defense Ability - a legend may have some
     * GearItem which increases their defense amount.
     *
     * For a Monster, the defense amount is just the defense ability
     * @return the Defense amount
     */
    @Override
    public double getDefenseAmount() {
        return this.getDefense().getAbilityValue();
    }

    /**
     * Calculates the chance of dodging an attack for this Legend.
     * This is a probability, so it is normalized to range [0,1]
     *
     * For a Monster, the chance of dodging an attack is agility * .01
     * @return the likelihood of dodging an attack.
     */
    public double getDodgeChance() {
        return Math.min(this.getAgility().getAbilityValue() * 0.01, 1);
    }

    /**
     * @return String representation of this Monster object.
     */
    @Override
    public String toString() {
        String legend = super.toString();
        return "Monster! " + legend;
    }

    /**
     * Defines equality for two Monster objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Monster, they share the same statistics, and the same
     * Legend attributes.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Monster)) {
            return false;
        }

        return super.equals(o);
    }
}
