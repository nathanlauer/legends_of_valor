package main.market_and_gear;

import main.attributes.Ability;
import main.attributes.Level;
import main.utils.Validations;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Potion is a concrete type of GearItem. In addition to a name, price, and
 * minimum level, it also can increase an Ability by a certain amount, and can only
 * be used once.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Potion extends GearItem {
    public static final String outputFormat = "%-4s%-21s%-6s%-5s%-8s%-12s";
    public static final String defaultName = "Potion";
    private List<Ability> abilities;
    private double incrementAmount;
    private boolean used;

    /**
     * Empty constructor. Sets the name to Potion, and everything else
     * to zero values.
     */
    public Potion() {
        this(Potion.defaultName, 0, new Level(0), Ability.emptyAbilityList(), 0);
    }

    /**
     * Standard constructor for a Potion.
     * Throws an IllegalArgumentException if incrementAmount is negative.
     *
     * By default, a Potion has not been used.
     *
     * @param name name of this GearItem
     * @param price price of this GearItem
     * @param minLevel min Level of this GearItem
     * @param abilities Abilities this Potion affects
     * @param incrementAmount amount to increment ability of this Potion
     */
    public Potion(String name, int price, Level minLevel, List<Ability> abilities, double incrementAmount) {
        super(name, price, minLevel);

        Validations.nonNegative(incrementAmount, "incrementAmount");
        this.abilities = new ArrayList<>(abilities);
        this.incrementAmount = incrementAmount;
        this.used = false;
    }

    /**
     *
     * @return the Abilities associated with this Potion
     */
    public List<Ability> getAbilities() {
        return abilities;
    }

    /**
     * Sets the abilities of this Potion to the passed in List of Abilities.
     * @param abilities new List of Abilities for this Potion
     */
    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    /**
     * Adds the passed in Ability to this Potion
     * @param ability the Ability to add
     */
    public void addAbility(Ability ability) {
        abilities.add(ability);
    }

    /**
     * Removes the passed in Ability from the list of Abilities for this Potion
     * @param ability Ability to be removed
     */
    public void removeAbility(Ability ability) {
        abilities.remove(ability);
    }

    /**
     *
     * @return the amount to increment the associated Ability by
     */
    public double getIncrementAmount() {
        return incrementAmount;
    }

    /**
     * Sets the increment amount for this Potion
     * Throws an IllegalArgumentException if newAmount is negative
     * @param newAmount the new amount that this Potion will increase its Ability.
     */
    public void setIncrementAmount(double newAmount) {
        Validations.nonNegative(newAmount, "newAmount");
        incrementAmount = newAmount;
    }

    /**
     * Indicates whether or not this Potion was used.
     * @return true if this Potion was used, false otherwise
     */
    public boolean wasUsed() {
        return used;
    }

    /**
     * Sets whether or not this Potion was used.
     * @param newUsed whether or not this Potion was used
     */
    public void setUsed(boolean newUsed) {
        used = newUsed;
    }

    /**
     * Convenient helper method which marks this Potion as having been used.
     */
    public void markUsed() {
        this.setUsed(true);
    }

    /**
     * Indicates whether or not this Potion can still be used.
     * @return true if this Potion was not used yet, false otherwise
     */
    public boolean canBeUsed() {
        return !wasUsed();
    }

    /**
     *
     * @return the List of Abilities that this Potion affects as a String, separated by "/"
     */
    public String getAbilitiesAsString() {
        StringBuilder abilities = new StringBuilder();
        for(int i = 0; i < this.getAbilities().size(); i++) {
            Ability ability = this.getAbilities().get(i);
            abilities.append(ability.getType());
            if(i < this.getAbilities().size() - 1) {
                abilities.append("/");
            }
        }
        return abilities.toString();
    }

    /**
     * @return the Type of this Gear Item
     */
    @Override
    public GearItemType getType() {
        return GearItemType.POTION;
    }

    /**
     * @return the format string which can be used to output all GearItems of this type
     */
    @Override
    public String getOutputFormat() {
        return Potion.outputFormat;
    }

    /**
     * @return the Header string that can be used to print out the relevant GearItems.
     */
    @Override
    public String getHeaderString() {
        return String.format(getOutputFormat(), "Lvl", "Name", "Price", "Used", "IncAmt", "Ablts");
    }

    /**
     * @return String representation of this Potion object.
     */
    @Override
    public String toString() {
        String used = wasUsed() ? "Yes" : "No";
        String abilities = getAbilitiesAsString();
        return String.format(getOutputFormat(), getMinLevel(), getName(), getPrice(), used, getIncrementAmount(), abilities);
    }

    /**
     * Defines equality for two Potion objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Potion, they share the same Potion attributes, and the
     * same GearItem attributes.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Potion)) {
            return false;
        }

        Potion other = (Potion) o;
        return this.getAbilities().equals(other.getAbilities()) &&
                this.getIncrementAmount() == other.getIncrementAmount() &&
                this.wasUsed() == other.wasUsed() &&
                super.equals(o);
    }
}
