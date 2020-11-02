package main.legends;

import main.attributes.Ability;
import main.attributes.HealthPower;
import main.attributes.Level;
import main.attributes.UncappedHealthPower;

/**
 * Class Paladin is a concrete type of Hero. Paladins favor their strength and
 * dexterity Abilities, meaning that they are greater and grow more quickly.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Paladin extends Hero {
    public static final String defaultName = "Paladin Hero";

    /**
     * Empty constructor for a Paladin. Sets the name to "Paladin Hero" and
     * initializes all other attributes and Abilities to zero.
     */
    public Paladin() {
        this(Paladin.defaultName, new Level(0), new UncappedHealthPower(0),
                new Ability("Agility", 0),
                new Ability("Dexterity", 0),
                new Ability("Strength", 0));
    }

    /**
     * Standard constructor for a Paladin.
     * @param name name of this Hero
     * @param level Level of this Hero
     * @param healthPower HealthPower of this Hero
     * @param agility agility Ability of this Hero
     * @param dexterity dexterity Ability of this Hero
     * @param strength strength Ability of this Hero
     */
    public Paladin(String name, Level level, HealthPower healthPower,
                    Ability agility, Ability dexterity, Ability strength) {
        HeroBuilder.initializeHeroAttributes(this, name, level, healthPower, agility, dexterity, strength);
    }

    /**
     * @return String representation of this Paladin object.
     */
    @Override
    public String toString() {
        return super.toString() + " Paladin Hero! Your knight in shining armor has arrived!";
    }

    /**
     * Defines equality for two Paladin objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Paladin, and they are the same Hero
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Paladin)) {
            return false;
        }

        return super.equals(o);
    }
}
