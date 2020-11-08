package test.utils;

import main.attributes.*;
import main.legends.Dragon;
import main.legends.Hero;
import main.legends.Monster;
import main.legends.Paladin;
import main.utils.Coffer;

import java.util.Collections;

/**
 * Class LegendBuilder
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/7/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class LegendBuilder {
    /**
     *
     * @return an example Hero for use in testing
     */
    public static Hero exampleHero() {
        Ability agility = new Ability(AbilityType.AGILITY, 650);
        Ability dexterity = new Ability(AbilityType.DEXTERITY, 700);
        Ability strength = new Ability(AbilityType.STRENGTH, 750);
        Coffer coffer = new Coffer(3000);
        Mana mana = new Mana(1000);
        Level level = new Level(50); // For testing purposes
        HealthPower healthPower = new UncappedHealthPower(100);
        String name = "Solonor_Thelandira";
        return new Paladin(name, level, healthPower, mana, coffer, strength, dexterity, agility);
    }

    /**
     *
     * @return and example Monster for use in testing
     */
    public static Monster exampleMonster() {
        String name = "Desghidorrah";
        Level level = new Level(3);
        HealthPower healthPower = new UncappedHealthPower(100);
        Ability strength = new Ability(AbilityType.STRENGTH, 300);
        Ability defense = new Ability(AbilityType.DEFENSE, 400);
        Ability agility = new Ability(AbilityType.AGILITY, 35);

        return new Dragon(name, level, healthPower, strength, defense, agility);
    }
}
