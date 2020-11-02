package main.legends;

import main.attributes.Ability;
import main.attributes.HealthPower;
import main.attributes.Level;

/**
 * Class HeroBuilder is a static class that provides helpful methods for building Heroes.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class HeroBuilder {
    public static void initializeHeroAttributes(
            Hero hero, String name, Level level, HealthPower healthPower,
            Ability agility, Ability dexterity, Ability strength) {
        // Set Legend attributes
        hero.setName(name);
        hero.setLevel(level);
        hero.setHealthPower(healthPower);

        // Set Hero Abilities
        hero.setAgility(agility);
        hero.setDexterity(dexterity);
        hero.setStrength(strength);
    }
}
