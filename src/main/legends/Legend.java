package main.legends;

import main.attributes.HealthPower;
import main.attributes.Level;

/**
 * Abstract Class Legend sits at the top of the inheritance hierarchy for all heroes and monsters.
 * The idea is that heroes and monsters share some common characteristics, such as health power,
 * name, and a level. In addition, it is a useful abstraction to group all "playing entities" within
 * this game into one hierarchy, which allows for common collections to be used in a number of other
 * places.
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
