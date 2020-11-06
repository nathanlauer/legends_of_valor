package main.utils;

import main.fight.InvalidFightMoveException;
import main.legends.Hero;
import main.legends.NonOwnedGearItemException;
import main.market_and_gear.GearItem;
import main.market_and_gear.Potion;

/**
 * Class Validations is a static class that offers a couple helper methods,
 * which throw IllegalArgumentException if a relevant condition fails.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Validations {
    /**
     * Throws an IllegalArgumentException if value is negative.
     * @param value the value in question
     * @param name variable name that wraps value
     */
    public static void nonNegative(int value, String name) {
        if(value < 0) {
            throw new IllegalArgumentException(name + " can't be negative!");
        }
    }

    /**
     * Throws a NonOwnedGearItemException if the passed in GearItem is
     * not owned by the passed in Hero
     * @param hero the Hero in question
     * @param gearItem the GearItem in question
     */
    public static void heroOwnsGearItem(Hero hero, GearItem gearItem) {
        if(!hero.getGearItemList().containsGearItem(gearItem)) {
            throw new NonOwnedGearItemException("Attempted to activate GearItem that is not owned!");
        }
    }

    /**
     * Throws an exception if the passed in Hero has fainted
     * @param hero the Hero in question
     * @throws InvalidFightMoveException if the Hero has fainted
     */
    public static void heroIsAlive(Hero hero) throws InvalidFightMoveException {
        if(hero.hasFainted()) {
            throw new InvalidFightMoveException("Hero has fainted!");
        }
    }

    /**
     * Throws an exception if the passed in Potion was already used
     * @param potion the Potion in question
     * @throws InvalidFightMoveException if the Potion was already used
     */
    public static void potionUnused(Potion potion) throws InvalidFightMoveException {
        if(potion.wasUsed()) {
            throw new InvalidFightMoveException("This Potion was already used!");
        }
    }
}
