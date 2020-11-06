package main.legends;

import main.attributes.Ability;
import main.attributes.HealthPower;
import main.attributes.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Class Legend sits at the top of the inheritance hierarchy for all heroes and monsters.
 * The idea is that heroes and monsters share some common characteristics, such as health power,
 * name, and a level. In addition, it is a useful abstraction to group all "playing entities" within
 * this game into one hierarchy, which allows for common collections to be used in a number of other
 * places.
 *
 * Every Legend has at least two Abilities: HealthPower and Defense. Subclasses of Legends
 * have further Abilities.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class Legend {
    private String name;
    private Level level;
    private HealthPower healthPower;
    private final List<Ability> abilities;

    /**
     * Standard constructor for a Legend.
     * @param name Name of this Legend
     * @param level Level of this Legend
     * @param healthPower HealthPower of this Legend
     */
    public Legend(String name, Level level, HealthPower healthPower) {
        this.name = name;
        this.level = level;
        this.healthPower = healthPower;
        abilities = new ArrayList<>();
        // TODO: use enum ability type instead of name.
        // Then, list of abilities by type
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
     * Sets the Level of this Legend to the passed in value
     * @param level level for this Legend
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     *
     * @return the healthPower of this Legend
     */
    public HealthPower getHealthPower() {
        return healthPower;
    }

    /**
     * Sets the healthPower for this Legend to the passed in value.
     * @param healthPower the new healthPower for this Legend
     */
    public void setHealthPower(HealthPower healthPower) {
        this.healthPower = healthPower;
    }

    /**
     *
     * @return String representation of this Legend
     */
    @Override
    public String toString() {
        return "Legend: " + getName() + ", " + this.getLevel().toString() + ", " + this.getHealthPower().toString();
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
                other.getHealthPower().equals(this.getHealthPower());
    }
}
