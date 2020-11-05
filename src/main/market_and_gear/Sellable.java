package main.market_and_gear;

import main.legends.Hero;
import main.legends.NonOwnedGearItemException;

/**
 * Interface Sellable describes an entity that can be sold. In particular,
 * in the context of this game, we define Sellable as with regards to a
 * Market, meaning that a Hero can sell such an entity to a Market.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/4/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface Sellable {
    void sell(Market market, Hero hero) throws NonOwnedGearItemException;
}
