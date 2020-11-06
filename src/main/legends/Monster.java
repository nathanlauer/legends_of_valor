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
    private Ability strength; // amount of damage a Monster does
    private Ability defense;
    private Ability agility; // dodge chance

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
        super(name, level, healthPower);
        this.strength = strength;
        this.defense = defense;
        this.agility = agility;
    }

    /**
     * Sets the strength Ability for this Monster to the passed in value.
     * @param newStrength new Ability for damage
     */
    public void setStrength(Ability newStrength) {
        strength = newStrength;
    }

    /**
     *
     * @return the strength Ability for this Monster
     */
    public Ability getStrength() {
        return strength;
    }

    /**
     * Sets the defense Ability for this Monster to the passed in value
     * @param newDefense the new value for defense of this Monster
     */
    public void setDefense(Ability newDefense) {
        defense = newDefense;
    }

    /**
     *
     * @return the defense Ability for this Monster
     */
    public Ability getDefense() {
        return defense;
    }

    /**
     * Sets the Agility (dodge chance) Ability for this Monster.
     * @param newAgility the new Ability for Agility of this Monster.
     */
    public void setAgility(Ability newAgility) {
        agility = newAgility;
    }

    /**
     *
     * @return the dodgeChance Ability for this Monster
     */
    public Ability getAgility() {
        return agility;
    }

    /**
     * @return String representation of this Monster object.
     */
    @Override
    public String toString() {
        String legend = super.toString();
        return legend + ". Monster! Stats: Damage: " + strength.toString() + ", Defense: " + defense.toString() + ", dodge_chance: " + agility.toString();
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
        return this.getDefense().equals(other.getDefense()) &&
                this.getStrength().equals(other.getStrength()) &&
                this.getAgility().equals(other.getAgility()) &&
                super.equals(o);
    }
}
