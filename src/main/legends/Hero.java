package main.legends;

import main.attributes.*;
import main.market_and_gear.Armor;
import main.market_and_gear.Weapon;
import main.utils.Coffer;

import java.text.DecimalFormat;
import java.util.List;

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
    public static final String outputFormat = "%-4s%-21s%-15s%-15s%-6s%-8s%-8s%-8s%-21s%-21s%-5s%-5s";
    public static final boolean defaultFainted = false;

    private boolean fainted;
    private final Mana mana;
    private final Ability dexterity;
    private final Coffer coffer;
    private final GearItemList gearItemList;
    private final ActiveGearItems activeGearItems;
    private final List<Ability> specialAbilities;
    private final Experience experience;

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
                Mana mana, Coffer coffer, Ability strength, Ability agility, Ability dexterity, List<Ability> specialAbilities) {
        super(name, level, healthPower, strength, AbilityBuilder.baseDefenseAbility(), agility);
        this.mana = mana;
        this.coffer = coffer;
        this.dexterity = dexterity;
        this.fainted = Hero.defaultFainted;
        this.gearItemList = new GearItemList();
        this.activeGearItems = new ActiveGearItems(this);
        this.specialAbilities = specialAbilities;
        this.experience = new Experience(this);

        // Add the Hero-unique Abilities to this Legend.
        this.addAbility(this.dexterity);
        this.addAbility(mana);
    }

    /**
     * Sets the experience of this Hero. This function is intended to be used when a
     * Hero is created to set the initial experience level.
     * @param experience the starting experience of this Hero.
     */
    public void setStartingExperience(int experience) {
        getExperience().setExperience(experience);
    }

    /**
     *
     * @return the experience of this Hero
     */
    public Experience getExperience() {
        return experience;
    }

    /**
     *
     * @return a List of special Abilities for this Hero
     */
    public List<Ability> getSpecialAbilities() {
        return this.specialAbilities;
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
     * For a Hero, the amount of damage is the Strength ability plus a possible Weapon
     * @return the amount of Damage in an attack
     */
    public double getDamageAmount() {
        double baseDamage = this.getStrength().getAbilityValue();
        if(this.getActiveGearItems().hasActiveWeapon()) {
            baseDamage += this.getActiveGearItems().getWeapon().getDamage();
        }
        return baseDamage;
    }

    /**
     * Calculates the amount of Defense this Legend has if it were attacked.
     * Note: this is not just the Defense Ability - a legend may have some
     * GearItem which increases their defense amount.
     *
     * A Hero's defense comes entirely from Armor being worn
     * @return the Defense amount
     */
    public double getDefenseAmount() {
        if(!this.getActiveGearItems().hasActiveArmor()) {
            return 0;
        }

        return this.getActiveGearItems().getArmor().getDefense();
    }

    /**
     * Calculates the chance of dodging an attack for this Legend.
     * This is a probability, so it is normalized to range [0,1]
     *
     * DodgeChance is normalized to an Agility range of 0 - 1000. That is,
     * and Agility of 1000 will mean a dodge chance of 100%.
     *
     * For a Hero, this is calculated as agility * 0.002
     * @return the likelihood of dodging an attack.
     */
    public double getDodgeChance() {
        return Math.min((this.getAgility().getAbilityValue() / 2.0) * 0.002, 1);
    }

    /**
     *
     * @return the format string which can be used to output all GearItems of this type
     */
    public String getOutputFormat() {
        return Hero.outputFormat;
    }

    /**
     *
     * @return the Header string that can be used to print out the relevant GearItems.
     */
    public String getHeaderString() {
        return String.format(getOutputFormat(), "Lvl", "Hero Name", "HP", "Mana", "Coins", "Str", "Agl", "Dxt", "Weapon", "Armor", "#Spl", "#Ptn");
    }

    /**
     * @return String representation of this Hero object.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.0");
        String hp = df.format(getHealthPower().getHealthPower()) + "/" + df.format(getHealthPower().getFullAmount());
        String mana = df.format(getMana().getManaAmount()) + "/" + df.format(getMana().getFullAmount());
        String weapon = "None";
        if(getActiveGearItems().hasActiveWeapon()) {
            Weapon wielded = getActiveGearItems().getWeapon();
            weapon = wielded.getName() + ":" + wielded.getDamage();
        }
        String armor = "None";
        if(getActiveGearItems().hasActiveArmor()) {
            Armor worn = getActiveGearItems().getArmor();
            armor = worn.getName() + ":" + worn.getDefense();
        }
        int numSpells = getGearItemList().getSpells().size();
        int numPotions = getGearItemList().getUsablePotions().size();
        return String.format(getOutputFormat(), getLevel(), getName(), hp, mana, getCoffer().getNumCoins(),
                getStrength().getAbilityValue(), getAgility().getAbilityValue(), getDexterity().getAbilityValue(),
                weapon, armor, numSpells, numPotions);
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
