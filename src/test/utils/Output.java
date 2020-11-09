package test.utils;

import main.attributes.Ability;
import main.legends.Hero;
import main.legends.Monster;
import main.market_and_gear.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
        List<String> asStrings = Output.printHeroListAsStrings(heroes);
        asStrings.forEach(System.out::println);
    }

    /**
     * Returns a List of strings that would be printed to standard out as represented
     * by the List of passed in Heroes.
     * @param heroes the Heroes that would be printed
     */
    public static List<String> printHeroListAsStrings(List<Hero> heroes) {
        List<String> output = new ArrayList<>();
        String format = "%-4s%-21s%-15s%-15s%-6s%-8s%-8s%-8s%-20s%-20s";
        String header = String.format(format, "Lvl", "Hero Name", "HP", "Mana", "Coins", "Str", "Agl", "Dxt", "Weapon", "Armor");
        DecimalFormat df = new DecimalFormat("0.0");
        output.add(header);
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
            String status = String.format(format, hero.getLevel().getLevel(), hero.getName(), hp, mana, hero.getCoffer().getNumCoins(),
                    hero.getStrength().getAbilityValue(), hero.getAgility().getAbilityValue(), hero.getDexterity().getAbilityValue(),
                    weapon, armor);
            output.add(status);
        }
        return output;
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

    /**
     * Prints each of the Weapons out in a standard format
     * @param weapons the List of Weapons to print
     */
    public static void printWeapons(List<GearItem> weapons) {
        List<String> asStrings = Output.printWeaponsAsStrings(weapons);
        asStrings.forEach(System.out::println);
    }

    /**
     * Returns a List of Strings that may be printed to standard out,
     * reflecting the passed in Weapons
     *
     * @param weapons the relevant Weapons
     */
    public static List<String> printWeaponsAsStrings(List<GearItem> weapons) {
        List<String> output = new ArrayList<>();
        output.add("Weapons:");
        String format = "%-4s%-21s%-6s%-8s%-3s";
        String header = String.format(format, "Lvl", "Name", "Price", "Dmg", "Hands");
        output.add(header);
        for(GearItem item : weapons) {
            Weapon weapon = (Weapon)item;
            String status = String.format(format, weapon.getMinLevel().getLevel(), weapon.getName(), weapon.getPrice(), weapon.getDamage(), weapon.getNumHands());
            output.add(status);
        }
        return output;
    }

    /**
     * Prints each of the Armors out in a standard format.
     * @param armors the List of Armor to print
     */
    public static void printArmor(List<GearItem> armors) {
        List<String> asStrings = Output.printArmorAsStrings(armors);
        asStrings.forEach(System.out::println);
    }

    /**
     * Returns a List of Strings that may be printed to standard out,
     * reflecting the passed in Armors
     *
     * @param armors the relevant Armors
     */
    public static List<String> printArmorAsStrings(List<GearItem> armors) {
        List<String> output = new ArrayList<>();
        output.add("Armor:");
        String format = "%-4s%-21s%-6s%-5s";
        String header = String.format(format, "Lvl", "Name", "Price", "Def");
        output.add(header);
        for(GearItem item : armors) {
            Armor armor = (Armor)item;
            String status = String.format(format, armor.getMinLevel().getLevel(), armor.getName(), armor.getPrice(), armor.getDefense());
            output.add(status);
        }
        return output;
    }

    /**
     * Prints each of the passed in Spells in a standard format.
     * @param spells The List of Spells to output
     */
    public static void printSpells(List<GearItem> spells) {
        List<String> asStrings = Output.printSpellsAsStrings(spells);
        asStrings.forEach(System.out::println);
    }

    /**
     * Returns a List of Strings that may be printed to standard out,
     * reflecting the passed in Spells
     *
     * @param spells the relevant Spells
     */
    public static List<String> printSpellsAsStrings(List<GearItem> spells) {
        List<String> output = new ArrayList<>();
        output.add("Spells:");
        String format = "%-4s%-21s%-6s%-8s%-8s%-12s";
        String header = String.format(format, "Lvl", "Name", "Price", "Mana", "Dmg", "Ablt");
        output.add(header);
        for(GearItem item : spells) {
            Spell spell = (Spell)item;
            String status = String.format(format, spell.getMinLevel().getLevel(), spell.getName(), spell.getPrice(),
                    spell.getMana().getManaAmount(), spell.getDamage(), spell.getAbility().getType().getName());
            output.add(status);
        }
        return output;
    }

    /**
     * Prints each of the passed in Potions in a standard format.
     * @param potions the List of Potions to output
     */
    public static void printPotions(List<GearItem> potions) {
        List<String> toPrint = Output.printPotionsAsStrings(potions);
        toPrint.forEach(System.out::println);
    }

    /**
     * Returns a List of Strings that may be printed to standard out,
     * reflecting the passed in Potions
     *
     * @param potions the relevant Potions
     */
    public static List<String> printPotionsAsStrings(List<GearItem> potions) {
        List<String> output = new ArrayList<>();
        output.add("Potions:");
        String format = "%-4s%-21s%-6s%-5s%-8s%-12s";
        String header = String.format(format, "Lvl", "Name", "Price", "Used", "IncAmt", "Ablts");
        output.add(header);
        for(GearItem item : potions) {
            Potion potion = (Potion) item;
            String used = potion.wasUsed() ? "Yes" : "No";
            String abilities = potion.getAbilitiesAsString();
            String status = String.format(format, potion.getMinLevel().getLevel(), potion.getName(), potion.getPrice(),
                    used, potion.getIncrementAmount(), abilities);
            output.add(status);
        }
        return output;
    }
}
