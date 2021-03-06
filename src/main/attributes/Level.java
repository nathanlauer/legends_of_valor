package main.attributes;

import main.utils.Validations;

/**
 * Class Level represents the advancement or progression of a Legend. It can be thought of
 * as how strong an ability a Legend has. For example, a Legend with a low Level can be
 * considered an amateur (or acolyte or novice), while a Legend with a high Level can
 * be considered something like a master.
 *
 * This class is basically a wrapper around an int. It is extracted out as a class, for
 * two reasons:
 * 1) There is some logic associated with incrementing levels.
 * 2) It is inherently independent of a Legend - it is conceivable that this class
 * can be used in other contexts. In this repository, we really only use it with
 * Legends, but that doesn't change the idea that it is fundamentally independent
 * of a Legend.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Level implements Comparable<Level>, Cloneable{
    private int level;

    /**
     * Empty constructor, sets this Level to zero.
     */
    public Level() {
        this(0);
    }

    /**
     * Constructs a new Level, and sets the level to the passed in value.
     * Throws an IllegalArgumentException if level is negative.
     * @param level the level to set
     */
    public Level(int level) {
        Validations.nonNegative(level, "level");
        this.level = level;
    }

    /**
     * Increments the level of this Legend by one.
     */
    public void incrementLevel() {
        this.setLevel(this.getLevel() + 1);
    }

    /**
     * Sets the level for this Legend.
     * Throws an IllegalArgumentException if level is less than zero
     * @param newLevel new level for this Legend
     */
    public void setLevel(int newLevel) {
        Validations.nonNegative(newLevel, "newLevel");
        level = newLevel;
    }

    /**
     *
     * @return the level of this Legend
     */
    public int getLevel() {
        return level;
    }

    /**
     * Helper method which indicates if this level is less than the passed in level.
     * A Level is less than another if it's level is lower.
     * @param other the other Level in question
     * @return true if this level is less than other, false otherwise.
     */
    public boolean isLessThan(Level other) {
        return this.compareTo(other) < 0;
    }

    /**
     * Helper method which indicates if this level is less than or equal to the passed in level.
     * A Level is less than another if it's level is lower.
     * A Level is equal to the other if it's level is the same.
     * @param other the other Level in question
     * @return true if this level is less than or equal to other, false otherwise.
     */
    public boolean isLessThanOrEqual(Level other) {
        int compare = this.compareTo(other);
        return compare <= 0 ;
    }

    /**
     *
     * @return a String representation of this Level
     */
    @Override
    public String toString() {
        return String.valueOf(getLevel());
    }

    /**
     * Defines equality for Levels: two Levels are equal if they have the same level.
     * @param o other Object in consideration
     * @return true if o is a Level and has the same level as this, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }

        if(!(o instanceof Level)) {
            return false;
        }

        Level other = (Level)o;
        return other.getLevel() == this.getLevel();
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Level o) {
        return this.getLevel() - o.getLevel();
    }

    /**
     *
     * @return a cloned copy of this Level
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
