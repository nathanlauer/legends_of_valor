package main.legends;

import main.attributes.*;

/**
 * Class Spirit is a concrete instance of a Monster.
 * At the moment, Spirits don't have any abilities that generic Monsters don't have,
 * however, Spirits tend to have higher dodgeChance Abilities.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Spirit extends Monster {
    public static final String defaultName = "Spirit Monster";
    /**
     * Empty constructor for a Spirit Monster.
     * All values are initialized to zero, and the name is set to "Spirit Monster"
     */
    public Spirit() {
        this(Spirit.defaultName, new Level(0), new UncappedHealthPower(0),
                AbilityBuilder.baseStrengthAbility(),
                AbilityBuilder.baseDefenseAbility(),
                AbilityBuilder.baseAgilityAbility());
    }

    /**
     * Standard constructor for a Spirit Monster, where all values are specified.
     * @param name name of this Spirit Monster
     * @param level the level of this Monster.
     * @param healthPower healthPower of this Monster.
     * @param strength attack Ability for this Spirit monster.
     * @param defense defense Ability for this Spirit monster.
     * @param agility dodgeChance Ability for this Spirit.
     */
    public Spirit(String name, Level level, HealthPower healthPower, Ability strength, Ability defense, Ability agility) {
        super(name, level, healthPower, strength, defense, agility);
    }

    /**
     * @return String representation of this Spirit object.
     */
    @Override
    public String toString() {
        return super.toString() + ". Spirit Monster! Ooooooo";
    }

    /**
     * Defines equality for two Spirit objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Spirit, and they are the same Monster.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Spirit)) {
            return false;
        }

        return super.equals(o);
    }
}
