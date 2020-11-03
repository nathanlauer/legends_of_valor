package main.market_and_gear;

import main.attributes.AbilityBuilder;
import main.attributes.Level;
import main.attributes.Mana;

/**
 * Class LightningSpell is a concrete type of Spell, and hence also a GearItem.
 * When used, it reduces the dodge chance of the enemy.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class LightningSpell extends Spell {
    public static final String defaultName = "Lightning Spell";

    /**
     * Empty constructor for a LightningSpell.
     */
    public LightningSpell() {
        this(LightningSpell.defaultName, 0.0, new Level(0), new Mana(0), 0.0);
    }

    /**
     * Standard constructor for a LightningSpell, which has Ability dodgeChance.
     * @param name name of this GearItem
     * @param price price of this GearItem
     * @param minLevel minLevel required to used this GearItem
     * @param mana Mana required to use this Spell.
     * @param damage amount of damage caused by this Spell.
     */
    public LightningSpell(String name, double price, Level minLevel, Mana mana, double damage) {
        super(name, price, minLevel, damage, mana, AbilityBuilder.baseDodgeChanceAbility());
    }

    /**
     * Defines equality for two LightningSpell objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of LightningSpell, and they are the same Spell.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof LightningSpell)) {
            return false;
        }

        return super.equals(o);
    }
}
