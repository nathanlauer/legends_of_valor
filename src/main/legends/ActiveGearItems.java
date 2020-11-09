package main.legends;

import main.market_and_gear.*;
import main.utils.Validations;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ActiveGearItems tracks the GearItems for a Hero that are currently "active."
 * Active means that the item is available for use - the Hero is wielding a certain
 * Weapon, or wearing some Armor, or a list of Potions that can still be used.
 *
 * Note that this class is not responsible for what happens to an item when it is
 * used. It is simply a list of the GearItems that are active.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/4/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class ActiveGearItems {
    private Weapon weapon; // for now, only one Weapon can be active
    private Armor armor; // for now, only one set of Armor can be worn
    private final List<GearItem> potions;
    private final List<GearItem> spells;
    private final Hero hero;

    /**
     * Empty constructor for an ActiveGearItems instance
     */
    public ActiveGearItems(Hero hero) {
        this(hero,null, null, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Standard constructor
     * @param hero Hero that holds these active GearItems.
     * @param weapon Weapon that is active
     * @param armor Armor that is active
     * @param potions List of active Potions
     * @param spells List of active Spells
     */
    public ActiveGearItems(Hero hero, Weapon weapon, Armor armor, List<GearItem> potions, List<GearItem> spells) {
        this.hero = hero;
        this.weapon = weapon;
        this.armor = armor;
        this.potions = new ArrayList<>(potions);
        this.spells = new ArrayList<>(spells);
    }

    /**
     *
     * @return the Weapon being held
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     *
     * @return the Armor being worn
     */
    public Armor getArmor() {
        return armor;
    }

    /**
     *
     * @return List of active Potions
     */
    public List<GearItem> getPotions() {
        return potions;
    }

    /**
     *
     * @return List of active Spells.
     */
    public List<GearItem> getSpells() {
        return spells;
    }

    /**
     * Indicates whether or not a Weapon is active
     * @return true is a Weapon is active, false otherwise
     */
    public boolean hasActiveWeapon() {
        return weapon != null;
    }

    /**
     * Indicates whether or not some Armor is active
     * @return true if some Armor is being worn
     */
    public boolean hasActiveArmor() {
        return armor != null;
    }

    /**
     * Indicates whether or not there are some active Potions.
     * @return true if there is at least one active Potion, false otherwise
     */
    public boolean hasActivePotions() {
        return potions.size() > 0;
    }

    /**
     * Indicates whether or not there are some active Spells.
     * @return true if there is at least one active Spell, false otherwise.
     */
    public boolean hasActiveSpells() {
        return spells.size() > 0;
    }

    /**
     * Deactivates the currently active Weapon.
     * Internally, this is equivalent to setting the Weapon to null.
     */
    public void deactivateWeapon() {
        this.weapon = null;
    }

    /**
     * Activates the passed in Weapon. If another Weapon is already active,
     * that one is first deactivated.
     *
     * @throws NonOwnedGearItemException if the Weapon that is attempted to be activated is not owned by the relevant Hero
     * @param weapon The Weapon to be activated
     */
    public void activateWeapon(Weapon weapon) {
        Validations.heroOwnsGearItem(hero, weapon);
        if(this.hasActiveWeapon()) {
            this.deactivateWeapon();
        }
        this.weapon = weapon;
    }

    /**
     * Removes any currently worn armor
     * Internally, this is equivalent to setting armor to null.
     */
    public void removeArmor() {
        this.armor = null;
    }

    /**
     * Puts on the passed in Armor, which effectively means making the passed in Armor active.
     * If some previous Armor was active, that Armor is first deactivated.
     * @param armor the Armor to be active
     */
    public void putOnArmor(Armor armor) {
        Validations.heroOwnsGearItem(hero, armor);
        if(this.hasActiveArmor()) {
            this.removeArmor();
        }
        this.armor = armor;
    }

    /**
     * Adds the passed in Potion to the list of active Potions
     * @param potion Potion to be activated
     */
    public void addPotion(Potion potion) {
        Validations.heroOwnsGearItem(hero, potion);
        this.potions.add(potion);
    }

    /**
     * Removes the passed in Potion from the active list of Potions.
     * @param potion the Potion to be deactivated
     */
    public void removePotion(Potion potion) {
        this.potions.remove(potion);
    }

    /**
     * Adds the passed in Spell to the list of active Spells
     * @param spell the Spell to be activated
     */
    public void addSpell(Spell spell) {
        Validations.heroOwnsGearItem(hero, spell);
        this.spells.add(spell);
    }

    /**
     * Removes the passed in Spell from the list of active Spells.
     * @param spell the Spell to be removed
     */
    public void removeSpell(Spell spell) {
        this.spells.remove(spell);
    }

    /**
     * A more generic method to remove a GearItem.
     * This method checks to see if any of the contained Weapon, Armor,
     * Potions list, or Spells list contains the passed in GearItem, and
     * if so, removes it.
     *
     * @param gearItem the GearItem to be removed
     */
    public void removeGearItem(GearItem gearItem) {
        switch (gearItem.getType()) {
            case WEAPON:
                if(this.hasActiveWeapon() && this.getWeapon().equals(gearItem)) {
                    this.deactivateWeapon();
                }
                break;
            case ARMOR:
                if(this.hasActiveArmor() && this.getArmor().equals(gearItem)) {
                    this.removeArmor();
                }
                break;
            case POTION:
                this.removePotion((Potion)gearItem);
                break;
            case SPELL:
                this.removeSpell((Spell)gearItem);
                break;
            default:
                throw new UnknownGearItemTypeException("Unknown type of GearItem: " + gearItem.getType());
        }
    }

    /**
     * Indicates whether or not the passed in GearItem is active
     * @param gearItem the GearItem in question
     * @return true if the GearItem is active, false otherwise.
     */
    public boolean gearItemIsActive(GearItem gearItem) {
        boolean hasItem;
        switch (gearItem.getType()) {
            case WEAPON:
                hasItem = this.hasActiveWeapon() && this.getWeapon().equals(gearItem);
                break;
            case ARMOR:
                hasItem = this.hasActiveArmor() && this.getArmor().equals(gearItem);
                break;
            case POTION:
                hasItem = this.getPotions().contains((Potion)gearItem);
                break;
            case SPELL:
                hasItem = this.getSpells().contains((Spell)gearItem);
                break;
            default:
                throw new UnknownGearItemTypeException("Unknown type of GearItem: " + gearItem.getType());
        }
        return hasItem;
    }


    /**
     * @return String representation of this ActiveGearItems object.
     */
    @Override
    public String toString() {
        return "Active Gear Items: " + this.getWeapon() + ", " + this.getArmor() + ", " + this.getPotions() + ", " + this.getSpells();
    }

    /**
     * Defines equality for two ActiveGearItems objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of ActiveGearItems, they share the same Weapon, Armor, Potions, and Spells.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof ActiveGearItems)) {
            return false;
        }

        ActiveGearItems other = (ActiveGearItems) o;
        return this.getWeapon().equals(other.getWeapon()) &&
                this.getArmor().equals(other.getArmor()) &&
                this.getSpells().equals(other.getSpells()) &&
                this.getPotions().equals(other.getPotions());
    }
}
