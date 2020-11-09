package main.legends;

import main.attributes.*;

/**
 * Class Dragon is a concrete instance of a Monster.
 * At the moment, Dragons don't have any abilities that generic Monsters don't have,
 * however, Dragons tend to have higher damage Abilities.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Dragon extends Monster {
    public static final String defaultName = "Dragon Monster";
    /**
     * Empty constructor for a Dragon Monster.
     * All values are initialized to zero, and the name is set to "Dragon Monster"
     */
    public Dragon() {
        this(Dragon.defaultName, new Level(0), new UncappedHealthPower(0),
                AbilityBuilder.baseStrengthAbility(),
                AbilityBuilder.baseDefenseAbility(),
                AbilityBuilder.baseAgilityAbility());
    }

    /**
     * Standard constructor for a Dragon Monster, where all values are specified.
     * @param name name of this Dragon Monster
     * @param level the level of this Monster.
     * @param healthPower healthPower of this Monster.
     * @param strength attack Ability for this Dragon monster.
     * @param defense defense Ability for this Dragon monster.
     * @param agility dodgeChance Ability for this Dragon.
     */
    public Dragon(String name, Level level, HealthPower healthPower, Ability strength, Ability defense, Ability agility) {
        super(name, level, healthPower, strength, defense, agility);
    }

    /**
     * @return String representation of this Dragon object.
     */
    @Override
    public String toString() {
        return super.toString() + ". Dragon Monster! Roar! Fire!";
    }

    /**
     * Defines equality for two Dragon objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Dragon, and if they are the same Monster.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Dragon)) {
            return false;
        }

        return super.equals(o);
    }
}
