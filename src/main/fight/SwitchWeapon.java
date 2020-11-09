package main.fight;

import main.legends.Hero;
import main.legends.Legend;
import main.legends.NonOwnedGearItemException;
import main.market_and_gear.Weapon;
import main.utils.Validations;

/**
 * Class switchWeapon is a type of Internal FightMove where a Hero switches Weapons
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/6/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class SwitchWeapon extends InternalMove {
    private final Weapon toWield;

    /**
     * Standard constructor
     * @param legend the Legend performing this Move
     * @param toWield the Weapon that the Legend is now wielding
     */
    public SwitchWeapon(Legend legend, Weapon toWield) {
        super(legend);
        this.toWield = toWield;
    }

    /**
     * Executes this FightMove
     */
    @Override
    public void execute() throws InvalidFightMoveException, NonOwnedGearItemException {
        Hero hero = (Hero)this.getExecutor();
        Validations.heroIsAlive(hero);
        Validations.heroOwnsGearItem(hero, toWield);

        // This Weapon should not be currently active
        if(hero.getActiveGearItems().getWeapon().equals(toWield)) {
            throw new InvalidFightMoveException("Hero is already wielding this Weapon!");
        }

        Weapon previous = hero.getActiveGearItems().getWeapon();
        hero.getActiveGearItems().activateWeapon(toWield);

        if(previous == null) {
            System.out.println(this.getExecutor().getName() + " is now wielding " + toWield.getName());
        } else {
            System.out.println(this.getExecutor().getName() + " has switched weapons from " + previous.getName() + " to " + toWield.getName());
        }
    }
}
