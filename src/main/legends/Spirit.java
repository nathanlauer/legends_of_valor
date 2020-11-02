package main.legends;

import main.attributes.Ability;
import main.attributes.HealthPower;
import main.attributes.Level;
import main.attributes.UncappedHealthPower;
import main.utils.Validations;

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
                new Ability("Damage", 0),
                new Ability("Defense", 0),
                new Ability("DodgeChance", 0));
    }

    /**
     * Standard constructor for a Spirit Monster, where all values are specified.
     * @param name name of this Spirit Monster
     * @param level the level of this Monster.
     * @param healthPower healthPower of this Monster.
     * @param damage attack Ability for this Spirit monster.
     * @param defense defense Ability for this Spirit monster.
     * @param dodgeChance dodgeChance Ability for this Spirit.
     */
    public Spirit(String name, Level level, HealthPower healthPower, Ability damage, Ability defense, Ability dodgeChance) {
        MonsterBuilder.initializeMonsterAttributes(this, name, level, healthPower, damage, defense, dodgeChance);
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
