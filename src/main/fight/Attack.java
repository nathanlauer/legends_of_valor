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

        // The amount of damage done differs depending on the type of Legend
        int damage = this.getExecutor().getDamageAmount();

        // Split that damage equally towards each of the receivers
        int damagePerReceiver = damage / this.getReceivers().size();
        for(Legend legend : this.getReceivers()) {
            legend.wasAttacked(damagePerReceiver);
        }
    }
}
