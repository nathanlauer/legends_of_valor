package main.market_and_gear;

import main.legends.Hero;
import main.utils.BeneathLevelException;
import main.utils.GetUserNumericInput;
import main.utils.NotEnoughCoinsException;
import test.utils.Output;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class MarketInteraction is a class that handles the interaction between users and a Market.
 * It allows users to buy GearItems from the market for their Heroes, and sell GearItems from
 * their Heroes to the Market.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class MarketInteraction {
    private final Market market;
    private final List<Hero> heroes;

    /**
     * Standard constructor
     * @param market the Market
     * @param heroes the Heroes
     */
    public MarketInteraction(Market market, List<Hero> heroes) {
        this.market = market;
        this.heroes = heroes;
    }

    /**
     * "Main" method, which walks the user through the process of buying and selling GearItems.
     */
    public void run() {
        System.out.println("Welcome to the Market! Here are the items we have for sale:");
        printAllGearItems();
        Output.printSeparator();

        boolean finished = false;
        while(!finished) {
            // Check to see if the user wants to buy or sell items
            String prompt = "Would you like to buy or sell items?";
            List<String> options = new ArrayList<>(Arrays.asList("Buy", "Sell"));
            int chosen = new GetUserNumericInput(new Scanner(System.in), prompt, options).run();

            if(chosen == 0) {
                buyItems();
            } else {
                sellItems();
            }

            // Check if the user is finished
            prompt = "Would you like to make another transaction?";
            options = new ArrayList<>(Arrays.asList("Yes", "No"));
            chosen = new GetUserNumericInput(new Scanner(System.in), prompt, options).run();
            if(chosen == 1) {
                finished = true;
            }
        }
    }

    /**
     * Prints out all the available GearItems in the Market.
     */
    private void printAllGearItems() {
        Output.printOutputables(market.getWeapons());
        Output.printOutputables(market.getArmor());
        Output.printOutputables(market.getSpells());
        Output.printOutputables(market.getPotions());
    }

    /**
     * Helper function which walks through the process of buying GearItems
     */
    private void buyItems() {
        boolean successfulPurchase = false;
        while(!successfulPurchase) {
            // Get the Hero to buy for
            String prompt = "Which Hero would you like to purchase gear for?";
            Hero hero = this.getSelectedHero(prompt);

            // Get the item that the Hero can buy
            prompt = "Ok, here are the items " + hero.getName() + " can buy:";
            List<Integer> linesToSkip = new ArrayList<>();
            List<GearItem> availableItems = new ArrayList<>();
            List<String> options = buyableOptions(hero, linesToSkip, availableItems);
            if(options.size() == 0) {
                System.out.println("Unfortunately, there are no item that " + hero.getName() + " can buy.");
                return;
            }

            GetUserNumericInput getUserNumericInput = new GetUserNumericInput(new Scanner(System.in), prompt, options);
            getUserNumericInput.setLinesToSkip(linesToSkip);
            int chosen = getUserNumericInput.run();

            // And buy that item.
            GearItem toBuy = availableItems.get(chosen);
            try {
                toBuy.buy(market, hero);
                successfulPurchase = true;
            } catch (NotEnoughCoinsException e) {
                System.out.println("You don't have enough coins to buy this item! Please try again.");
            } catch (BeneathLevelException e) {
                System.out.println(hero.getName() + " is not advanced enough to buy this item! Please try again");
            }
        }
    }

    /**
     * Given a prompt, returns the Hero that the user has selected
     * @param prompt the prompt to display to the user
     * @return Hero selected by the user
     */
    private Hero getSelectedHero(String prompt) {
        if(heroes.size() == 1) {
            return heroes.get(0);
        }
        List<String> options = Output.outputablesAsString(heroes);
        List<Integer> linesToSkip = new ArrayList<>(Collections.singletonList(0)); // skip header line
        GetUserNumericInput getUserNumericInput = new GetUserNumericInput(new Scanner(System.in), prompt, options);
        getUserNumericInput.setLinesToSkip(linesToSkip);
        int selected = getUserNumericInput.run();
        return this.heroes.get(selected);
    }

    /**
     * Prints the list of GearItems from the Market that the Hero can purchase.
     * That is, the Hero has enough coins and is of a high enough level.
     *
     * This function will internally modify linesToSkip and availableItems, filling them
     * such that the caller can expect them to have correct information when this function
     * has completed. That is, linesToSkip will be populated with a list of numbers
     * corresponding to printed rows which are not GearItems, and availableItems will
     * be populated with a list of GearItems that the Hero can buy.
     * @param hero the Hero is question
     * @param linesToSkip list to populate of lines to be skipped
     * @param availableItems list to populate of available GearItems that this hero can buy.
     */
    private List<String> buyableOptions(Hero hero, List<Integer> linesToSkip, List<GearItem> availableItems) {
        int counter = 0;
        List<String> options = new ArrayList<>();

        // Start with Weapons: filter out the weapons that can't be purchased, update linesToSkip, and availableItems
        List<GearItem> items = market.getWeapons();
        List<GearItem> weapons = items.stream().filter(gearItem -> gearItem.heroCanBuy(hero)).collect(Collectors.toList());
        if(weapons.size() > 0) {
            List<String> weaponsOut = Output.outputablesAsString(weapons);
            options.addAll(weaponsOut);
            availableItems.addAll(weapons);

            // There are two lines to skip: the GearItem type line and the
            linesToSkip.add(counter);
            linesToSkip.add(counter + 1);

            // Now, advance the counter past each of these lines
            counter += weaponsOut.size();
        }

        // Do the same with Armor
        items = market.getArmor();
        List<GearItem> armors = items.stream().filter(gearItem -> gearItem.heroCanBuy(hero)).collect(Collectors.toList());
        if(armors.size() > 0) {
            List<String> armorOut = Output.outputablesAsString(armors);
            options.addAll(armorOut);
            availableItems.addAll(armors);

            // There are two lines to skip: the GearItem type line and the
            linesToSkip.add(counter);
            linesToSkip.add(counter + 1);

            // Now, advance the counter past each of these lines
            counter += armorOut.size();
        }

        // And the same with Spells
        items = market.getSpells();
        List<GearItem> spells = items.stream()
                .filter(gearItem -> gearItem.heroCanBuy(hero))
                .filter(gearItem -> ((Spell)gearItem).heroHasEnoughMana(hero))
                .collect(Collectors.toList());

        if(spells.size() > 0) {
            List<String> spellsOut = Output.outputablesAsString(spells);
            options.addAll(spellsOut);
            availableItems.addAll(spells);

            // There are two lines to skip: the GearItem type line and the
            linesToSkip.add(counter);
            linesToSkip.add(counter + 1);

            // Now, advance the counter past each of these lines
            counter += spellsOut.size();
        }

        // And finally, the same with Potions
        items = market.getPotions();
        List<GearItem> potions = items.stream().filter(gearItem -> gearItem.heroCanBuy(hero)).collect(Collectors.toList());
        if(potions.size() > 0) {
            List<String> potionsOut = Output.outputablesAsString(potions);
            options.addAll(potionsOut);
            availableItems.addAll(potions);

            // There are two lines to skip: the GearItem type line and the
            linesToSkip.add(counter);
            linesToSkip.add(counter + 1);
        }

        return options;
    }

    /**
     * Helper function which walks through the process of selling GearItems
     */
    private void sellItems() {

    }
}
