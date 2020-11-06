package main.legends;

import main.attributes.*;
import main.market_and_gear.Weapon;
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
    private final Mana mana;
    private final Ability dexterity;
    private final Coffer coffer;
    private final GearItemList gearItemList;
    private final ActiveGearItems activeGearItems;

    /**
     * Standard constructor for a Hero. By default, a Hero is created without any GearItems.
     * Heroes have a defense ability, but it starts at zero - that is, there is no natural
     * defense ability, a Hero must wear some Armor in order to increase their defense.
     * @param name Name of this Hero
     * @param level Level of this Hero
     * @param healthPower HealthPower of this Hero
     * @param mana Mana of this Hero
     * @param coffer Coffer for this Hero
     * @param strength Strength Ability of this Hero
     * @param agility Agility Ability of this Hero
     * @param dexterity Dexterity Ability of this Hero
     */
    public Hero(String name, Level level, HealthPower healthPower,
                Mana mana, Coffer coffer, Ability strength,Ability agility, Ability dexterity) {
        super(name, level, healthPower, strength, AbilityBuilder.baseDefenseAbility(), agility);
        this.mana = mana;
        this.coffer = coffer;
        this.dexterity = dexterity;
        this.fainted = Hero.defaultFainted;
        this.gearItemList = new GearItemList();
        this.activeGearItems = new ActiveGearItems(this);
    }

    /**
     *
     * @return the List of all GearItem's this Hero has
     */
    public GearItemList getGearItemList() {
        return gearItemList;
    }

    /**
     *
     * @return the active GearItems this Hero has
     */
    public ActiveGearItems getActiveGearItems() {
        return activeGearItems;
    }

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
     *
     * @return the Coffer belonging to this Hero
     */
    public Coffer getCoffer() {
        return coffer;
    }

    /**
     *
     * @return the dexterity Ability of this Hero
     */
    public Ability getDexterity() {
        return dexterity;
    }


    /**
     * Calculates the amount of Damage this Legend would cause if it attacked.
     * Note: this is not just the Strength ability - a Legend may wield some
     * other GearItem which increases their damage amount.
     *
     * For a Hero, the amount of damage is (Strength + Weapon) * 0.05;
     * @return the amount of Damage in an attack
     */
    public int getDamageAmount() {
        int baseDamage = this.getStrength().getAbilityValue();
        if(this.getActiveGearItems().hasActiveWeapon()) {
            baseDamage += this.getActiveGearItems().getWeapon().getDamage();
        }
        double damageAmount = baseDamage * 0.05;
        return (int)Math.ceil(damageAmount); // favor the Hero
    }

    /**
     * Calculates the amount of Defense this Legend has if it were attacked.
     * Note: this is not just the Defense Ability - a legend may have some
     * GearItem which increases their defense amount.
     *
     * A Hero's defense comes entirely from Armor being worn
     * @return the Defense amount
     */
    public int getDefenseAmount() {
        if(!this.getActiveGearItems().hasActiveArmor()) {
            return 0;
        }

        return this.getActiveGearItems().getArmor().getDefense();
    }

    /**
     * Calculates the chance of dodging an attack for this Legend.
     * This is a probability, so it is normalized to range [0,1]
     *
     * For a Hero, this is calculated as agility * 0.002
     * @return the likelihood of dodging an attack.
     */
    public double getDodgeChance() {
        return Math.max(this.getAgility().getAbilityValue() * 0.002, 1);
    }


    /**
     * @return String representation of this Hero object.
     */
    @Override
    public String toString() {
        return "Hero! " + super.toString();
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
        return this.getDexterity().equals(other.getDexterity()) &&
                this.getMana().equals(other.getMana()) &&
                super.equals(o);
    }
}
