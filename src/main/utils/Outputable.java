package main.utils;

/**
 * Abstract class Outputable defines the behavior of an entity that can be output to standard out
 * in some specific format.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public abstract class Outputable {
    /**
     *
     * @return the format string which can be used to output all GearItems of this type
     */
    public abstract String getOutputFormat();

    /**
     *
     * @return the Header string that can be used to print out the relevant GearItems.
     */
    public abstract String getHeaderString();

    /**
     *
     * @return String representation of this Outputable
     */
    public abstract String toString();
}
