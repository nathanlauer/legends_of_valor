package main.legends;

import main.attributes.HealthPower;
import main.attributes.Level;
import main.attributes.UncappedHealthPower;

/**
 * Class Dragon is a concrete instance of a Monster.
 * At the moment, Dragons don't have any abilities that generic Monsters don't have.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Dragon extends Monster {
    /**
     * Empty constructor for a Dragon Monster.
     * All values are initialized to zero, and the name is set to "Dragon Monster"
     */
    public Dragon() {
        this("Dragon Monster!", new Level(0), new UncappedHealthPower(0), 0, 0, 0);
    }

    /**
     * Constructor with just the name for this Monster. All other values are set to zero.
     * @param name the name of this Monster.
     */
    public Dragon(String name) {
        this(name, new Level(0), new UncappedHealthPower(0), 0, 0, 0);
    }

    /**
     * Construct a Dragon Monster with the passed in name, attack, defense, and dodgeChance attributes.
     * HealthPower and Level are initialized to zero.
     * @param name name of this Dragon Monster
     * @param attack attack value for this Dragon monster. Can't be negative.
     * @param defense defense value for this Dragon monster. Can't be negative.
     * @param dodgeChance dodgeChance value for this Dragon. Must be in range [0, 1]
     */
    public Dragon(String name, double attack, double defense, double dodgeChance) {
        this(name, new Level(0), new UncappedHealthPower(0), attack, defense, dodgeChance);
    }

    /**
     * Standard constructor for a Dragon Monster, where all values are specified.
     * @param name name of this Dragon Monster
     * @param level the level of this Monster.
     * @param healthPower healthPower of this Monster.
     * @param attack attack value for this Dragon monster. Can't be negative.
     * @param defense defense value for this Dragon monster. Can't be negative.
     * @param dodgeChance dodgeChance value for this Dragon. Must be in range [0, 1]
     */
    public Dragon(String name, Level level, HealthPower healthPower, double attack, double defense, double dodgeChance) {
        MonsterBuilder.initializeMonsterAttributes(this, name, level, healthPower, attack, defense, dodgeChance);
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
