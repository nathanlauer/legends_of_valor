package main.legends;

import main.attributes.Ability;
import main.attributes.Mana;
import main.utils.Coffer;

/**
 * Class Hero extends Legends, and represents a "good guy" in this game. There are a number
 * of different types of Heroes, and thus this is an abstract class. The structure of this
 * class and subclasses is similar to that of Monster.
 *
 * Every Hero has a name, some HealthPower, and a Level: these are common
 * to all Legends, and hence these attributes are inherited from Legend.
 * In addition, every Hero has some statistics, including strength,
 * dexterity, and agility. None of these can be negative.
 *
 * Beyond abilities, every Hero also has Mana and a Coffer. Mana is similar to
 * HealthPower, except that it applies to magical spells. Coffers contain money,
 * which a Hero can use to buy and sell Gear items.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class Hero extends Legend {
    public static final boolean defaultFainted = false;

    private boolean fainted;
    Mana mana;
    Coffer coffer;
    Ability agility;
    Ability dexterity;
    Ability strength;

    /**
     * Indicates whether or not this Hero has fainted.
     * @return true if this Hero has fainted, false otherwise.
     */
    public boolean hasFainted() {
        return fainted;
    }

    /**
     * Sets this Hero to be fainted or not.
     * @param fainted whether or not this Hero has fainted.
     */
    public void setFainted(boolean fainted) {
        this.fainted = fainted;
    }

    /**
     *
     * @return the Mana for this Hero
     */
    public Mana getMana() {
        return mana;
    }

    /**
     * Sets the Mana for this Hero to the passed in mana
     * @param mana the new Mana for this Hero
     */
    public void setMana(Mana mana) {
        this.mana = mana;
    }

    /**
     *
     * @return the Coffer belonging to this Hero
     */
    public Coffer getCoffer() {
        return coffer;
    }

    /**
     * Sets the Coffer for this Hero to the passed in Coffer.
     * @param coffer the new Coffer for this Hero.
     */
    public void setCoffer(Coffer coffer) {
        this.coffer = coffer;
    }

    /**
     *
     * @return the agility Ability of this Hero
     */
    public Ability getAgility() {
        return agility;
    }

    /**
     * Sets the agility of this Hero to the passed in agility.
     * @param newAgility new agility Ability for this Hero
     */
    public void setAgility(Ability newAgility) {
        agility = newAgility;
    }

    /**
     *
     * @return the dexterity Ability of this Hero
     */
    public Ability getDexterity() {
        return dexterity;
    }

    /**
     * Sets the dexterity of this Hero to the passed in dexterity.
     * @param newDexterity new dexterity Ability for this Hero
     */
    public void setDexterity(Ability newDexterity) {
        dexterity = newDexterity;
    }

    /**
     *
     * @return strength Ability of this Hero
     */
    public Ability getStrength() {
        return strength;
    }

    /**
     * Sets the strength Ability of this Hero.
     * @param newStrength new strength Ability for this Hero
     */
    public void setStrength(Ability newStrength) {
        strength = newStrength;
    }

    /**
     * @return String representation of this Hero object.
     */
    @Override
    public String toString() {
        return super.toString() + ". Hero! Abilities: " + agility.toString() + dexterity.toString() + strength.toString();
    }

    /**
     * Defines equality for two Hero objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Hero, and they share all abilities and Legend attributes.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Hero)) {
            return false;
        }

        Hero other = (Hero) o;
        return this.getStrength().equals(other.getStrength()) &&
                this.getAgility().equals(other.getAgility()) &&
                this.getDexterity().equals(other.getDexterity()) &&
                super.equals(o);
    }
}
