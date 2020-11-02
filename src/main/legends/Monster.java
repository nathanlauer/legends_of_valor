package main.legends;

import main.utils.Validations;

/**
 * Abstract Class Monster extends Legend, and represents a Monster. There are
 * a number of different types of Monster, thus this class is an abstract class.
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
    private double attackDamage;
    private double defense;
    private double dodgeChance;

    /**
     * Sets the attackDamage for this Monster to the passed in value.
     * Throws an IllegalArgumentException if newAttackDamage is negative.
     * @param newAttackDamage new value for attackDamage
     */
    public void setAttackDamage(double newAttackDamage) {
        Validations.nonNegative(newAttackDamage, "newAttackDamange");
        attackDamage = newAttackDamage;
    }

    /**
     *
     * @return the attackDamage for this Monster
     */
    public double getAttackDamage() {
        return attackDamage;
    }

    /**
     * Sets the defense value for this Monster to the passed in value
     * Throws an IllegalArgumentException if newDefense is negative.
     * @param newDefense the new value for defense of this Monster
     */
    public void setDefense(double newDefense) {
        Validations.nonNegative(newDefense, "newDefense");
        defense = newDefense;
    }

    /**
     *
     * @return the defense value for this Monster
     */
    public double getDefense() {
        return defense;
    }

    /**
     * Sets the dodgeChance for this Monster.
     * Throws an IllegalArgumentException if newDodgeChance is not a percentage (valid range [0, 1])
     * @param newDodgeChance the new value for dodgeChance of this Monster.
     */
    public void setDodgeChance(double newDodgeChance) {
        Validations.percentage(newDodgeChance, "newDodgeChance");
        dodgeChance = newDodgeChance;
    }

    /**
     *
     * @return the dodge chance for this Monster
     */
    public double getDodgeChance() {
        return dodgeChance;
    }

    /**
     * @return String representation of this Monster object.
     */
    @Override
    public String toString() {
        String legend = super.toString();
        return legend + ". Monster! Stats: Attack: " + attackDamage + ", Defense: " + defense + ", dodge_chance: " + dodgeChance;
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

        Monster other = (Monster) o;
        return Double.compare(this.getAttackDamage(), other.getAttackDamage()) == 0 &&
                Double.compare(this.getDefense(), other.getDefense()) == 0 &&
                Double.compare(this.getDodgeChance(), other.getDodgeChance()) == 0 &&
                super.equals(o);
    }
}
