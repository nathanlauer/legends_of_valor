package main.legends;

import main.attributes.*;
import main.utils.Coffer;

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
                new Mana(0), new Coffer(0),
                AbilityBuilder.baseStrengthAbility(),
                AbilityBuilder.baseDexterityAbility(),
                AbilityBuilder.baseAgilityAbility());
    }

    /**
     * Standard constructor for a Paladin. By default, a Hero is created without any GearItems.
     * Heroes have a defense ability, but it starts at zero - that is, there is no natural
     * defense ability, a Hero must wear some Armor in order to increase their defense.
     *
     * @param name Name of this Hero
     * @param level Level of this Hero
     * @param healthPower HealthPower of this Hero
     * @param mana Mana of this Hero
     * @param coffer Coffer for this Hero
     * @param strength Strength Ability of this Hero
     * @param agility Agility Ability of this Hero
     * @param dexterity Dexterity Ability of this Hero
     */
    public Paladin(String name, Level level, HealthPower healthPower, Mana mana, Coffer coffer,
                   Ability strength, Ability agility, Ability dexterity) {
        super(name, level, healthPower, mana, coffer, strength, dexterity, agility);
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
