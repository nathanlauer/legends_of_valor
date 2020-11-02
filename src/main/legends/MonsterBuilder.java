package main.legends;

import main.attributes.Ability;
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
     * @param damage damage Ability for this Monster. Can't be negative.
     * @param defense defense Ability for this Monster. Can't be negative.
     * @param dodgeChance dodgeChance Ability for this Monster. Can't be negative
     */
    public static void initializeMonsterAttributes(
            Monster monster, String name, Level level, HealthPower healthPower,
            Ability damage, Ability defense, Ability dodgeChance) {

        // Set Legend attributes
        monster.setName(name);
        monster.setLevel(level);
        monster.setHealthPower(healthPower);

        // Set Monster attributes
        monster.setDamage(damage);
        monster.setDefense(defense);
        monster.setDodgeChance(dodgeChance);
    }
}
