package main.fight;

import main.legends.Hero;
import main.legends.Legend;
import main.legends.NonOwnedGearItemException;
import main.market_and_gear.Armor;
import main.utils.Validations;

/**
 * Class SwitchArmor is a type of Internal FightMove where the Hero switches
 * their Armor.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/6/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class SwitchArmor extends InternalMove {
    private final Armor toPutOn;

    /**
     * Standard constructor
     * @param legend Legend that is making this Move
     * @param toPutOn the Armor that the Legend wants to put on
     */
    public SwitchArmor(Legend legend, Armor toPutOn) {
        super(legend);
        this.toPutOn = toPutOn;
    }


    /**
     * Executes this FightMove
     */
    @Override
    public void execute() throws InvalidFightMoveException, NonOwnedGearItemException {
        Hero hero = (Hero)this.getExecutor();
        Validations.heroIsAlive(hero);
        Validations.heroOwnsGearItem(hero, toPutOn);

        // This Armor should not be currently active (otherwise it's not being put on!)
        if(hero.getActiveGearItems().getArmor().equals(toPutOn)) {
            throw new InvalidFightMoveException("Hero is already wearing this Armor!");
        }

        Armor previous = hero.getActiveGearItems().getArmor();
        hero.getActiveGearItems().putOnArmor(toPutOn);

        if(previous == null) {
            System.out.println(this.getExecutor().getName() + " is now wearing the " + toPutOn.getName() + " armor");
        } else {
            System.out.println(this.getExecutor().getName() + " has switched armor from " + previous.getName() + " to " + toPutOn.getName());
        }
    }
}
