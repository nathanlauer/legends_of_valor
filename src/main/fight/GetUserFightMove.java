package main.fight;

import main.legends.Hero;
import main.legends.Monster;
import main.market_and_gear.*;
import main.utils.GetUserNumericInput;
import main.utils.Validations;
import test.utils.Output;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class GetUserFightMove is a class that encapsulates the logic of building a FightMove
 * for the passed in Hero
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/8/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class GetUserFightMove {
    private final Hero hero;
    private final List<Monster> facedMonsters;

    /**
     * Standard Constructor
     * @param hero Hero who is making this move
     */
    public GetUserFightMove(Hero hero, List<Monster> facedMonsters) {
        this.hero = hero;
        this.facedMonsters = facedMonsters;
    }

    /**
     * Walks the user through the process of selecting a Move
     * @return a FightMove encapsulating the user's desired action
     */
    public FightMove run() {
        // There are five possible moves: attack, use a spell, cast a potion, switch weapons, and switch armor.
        // We output each of these as a possibility, and then check to see if the selected option
        // is valid.
        boolean selectedPossibleMove = false;
        FightMove move = null;
        while(!selectedPossibleMove) {
            switch (promptUserWithOptions()) {
                case 0:
                    // attack
                    move = new Attack(hero, new ArrayList<>(facedMonsters));
                    selectedPossibleMove = true;
                    break;
                case 1:
                    // cast a spell
                    Spell spell = chooseSpellToCast();
                    if(Validations.notNull(spell)) {
                        move = new CastSpell(spell, hero, chooseMonsterForSpell());
                        selectedPossibleMove = true;
                    }
                    break;
                case 2:
                    // use a potion
                    Potion potion = choosePotionsToUse();
                    if(Validations.notNull(potion)) {
                        move = new UsePotion(hero, potion);
                        selectedPossibleMove = true;
                    }
                    break;
                case 3:
                    // switch weapons
                    Weapon weapon = chooseWeaponToSwitch();
                    if(Validations.notNull(weapon)) {
                        move = new SwitchWeapon(hero, weapon);
                        selectedPossibleMove = true;
                    }
                    break;
                case 4:
                    // switch armor
                    Armor armor = chooseArmorToSwitch();
                    if(Validations.notNull(armor)) {
                        move = new SwitchArmor(hero, armor);
                        selectedPossibleMove = true;
                    }
                    break;
                default:
                    System.out.println("Unknown option selected. Please try again.");
                    break;
            }
        }
        return move;
    }

    /**
     * Helper method which prints out the available options to the user.
     * We always print each of the five types of moves, regardless of
     * whether or not they are legal. The responsibility for determining
     * the legality of a given move is left to a different function.
     *
     * The order of prompted options is important, since the user will indicates
     * their choice by inputting a number corresponding to an index in
     * the output list. That order is:
     * 1) Attack
     * 2) Cast a Spell
     * 3) Use a Potion
     * 4) Switch Weapons
     * 5) Switch Armor
     *
     * @return the index of the option chosen by the user
     */
    private int promptUserWithOptions() {
        String prompt = "What would you like to do?";
        List<String> options = new ArrayList<>();
        if(facedMonsters.size() == 1) {
            options.add("Attack " + facedMonsters.get(0).getName() + "!");
        } else { // Assumes greater than 1
            options.add("Attack! (Your attack will be divided equally amongst the " + facedMonsters.size() + " monsters.");
        }
        options.add("Cast a spell");
        options.add("Use a potion");
        options.add("Switch Weapons");
        options.add("Switch Armor");

        return new GetUserNumericInput(new Scanner(System.in), prompt, options).run();
    }

    /**
     * Helper function which chooses an available Spell owned by the Hero for the
     * Hero to use.
     * @return the Spell chosen, or null if none are available
     */
    private Spell chooseSpellToCast() {
        List<GearItem> spells = hero.getGearItemList().getUsableSpells(hero);

        // Display spells that the Hero has, but doesn't have enough Mana to use
        List<GearItem> tooMuchMana = hero.getGearItemList().getNonUsableSpells(hero);
        if(tooMuchMana.size() > 0) {
            System.out.println("Spells that require too much Mana:");
            Output.printOutputables(tooMuchMana);
        }

        // There must be at least one spell available
        if(spells.size() < 1) {
            System.out.println("There are no available spells to cast! Please choose another option");
            return null;
        }

        // Get the desired spell from the user
        String prompt = "Which spell would you like to cast?";
        return (Spell)promptUserForChoice(spells, prompt);
    }

    /**
     * Prompts the user to select a Monster to cast a spell against
     */
    private Monster chooseMonsterForSpell() {
        if(facedMonsters.size() == 1) {
            return facedMonsters.get(0);
        }

        String prompt = "Which Monster do you want to cast your spell against?";
        List<String> options = new ArrayList<>();
        for(Monster monster : facedMonsters) {
            options.add(monster.getName());
        }
        int chosen = new GetUserNumericInput(new Scanner(System.in), prompt, options).run();
        return facedMonsters.get(chosen);
    }

    /**
     * Helper function which chooses an available Potion owned by the Hero for the
     * Hero to use.
     * @return the Potion chosen, or null if none are available.
     */
    private Potion choosePotionsToUse() {
        List<GearItem> availablePotions = hero.getGearItemList().getUsablePotions();

        // There must be at least one available Potion
        if(availablePotions.size() < 1) {
            System.out.println("You don't have any available potions! Please choose another option");
            return null;
        }

        String prompt = "Which Potion would you like to use?";
        return (Potion)promptUserForChoice(availablePotions, prompt);
    }

    /**
     * helper function which chooses the available Weapon for the Hero to switch to.
     * @return The desired Weapon, or null if none are available
     */
    private Weapon chooseWeaponToSwitch() {
        List<GearItem> weapons = hero.getGearItemList().getWeapons();

        // Filter out the Weapon that the Hero is currently using
        Weapon active = hero.getActiveGearItems().getWeapon();
        Stream<GearItem> otherWeaponsStream = weapons.stream().filter(weapon -> !weapon.equals(active));
        List<GearItem> otherWeapons = otherWeaponsStream.collect(Collectors.toList());

        // There must be at least one other Weapon
        if(otherWeapons.size() < 1) {
            System.out.println("No other Weapons to switch to! Please choose a different move");
            return null;
        }

        // Display each of the Weapons
        String prompt = "Please choose a Weapon to switch to:";
        return (Weapon)promptUserForChoice(otherWeapons, prompt);
    }

    /**
     * Helper function to choose the armor for the Hero to switch to
     * @return the desired Armor, or null if none are available.
     */
    private Armor chooseArmorToSwitch() {
        List<GearItem> armors = hero.getGearItemList().getArmor();

        // Filter out the Armor that the Hero is currently wearing
        Armor active = hero.getActiveGearItems().getArmor();
        Stream<GearItem> otherArmorStream = armors.stream().filter(armor -> !armor.equals(active));
        List<GearItem> otherArmor = otherArmorStream.collect(Collectors.toList());

        // There must be at least one other Armor
        if(otherArmor.size() < 1) {
            System.out.println("No other Armor to switch to! Please choose a different option");
            return null;
        }

        // Display each of the Armor
        String prompt = "Please choose a new Armor to switch to:";
        return (Armor)promptUserForChoice(otherArmor, prompt);
    }

    /**
     * Helper function which prompts the user to select one of the available GearItems
     * @param items List of Gear Items to be displayed to the user
     * @param prompt Prompt the user
     * @return GearItem that the user selected
     */
    private GearItem promptUserForChoice(List<? extends GearItem> items, String prompt) {
        // TODO: abstract method to string with format for each GearItem. Update Output methods accordingly
        List<String> options = new ArrayList<>();
        items.forEach(gearItem -> options.add(gearItem.toString()));
        int chosen = new GetUserNumericInput(new Scanner(System.in), prompt, options).run();

        return items.get(chosen);
    }
}
