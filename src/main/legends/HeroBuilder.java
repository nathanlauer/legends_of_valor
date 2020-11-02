package main.legends;

import main.attributes.Ability;
import main.attributes.HealthPower;
import main.attributes.Level;
import main.attributes.Mana;
import main.utils.Coffer;

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
            Mana mana, Coffer coffer,
            Ability agility, Ability dexterity, Ability strength) {
        // Set Legend attributes
        hero.setName(name);
        hero.setLevel(level);
        hero.setHealthPower(healthPower);

        // Set Hero Abilities
        hero.setAgility(agility);
        hero.setDexterity(dexterity);
        hero.setStrength(strength);
        hero.setCoffer(coffer);
        hero.setMana(mana);

        // By default, a hero has not fainted
        hero.setFainted(Hero.defaultFainted);
    }
}
