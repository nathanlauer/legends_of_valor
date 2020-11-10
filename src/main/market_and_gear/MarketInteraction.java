package main.market_and_gear;

import main.legends.GearItemList;
import main.legends.Hero;
import main.legends.NonOwnedGearItemException;
import main.utils.BeneathLevelException;
import main.utils.GetUserNumericInput;
import main.utils.GetUserYesNoInput;
import main.utils.NotEnoughCoinsException;
import main.utils.Output;

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
        System.out.println("Weapons:");
        Output.printOutputables(market.getWeapons());
        Output.newLine();

        System.out.println("Armor:");
        Output.printOutputables(market.getArmor());
        Output.newLine();

        System.out.println("Spells:");
        Output.printOutputables(market.getSpells());
        Output.newLine();

        System.out.println("Potions:");
        Output.printOutputables(market.getPotions());
        Output.newLine();
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
            List<String> options = transactionableOptions(market.getGearItemList(), hero, linesToSkip, availableItems, true);
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

                System.out.println(hero.getName() + " purchased " + toBuy.getName() + "!");
            } catch (NotEnoughCoinsException e) {
                System.out.println("You don't have enough coins to buy this item! Please try again.");
            } catch (BeneathLevelException e) {
                System.out.println(hero.getName() + " is not advanced enough to buy this item! Please try again");
            }
        }
    }

    /**
     * Helper function which walks through the process of selling GearItems
     */
    private void sellItems() {
        boolean successfulSale = false;
        while(!successfulSale) {
            // Get the Hero to sell for
            String prompt = "Which Hero would you like to sell gear for?";
            Hero hero = this.getSelectedHero(prompt);

            // Prompt the user with the item they would like to sell.
            // Get the item that the Hero can buy
            prompt = "Ok, each item can be sold for half the listed price. Here are the items " + hero.getName() + " can sell: ";
            List<Integer> linesToSkip = new ArrayList<>();
            List<GearItem> availableItems = new ArrayList<>();
            List<String> options = transactionableOptions(hero.getGearItemList(), hero, linesToSkip, availableItems, false);
            if(options.size() == 0) {
                System.out.println("Unfortunately, there are no item that " + hero.getName() + " can sell.");
                return;
            }

            GetUserNumericInput getUserNumericInput = new GetUserNumericInput(new Scanner(System.in), prompt, options);
            getUserNumericInput.setLinesToSkip(linesToSkip);
            int chosen = getUserNumericInput.run();

            // And sell that item.
            GearItem toSell = availableItems.get(chosen);
            try {
                sellItem(hero, toSell);
                successfulSale = true;

                System.out.println(hero.getName() + " has sold " + toSell.getName() + " for " + toSell.getPrice() / 2.0 + " coins.");
            } catch (NonOwnedGearItemException e) {
                System.out.println("Can't sell that item, as " + hero.getName() + " does not own it!");
            }
        }
    }

    /**
     * Prompts the user if they are sure they want to sell the passed in item, and if so,
     * sells it.
     * @param item the item to sell
     */
    private void sellItem(Hero hero, GearItem item) throws NonOwnedGearItemException {
        String prompt = "Are you sure you want to sell " + item.getName() + " for " + item.getPrice() / 2.0 + " coins?";
        boolean wantsToSell = GetUserYesNoInput.run(prompt);
        if(wantsToSell) {
            item.sell(market, hero);
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
        List<String> options = Output.outputablesAsStrings(heroes);
        List<Integer> linesToSkip = new ArrayList<>(Collections.singletonList(0)); // skip header line
        GetUserNumericInput getUserNumericInput = new GetUserNumericInput(new Scanner(System.in), prompt, options);
        getUserNumericInput.setLinesToSkip(linesToSkip);
        int selected = getUserNumericInput.run();
        return this.heroes.get(selected);
    }

    /**
     * Prints the list of GearItems from the Market that the Hero can purchase.
     * That is, the Hero has enough coins and is of a high enough level,
     *
     * Alternatively, this function can be used to print out a list of GearItems that a Hero
     * can sell.
     *
     * This function will internally modify linesToSkip and availableItems, filling them
     * such that the caller can expect them to have correct information when this function
     * has completed. That is, linesToSkip will be populated with a list of numbers
     * corresponding to printed rows which are not GearItems, and availableItems will
     * be populated with a list of GearItems that the Hero can buy/sell.
     *
     * @param gearItemList object which contains all items that can be transacted.
     * @param hero the Hero is question
     * @param linesToSkip list to populate of lines to be skipped
     * @param availableItems list to populate of available GearItems that this hero can buy.
     * @param isBuying whether or not buying filters should be applied
     */
    private List<String> transactionableOptions(GearItemList gearItemList, Hero hero, List<Integer> linesToSkip, List<GearItem> availableItems, boolean isBuying) {
        int counter = 0;
        List<String> options = new ArrayList<>();

        // Start with Weapons: filter out the weapons that can't be purchased, update linesToSkip, and availableItems
        List<GearItem> items = gearItemList.getWeapons();
        if(isBuying) {
            items = items.stream().filter(gearItem -> gearItem.heroCanBuy(hero)).collect(Collectors.toList());
        }
        if(items.size() > 0) {
            options.add("Weapons:");
            List<String> weaponsOut = Output.outputablesAsStrings(items);
            options.addAll(weaponsOut);
            availableItems.addAll(items);
            options.add(""); // will print a newline

            // There are three lines to skip: two at the top, and one at the end
            linesToSkip.add(counter);
            linesToSkip.add(counter + 1);

            // Now, advance the counter past each of these lines
            counter += weaponsOut.size() + 1;
            linesToSkip.add(counter); // account for the newline at the end
            counter++;
        }

        // Do the same with Armor
        items = gearItemList.getArmor();
        if(isBuying) {
            items = items.stream().filter(gearItem -> gearItem.heroCanBuy(hero)).collect(Collectors.toList());
        }
        if(items.size() > 0) {
            options.add("Armor:");
            List<String> armorOut = Output.outputablesAsStrings(items);
            options.addAll(armorOut);
            availableItems.addAll(items);
            options.add(""); // will print a newline

            // There are two lines to skip: the GearItem type line and the
            linesToSkip.add(counter);
            linesToSkip.add(counter + 1);

            // Now, advance the counter past each of these lines
            counter += armorOut.size() + 1;
            linesToSkip.add(counter); // account for the newline at the end
            counter++;
        }

        // And the same with Spells
        items = gearItemList.getSpells();
        if(isBuying) {
            items = items.stream()
                    .filter(gearItem -> gearItem.heroCanBuy(hero))
                    .filter(gearItem -> ((Spell)gearItem).heroHasEnoughMana(hero))
                    .collect(Collectors.toList());

        }

        if(items.size() > 0) {
            options.add("Spells:");
            List<String> spellsOut = Output.outputablesAsStrings(items);
            options.addAll(spellsOut);
            availableItems.addAll(items);
            options.add(""); // will print a newline

            // There are two lines to skip: the GearItem type line and the
            linesToSkip.add(counter);
            linesToSkip.add(counter + 1);

            // Now, advance the counter past each of these lines
            counter += spellsOut.size() + 1;
            linesToSkip.add(counter); // account for the newline at the end
            counter++;
        }

        // And finally, the same with Potions
        items = gearItemList.getPotions();
        if(isBuying) {
            items = items.stream().filter(gearItem -> gearItem.heroCanBuy(hero)).collect(Collectors.toList());
        }
        if(items.size() > 0) {
            options.add("Potions:");
            List<String> potionsOut = Output.outputablesAsStrings(items);
            options.addAll(potionsOut);
            availableItems.addAll(items);

            // There are two lines to skip: the GearItem type line and the
            linesToSkip.add(counter);
            linesToSkip.add(counter + 1);
        }

        return options;
    }
}
