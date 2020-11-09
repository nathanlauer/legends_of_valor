package main.fight;

import main.legends.Legend;
import main.legends.NonOwnedGearItemException;
import main.utils.Validations;

import java.util.List;

/**
 * Class Attack is a type of External FightMove where one Legend attacks another
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/6/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Attack extends ExternalMove {
    /**
     * Standard constructor
     * @param attacker Legend acting as the attacker
     * @param defenders Legends who are being attacked
     */
    public Attack(Legend attacker, List<Legend> defenders) {
        super(attacker, defenders);
    }

    /**
     * Executes this FightMove
     */
    @Override
    public void execute() throws InvalidFightMoveException, NonOwnedGearItemException {
        // Both Legends must be alive
        Validations.legendIsAlive(this.getExecutor());
        for(Legend legend : this.getReceivers()) {
            Validations.legendIsAlive(legend);
        }

        // The amount of damage done differs depending on the type of Legend. Split
        // it amongst each of the receivers
        double damagePerReceiver = this.getDamagePerReceiver();
        for(Legend legend : this.getReceivers()) {
            String result = legend.wasAttacked(damagePerReceiver);
            System.out.println(result);
        }
    }

    /**
     *
     * @return the amount of damage this Attack move deals (not including receiver's defense)
     * per each receiver.
     */
    public double getDamagePerReceiver() {
        double damage = this.getExecutor().getDamageAmount();
        return damage / this.getReceivers().size();
    }
}
