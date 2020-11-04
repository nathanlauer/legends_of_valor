package main.market_and_gear;

import main.utils.Coffer;

import java.util.List;

/**
 * Class Market is a class that represents a place in the game where Heroes
 * can buy and sell GearItems. Within a Market, there are four general
 * types of GearItems that can be purchased or sold: Weapons, Armor,
 * Potions, and Spells. Every GearItem has an associated price, and when
 * purchased, that amount of coins are extracted from the relevant Hero's
 * Coffer.
 *
 * Every Market has a subset of the total GearItems available. In other words,
 * there exists a MarketInventory which contains all possible GearItems. Then,
 * an individual Market will contain a random percentage of those items. That
 * percentage is a random value between 30% and 80% (Note: I've chosen this
 * arbitrarily, just to simulate some variation in available items between Markets).
 *
 * Buying GearItems: When an GearItem is bought from the Market, coins are transferred
 * from a Hero to the Market, and the Market transfers the GearItem to the Hero. That
 * GearItem will no longer be available for purchase.
 *
 * Selling GearItems: Heroes can choose to sell GearItems they currently own
 * back to the Market. When doing so, every Market will pay 50% of the GearItem's
 * original price. Note: as coins can't be split in two in this game, if a
 * GearItem was originally priced with an odd number, then Market will buy it
 * back at the ceiling of price/2. Once a GearItem has been sold back to the
 * Market, it becomes available again for purchase -- even to the Hero that
 * sold said item.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/3/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Market {
    private final List<GearItem> gearItems;
    private final Coffer coffer;

    /**
     * The only constructor available for a Market. We structure this class in
     * this fashion to control which GearItems are initially available to the
     * Market (see notes above). Additionally, we also want to guarantee that
     * the Market has a Coffer with enough money to allow Heroes to sell their
     * GearItems back to a Market.
     */
    public Market() {
        GearItemFactoryCreator factory = new RandomGearItemFactory();
        gearItems = factory.createGearItems();
        int numCoins = GearItem.mostExpensiveGearItem * 5; // Markets should have plenty of coins for this Game.
        coffer = new Coffer(numCoins);
    }

//    /**
//     * @return String representation of this Market object.
//     */
//    @Override
//    public String toString() {
//        // TODO
//    }
//
//    /**
//     * Defines equality for two Market objects.
//     *
//     * @param o Other object in consideration for equality
//     * @return true if o is an instance of Market, and TODO
//     */
//    @Override
//    public boolean equals(Object o) {
//        if (o == null) {
//            return false;
//        }
//
//        if (!(o instanceof Market)) {
//            return false;
//        }
//
//        Market other = (Market) o;
//        // TODO
//    }
}
