package main.legends;

import main.attributes.Ability;
import main.attributes.HealthPower;
import main.attributes.Level;
import main.attributes.UncappedHealthPower;

/**
 * Class Sorcerer is a concrete type of Hero. Sorcerers favor their dexterity and
 * agility Abilities, meaning that they are typically higher, and grow more rapidly.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Sorcerer extends Hero {
    public static final String defaultName = "Sorcerer Hero";

    /**
     * Empty constructor for a Sorcerer. Sets the name to "Sorcerer Hero" and
     * initializes all other attributes and Abilities to zero.
     */
    public Sorcerer() {
        this(Sorcerer.defaultName, new Level(0), new UncappedHealthPower(0),
                new Ability("Agility", 0),
                new Ability("Dexterity", 0),
                new Ability("Strength", 0));
    }

    /**
     * Standard constructor for a Sorcerer.
     * @param name name of this Hero
     * @param level Level of this Hero
     * @param healthPower HealthPower of this Hero
     * @param agility agility Ability of this Hero
     * @param dexterity dexterity Ability of this Hero
     * @param strength strength Ability of this Hero
     */
    public Sorcerer(String name, Level level, HealthPower healthPower,
                    Ability agility, Ability dexterity, Ability strength) {
        HeroBuilder.initializeHeroAttributes(this, name, level, healthPower, agility, dexterity, strength);
    }

    /**
     * @return String representation of this Sorcerer object.
     */
    @Override
    public String toString() {
        return super.toString() + " Sorcerer Hero! A spell to save the day!";
    }

    /**
     * Defines equality for two Sorcerer objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Sorcerer, and they are the same Hero
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Sorcerer)) {
            return false;
        }

        return super.equals(o);
    }
}
