package test.utils;

import main.legends.Hero;
import main.legends.Monster;
import main.market_and_gear.Armor;
import main.market_and_gear.Weapon;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Class Output
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Output {
    /**
     * Prints out a clear separator, for use when separating between various different sections.
     */
    public static void printSeparator() {
        System.out.println("=====================================================");
    }

    /**
     * Given a list heroes, prints them out to the user in a standard format
     * @param heroes the List of Heroes to print
     */
    public static void printHeroList(List<Hero> heroes) {
        String format = "%-4s%-21s%-15s%-15s%-8s%-8s%-8s%-20s%-20s";
        String header = String.format(format, "Lvl", "Hero Name", "HP", "Mana", "Str", "Agl", "Dxt", "Weapon", "Armor");
        DecimalFormat df = new DecimalFormat("0.0");
        System.out.println(header);
        for(Hero hero : heroes) {
            String hp = df.format(hero.getHealthPower().getHealthPower()) + "/" + df.format(hero.getHealthPower().getFullAmount());
            String mana = df.format(hero.getMana().getManaAmount()) + "/" + df.format(hero.getMana().getFullAmount());
            String weapon = "None";
            if(hero.getActiveGearItems().hasActiveWeapon()) {
                Weapon wielded = hero.getActiveGearItems().getWeapon();
                weapon = wielded.getName() + ":" + wielded.getDamage();
            }
            String armor = "None";
            if(hero.getActiveGearItems().hasActiveArmor()) {
                Armor worn = hero.getActiveGearItems().getArmor();
                armor = worn.getName() + ":" + worn.getDefense();
            }
            String status = String.format(format, hero.getLevel().getLevel(), hero.getName(), hp, mana,
                    hero.getStrength().getAbilityValue(), hero.getAgility().getAbilityValue(), hero.getDexterity().getAbilityValue(),
                    weapon, armor);
            System.out.println(status);
        }
    }

    /**
     * Given a list of Monsters, prints the Monsters to the user in a standard format.
     * @param monsters the List of Monsters to print.
     */
    public static void printMonsters(List<Monster> monsters) {
        DecimalFormat df = new DecimalFormat("0.0");
        String format = "%-4s%-21s%-15s%-8s%-8s%-8s";
        String header = String.format(format, "Lvl", "Monster Name", "HP", "Str", "Def", "Agl");
        System.out.println(header);
        for(Monster monster : monsters) {
            String hp = df.format(monster.getHealthPower().getHealthPower()) + "/" + df.format(monster.getHealthPower().getFullAmount());
            String status = String.format(format, monster.getLevel().getLevel(), monster.getName(), hp,
                    monster.getStrength().getAbilityValue(), monster.getDefense().getAbilityValue(), monster.getAgility().getAbilityValue());
            System.out.println(status);
        }
    }

    /**
     * Prints a new line
     */
    public static void newLine() {
        System.out.println();
    }
}
