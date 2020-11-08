package main.market_and_gear;

import main.attributes.Ability;
import main.attributes.Level;
import main.attributes.Mana;
import main.utils.Validations;

/**
 * Class Spell is an abstract class that extends from GearItem. It represents a magical
 * attack, which not only causes damage, but also reduces an opponent's Ability by some amount.
 *
 * A spell also requires a certain amount of Mana to use.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class Spell extends GearItem {
    private double damage;
    private Mana mana;
    private Ability ability;

    /**
     * Standard constructor for a Spell.
     * Throws an IllegalArgumentException if damage is negative
     *
     * @param name name of this GearItem
     * @param price price of this GearItem
     * @param minLevel minLevel to use this GearItem
     * @param damage the amount of damage this Spell will cause (and how much it reduces an opponent's Ability)
     * @param mana the required amount of Mana to use this Spell
     */
    public Spell(String name, int price, Level minLevel, double damage, Mana mana, Ability ability) {
        super(name, price, minLevel);

        Validations.nonNegative(damage, "damage");
        this.damage = damage;
        this.mana = mana;
        this.ability = ability;
    }

    /**
     *
     * @return the damage amount of this Spell
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Sets the damage amount of this Spell
     * Throws an IllegalArgumentException if newDamage is negative
     * @param newDamage the new amount of damage this Spell will cause.
     */
    public void setDamage(double newDamage) {
        Validations.nonNegative(newDamage, "newDamage");
        damage = newDamage;
    }

    /**
     *
     * @return the Mana required to use this Spell
     */
    public Mana getMana() {
        return mana;
    }

    /**
     * Sets the Mana required to use this Spell
     * @param newMana the new Mana that will be required to use this Spell.
     */
    public void setMana(Mana newMana) {
        mana = newMana;
    }

    /**
     *
     * @return the ability which this Spell affects.
     */
    public Ability getAbility() {
        return ability;
    }

    /**
     * Sets the Ability for this Spell, to the passed in value
     * @param ability the new Ability for this Spell.
     */
    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    /**
     *
     * @return the Type of this Gear Item
     */
    @Override
    public GearItemType getType() {
        return GearItemType.SPELL;
    }

    /**
     * @return String representation of this Spell object.
     */
    @Override
    public String toString() {
        return super.toString() + ". Spell! Damage: " + this.getDamage() + ", Mana: " + this.getMana() + ", Ability: " + this.getAbility();
    }

    /**
     * Defines equality for two Spell objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Spell, they share the same Spell attributes, and
     * are the same GearItem.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Spell)) {
            return false;
        }

        Spell other = (Spell) o;
        return this.getMana().equals(other.getMana()) &&
                this.getAbility().equals(other.getAbility()) &&
                this.getDamage() == other.getDamage()&&
                super.equals(o);
    }

    /**
     * Clones a Spell object, that is, returns a deep copy of this Spell.
     * @return deep copy of this Spell
     * @throws CloneNotSupportedException if this Object can't be cloned
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Spell cloned = (Spell)super.clone();
        cloned.setMana((Mana)this.getMana().clone());
        cloned.setAbility((Ability)this.getAbility().clone());

        return cloned;
    }
}
