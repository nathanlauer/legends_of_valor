package main.legends;

import main.market_and_gear.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class GearItemList is a class that contains the various GearItems held by a Hero.
 * To be clear, this class is actually just a list of GearItems, and can be
 * theoretically held by any entity. Nonetheless, within the context of this
 * game, a GearList instance is expected to be held by a Hero.
 *
 * This class is responsible only for maintaining a list of GearItems that belong
 * to a Hero. It is not responsible for tracking which GearItems are active or
 * inactive.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/4/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class GearItemList {
    private final List<GearItem> gearItems;

    /**
     * Empty constructor. Creates a new GearItemList with no GearItems
     */
    public GearItemList() {
        this(new ArrayList<>());
    }

    /**
     * Standard constructor for a GearItemList.
     * @param gearItems List of GearItems to initialize with
     */
    public GearItemList(List<GearItem> gearItems) {
        this.gearItems = new ArrayList<>(gearItems);
    }

    /**
     *
     * @return all GearItems contained
     */
    public List<GearItem> getGearItems() {
        return gearItems;
    }

    /**
     * Indicates whether or not the passed in GearItem is contained in this List
     * @param item the GearItem in question
     * @return true if the item is contained, false otherwise
     */
    public boolean containsGearItem(GearItem item) {
        return gearItems.contains(item);
    }

    /**
     * Adds the passed in GearItem to the contained List.
     * @param item the GearItem to be added
     */
    public void addGearItem(GearItem item) {
        gearItems.add(item);
    }

    /**
     * Removes the passed in GearItem from the contained List.
     * @param item the GearItem to be removed.
     */
    public void removeGearItem(GearItem item) {
        gearItems.remove(item);
    }

    /**
     *
     * @return a List of all the Weapons in this GearItemList
     */
    public List<GearItem> getWeapons() {
        List<GearItem> output = new ArrayList<>();
        for(GearItem item : getGearItems()) {
            if(item instanceof Weapon) {
                output.add((Weapon)item);
            }
        }
        return output;
    }

    /**
     *
     * @return a List of all the Armor in this GearItemList
     */
    public List<GearItem> getArmor() {
        List<GearItem> output = new ArrayList<>();
        for(GearItem item : getGearItems()) {
            if(item instanceof Armor) {
                output.add((Armor) item);
            }
        }
        return output;
    }

    /**
     *
     * @return a List of all the Spells in this GearItemList
     */
    public List<GearItem> getSpells() {
        List<GearItem> output = new ArrayList<>();
        for(GearItem item : getGearItems()) {
            if(item instanceof Spell) {
                output.add(item);
            }
        }
        return output;
    }

    /**
     * @param hero the relevant Hero
     * @return a List of spells that the passed in Hero has enough Mana to use
     */
    public List<GearItem> getUsableSpells(Hero hero) {
        List<GearItem> output = new ArrayList<>();
        for(GearItem item : getGearItems()) {
            if(item instanceof Spell) {
                Spell spell = (Spell)item;
                if(spell.heroHasEnoughMana(hero)) {
                    output.add(item);
                }
            }
        }
        return output;
    }

    /**
     *
     * @param hero the relevant Hero
     * @return a List of spells that the passed in Hero does not have enough Mana to use
     */
    public List<GearItem> getNonUsableSpells(Hero hero) {
        List<GearItem> allSpells = this.getSpells();
        List<GearItem> usableSpells = this.getUsableSpells(hero);
        for(GearItem item : usableSpells) {
            allSpells.remove(item);
        }
        return allSpells;
    }

    /**
     *
     * @return a List of all the Potions in this GearItemList
     */
    public List<GearItem> getPotions() {
        List<GearItem> output = new ArrayList<>();
        for(GearItem item : getGearItems()) {
            if(item instanceof Potion) {
                output.add(item);
            }
        }
        return output;
    }

    /**
     *
     * @return a List of all the Potions in this GearItemList, that have not been used
     */
    public List<GearItem> getUsablePotions() {
        List<GearItem> output = new ArrayList<>();
        for(GearItem item : getGearItems()) {
            if(item instanceof Potion) {
                Potion potion = (Potion)item;
                if(!potion.wasUsed()) {
                    output.add((Potion) item);
                }
            }
        }
        return output;
    }

    /**
     * @return String representation of this GearList object.
     */
    @Override
    public String toString() {
        return "GearItemList with gear items: " + getGearItems();
    }

    /**
     * Defines equality for two GearList objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of GearList, and they have the same list of GearItems.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof GearItemList)) {
            return false;
        }

        GearItemList other = (GearItemList) o;
        return this.getGearItems().equals(other.getGearItems());
    }
}
