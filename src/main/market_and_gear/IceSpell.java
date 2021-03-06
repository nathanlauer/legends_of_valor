package main.market_and_gear;

import main.attributes.AbilityBuilder;
import main.attributes.Level;
import main.attributes.Mana;

/**
 * Class IceSpell is a type of spell that reduces the enemy's Strength Ability in addition
 * to the nominal damage it causes.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class IceSpell extends Spell {
    public static final String defaultName = "Ice Spell";

    /**
     * Empty constructor for a IceSpell.
     */
    public IceSpell() {
        this(IceSpell.defaultName, 0, new Level(0), new Mana(0), 0);
    }

    /**
     * Standard constructor for a IceSpell, which has Ability damage - that is, it reduces the strength of the enemy.
     * @param name name of this GearItem
     * @param price price of this GearItem
     * @param minLevel minLevel required to used this GearItem
     * @param mana Mana required to use this Spell.
     * @param damage amount of damage caused by this Spell.
     */
    public IceSpell(String name, int price, Level minLevel, Mana mana, double damage) {
        super(name, price, minLevel, damage, mana, AbilityBuilder.baseStrengthAbility());
    }

    /**
     * Defines equality for two IceSpell objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of IceSpell, and they are the same Spell
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof IceSpell)) {
            return false;
        }

        return super.equals(o);
    }
}
