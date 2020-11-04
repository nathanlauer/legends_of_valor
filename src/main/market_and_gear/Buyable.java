package main.market_and_gear;

import main.legends.Hero;
import main.legends.NonOwnedGearItemException;
import main.utils.BeneathLevelException;
import main.utils.NotEnoughCoinsException;

/**
 * Interface Buyable describes a type of entity that can be bought.
 * Specifically, in this game, we define Buyable in the context of
 * a Market: meaning an item that can be obtained from the Market
 * in exchange for coins.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/3/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public interface Buyable {
    void buy(Market market, Hero hero) throws NonOwnedGearItemException, NotEnoughCoinsException, BeneathLevelException;
}
