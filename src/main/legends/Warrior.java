package main.legends;

import main.attributes.*;
import main.utils.Coffer;

/**
 * Class Warrior is a concrete type of Hero. Warriors favor their strength and
 * agility Abilities, meaning they are greater and grow more quickly.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Warrior extends Hero {
    public static final String defaultName = "Warrior Hero";

    /**
     * Empty constructor for a Warrior. Sets the name to "Warrior Hero" and
     * initializes all other attributes and Abilities to zero.
     */
    public Warrior() {
        this(Warrior.defaultName, new Level(0), new UncappedHealthPower(0),
                new Mana(0), new Coffer(0),
                new Ability("Agility", 0),
                new Ability("Dexterity", 0),
                new Ability("Strength", 0));
    }

    /**
     * Standard constructor for a Warrior.
     * @param name name of this Hero
     * @param level Level of this Hero
     * @param healthPower HealthPower of this Hero
     * @param agility agility Ability of this Hero
     * @param dexterity dexterity Ability of this Hero
     * @param strength strength Ability of this Hero
     */
    public Warrior(String name, Level level, HealthPower healthPower, Mana mana, Coffer coffer,
                   Ability agility, Ability dexterity, Ability strength) {
        HeroBuilder.initializeHeroAttributes(this, name, level, healthPower, mana, coffer, agility, dexterity, strength);
    }

    /**
     * @return String representation of this Warrior object.
     */
    @Override
    public String toString() {
        return super.toString() + " Paladin Hero! Warriors are strong!";
    }

    /**
     * Defines equality for two Warrior objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Warrior, and they are the same Hero
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Warrior)) {
            return false;
        }

        return super.equals(o);
    }
}
