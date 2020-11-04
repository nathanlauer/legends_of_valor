package main.market_and_gear;

import java.util.List;

/**
 * Interface GearItemFactoryCreator defines the Factory interface for creating GearItems.
 * Concrete implementing classes are factories which produce GearItems.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/3/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface GearItemFactoryCreator {
    List<GearItem> createGearItems();
}
