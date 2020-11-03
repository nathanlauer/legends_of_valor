package main.legends;

import main.attributes.*;

/**
 * Class Exoskeleton is a concrete instance of a Monster.
 * At the moment, Exoskeletons don't have any abilities that generic Monsters don't have,
 * however Exoskeletons tend to have greater defense Abilities.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Exoskeleton extends Monster {
    public static final String defaultName = "Exoskeleton Monster";
    /**
     * Empty constructor for a Exoskeleton Monster.
     * All values are initialized to zero, and the name is set to "Exoskeleton Monster"
     */
    public Exoskeleton() {
        this(Exoskeleton.defaultName, new Level(0), new UncappedHealthPower(0),
                AbilityBuilder.baseDamageAbility(),
                AbilityBuilder.baseDefenseAbility(),
                AbilityBuilder.baseDodgeChanceAbility());
    }

    /**
     * Standard constructor for a Exoskeleton Monster, where all values are specified.
     * @param name name of this Exoskeleton Monster
     * @param level the level of this Monster.
     * @param healthPower healthPower of this Monster.
     * @param damage damage Ability for this Exoskeleton monster.
     * @param defense defense Ability for this Exoskeleton monster.
     * @param dodgeChance dodgeChance Ability for this Exoskeleton.
     */
    public Exoskeleton(String name, Level level, HealthPower healthPower, Ability damage, Ability defense, Ability dodgeChance) {
        MonsterBuilder.initializeMonsterAttributes(this, name, level, healthPower, damage, defense, dodgeChance);
    }

    /**
     * @return String representation of this Exoskeleton object.
     */
    @Override
    public String toString() {
        return super.toString() + ". Exoskeleton Monster! Clunk!";
    }

    /**
     * Defines equality for two Exoskeleton objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Exoskeleton, and they are the same Monster.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Exoskeleton)) {
            return false;
        }

       return super.equals(o);
    }
}
