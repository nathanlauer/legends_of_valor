package main.legends;

import main.attributes.*;
import main.utils.Coffer;

import java.util.ArrayList;
import java.util.Arrays;

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
                AbilityBuilder.baseStrengthAbility(),
                AbilityBuilder.baseAgilityAbility(),
                AbilityBuilder.baseDexterityAbility());
    }

    /**
     * Standard constructor for a Warrior. By default, a Hero is created without any GearItems.
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
    public Warrior(String name, Level level, HealthPower healthPower, Mana mana, Coffer coffer,
                   Ability strength, Ability agility, Ability dexterity) {
        super(name, level, healthPower, mana, coffer,
                strength, agility, dexterity, new ArrayList<>(Arrays.asList(strength, agility)));
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
