package main.market_and_gear;

import main.attributes.Ability;
import main.attributes.Level;
import main.attributes.Mana;

/**
 * Class FireSpell
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class FireSpell extends Spell {
    public static final String defaultName = "Fire Spell";

    /**
     * Empty constructor for a FireSpell.
     */
    public FireSpell() {
        this(FireSpell.defaultName, 0.0, new Level(0), new Mana(0), 0.0);
    }

    /**
     * Standard constructor for a FireSpell, which has Ability defense.
     * @param name name of this GearItem
     * @param price price of this GearItem
     * @param minLevel minLevel required to used this GearItem
     * @param mana Mana required to use this Spell.
     * @param damage amount of damage caused by this Spell.
     */
    public FireSpell(String name, double price, Level minLevel, Mana mana, double damage) {
        super(name, price, minLevel, damage, mana, new Ability("Defense", 0));
    }

    /**
     * Defines equality for two FireSpell objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of FireSpell, and they are the same Spell
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof FireSpell)) {
            return false;
        }

        return super.equals(o);
    }
}
