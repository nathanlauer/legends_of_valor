package main.legends;

import main.attributes.Ability;
import main.attributes.HealthPower;
import main.attributes.Level;
import main.world.Position;
import main.utils.Outputable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Abstract Class Legend sits at the top of the inheritance hierarchy for all heroes and monsters.
 * The idea is that heroes and monsters share some common characteristics, such as health power,
 * name, and a level. In addition, it is a useful abstraction to group all "playing entities" within
 * this game into one hierarchy, which allows for common collections to be used in a number of other
 * places.
 *
 * Every Legend has at least four Abilities: HealthPower, Strength (used for attacks),
 * Defense, Agility (used for dodge chance). Subclasses may have further abilities.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class Legend extends Outputable implements Cloneable {
	private Position position;//each legend has a position on map.
    private String name;
    private final Level level;
    private final HealthPower healthPower;

    private final List<Ability> abilities;
    private final Ability strength; // amount of damage a Monster does
    private final Ability defense;
    private final Ability agility; // dodge chance

    /**
     * Standard constructor for a Legend.
     * @param name Name of this Legend
     * @param level Level of this Legend
     * @param healthPower HealthPower of this Legend
     */
    public Legend(String name, Level level, HealthPower healthPower, Ability strength, Ability defense, Ability agility) {
        this.name = name;
        this.level = level;
        this.healthPower = healthPower;
        this.strength = strength;
        this.defense = defense;
        this.agility = agility;
        abilities = new ArrayList<>(Arrays.asList(this.healthPower, this.strength, this.defense, this.agility));
        position = new Position();
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the name of this Legend
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the Level of this Legend
     */
    public Level getLevel() {
        return level;
    }

    /**
     *
     * @return the healthPower of this Legend
     */
    public HealthPower getHealthPower() {
        return healthPower;
    }

    /**
     *
     * @return the Strength of this Legend
     */
    public Ability getStrength() {
        return strength;
    }

    /**
     *
     * @return the Defense of this Legend
     */
    public Ability getDefense() {
        return defense;
    }

    /**
     *
     * @return the Agility of this Legend
     */
    public Ability getAgility() {
        return agility;
    }
    /**
     * 
     * get the position of the legend on map
     */
    public Position getPosition() {
		return position;
    	
    }

    /**
     * Adds the passed in Ability to the List of Abilities for this Legend
     * @param ability the Ability to be added
     */
    public void addAbility(Ability ability) {
        this.abilities.add(ability);
    }

    /**
     *
     * @return a List of all the abilities for this Legend
     */
    public List<Ability> getAbilities() {
        return abilities;
    }

    /**
     * Calculates the amount of Damage this Legend would cause if it attacked.
     * Note: this is not just the Strength ability - a Legend may wield some
     * other GearItem which increases their damage amount.
     * @return the amount of Damage in an attack
     */
    public abstract double getDamageAmount();

    /**
     * Calculates the amount of Defense this Legend has if it were attacked.
     * Note: this is not just the Defense Ability - a legend may have some
     * GearItem which increases their defense amount.
     * @return the Defense amount
     */
    public abstract double getDefenseAmount();

    /**
     * Calculates the chance of dodging an attack for this Legend.
     * This is a probability, so it is normalized to range [0,1]
     * @return the likelihood of dodging an attack.
     */
    public abstract double getDodgeChance();

    /**
     * Given the passed in Ability, find the Ability of this Legend that
     * matches the same type. For example, if ability is Defense, then
     * this method returns the Defense Ability of this Legend.
     *
     * If the relevant Ability is not found, null is returned.
     *
     * @param abilityToMatch the Ability to match
     * @return the matching Ability type of this Legend.
     */
    public Ability matchAbility(Ability abilityToMatch) {
        for(Ability ability : this.getAbilities()) {
            if(ability.getType().equals(abilityToMatch.getType())) {
                return ability;
            }
        }
        return null;
    }

    /**
     * Handles the logic for when a Legend is attacked. The value passed in is
     * the total amount of damage theoretically caused. Ignoring other semantics
     * for a second, the passed in damage times 0.05 is deducted from this
     * Legend's HealthPower.
     *
     * There are two caveats:
     * 1) A Legend has some defense amount - that defense is subtracted from the
     * total amount, before multiplying be 0.05.
     * 2) A Legend has a certain dodge chance, based on their Agility Ability.
     * If the Legend successfully dodges the attack, then no HealthPower is
     * removed.
     *
     * @param damage total damage the attacker is inflicting
     * @return a String indicating the result of what happened
     */
    public String wasAttacked(double damage) {
        double defense = this.getDefenseAmount();
        double totalDamage = damage - defense;
        if(totalDamage <= 0) {
            return "No damage done: " + this.getName() + "'s defense of " + this.getDefense().getAbilityValue() + " is greater than the " + damage + " inflicted damage.";
        }

        double inflictedDamage = totalDamage * 0.05;
        double dodgeChance = this.getDodgeChance();
        int rand = new Random().nextInt(100) + 1;
        if(rand > dodgeChance * 100.0) {
            this.getHealthPower().reduceHealthPowerBy(inflictedDamage);
            return "Attack successful! " + this.getName() + " lost " + inflictedDamage + " health power.";
        } else {
            return this.getName() + " dodged the attack!";
        }
    }

    /**
     *
     * @return String representation of this Legend
     */
    @Override
    public String toString() {
        return "Legend: " + getName() + ", " + this.getLevel().toString();
    }

    /**
     * Overrides default implementation of equality. Two main.legends are equal
     * if they have the same name, the same level, and the same health power.
     * @param o Other object in question
     * @return true if the two objects are equal according to the above definition,
     * false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }

        if(!(o instanceof Legend)) {
            return false;
        }

        Legend other = (Legend)o;
        return other.getName().equals(this.getName()) &&
                other.getLevel().equals(this.getLevel()) &&
                other.getHealthPower().equals(this.getHealthPower()) &&
                other.getAbilities().equals(this.getAbilities());
    }

    /**
     *
     * @return a cloned copy of this Legend
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
