package main.gearItems;

/**
 * Class GearItem is an abstract class that sits at the top of the Gear hierarchy.
 * It represents an item that can be used in the game.
 *
 * GearItems are both Sellable and Buyable: they can be sold from a Legend
 * to the Market, and they can be bought from the Market to a Hero, respectively.
 *
 * Every GearItem has a name, a price, and a minimum level. Legends with levels that
 * are less than an item's min level cannot be used or bought by a Legend.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class GearItem {

    /**
     * @return String representation of this GearItem object.
     */
    @Override
    public String toString() {
        // TODO
    }

    /**
     * Defines equality for two GearItem objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of GearItem, and TODO
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof GearItem)) {
            return false;
        }

        GearItem other = (GearItem) o;
        // TODO    
    }
}
