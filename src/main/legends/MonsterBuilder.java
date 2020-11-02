package main.legends;

import main.attributes.HealthPower;
import main.attributes.Level;
import main.utils.Validations;

/**
 * Class MonsterBuilder is a static class that provides some helpful methods
 * for constructing a Monster.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class MonsterBuilder {
    /** Given a monster, initializes each of its attributes to the passed in values.
     *
     * @param monster Monster in question
     * @param name Name of this Monster
     * @param level Level for this Monster
     * @param healthPower HealthPower for this Monster
     * @param attack attack value for this Monster. Can't be negative.
     * @param defense defense value for this Monster. Can't be negative.
     * @param dodgeChance dodgeChance value for this Monster. Must be a percentage. Valid range: [0, 1]
     */
    public static void initializeMonsterAttributes(
            Monster monster, String name, Level level, HealthPower healthPower,
            double attack, double defense, double dodgeChance) {

        // Validate input
        Validations.nonNegative(attack, "attack");
        Validations.nonNegative(defense, "defense");
        Validations.percentage(dodgeChance, "dodgeChance");

        // Set Legend attributes
        monster.setName(name);
        monster.setLevel(level);
        monster.setHealthPower(healthPower);

        // Set Monster attributes
        monster.setAttackDamage(attack);
        monster.setDefense(defense);
        monster.setDodgeChance(dodgeChance);
    }
}
