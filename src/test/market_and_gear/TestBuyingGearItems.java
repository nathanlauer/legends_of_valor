package test.market_and_gear;

import main.attributes.Ability;
import main.attributes.Level;
import main.attributes.Mana;
import main.attributes.UncappedHealthPower;
import main.legends.Hero;
import main.legends.NonOwnedGearItemException;
import main.legends.Sorcerer;
import main.market_and_gear.Armor;
import main.market_and_gear.GearItem;
import main.market_and_gear.Market;
import main.utils.BeneathLevelException;
import main.utils.Coffer;
import main.utils.NotEnoughCoinsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class TestBuyingGearItems
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/4/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestBuyingGearItems {
    private final Hero hero;

    public TestBuyingGearItems() {
        Ability agility = new Ability("Agility", 1000);
        Ability dexterity = new Ability("Dexterity", 900);
        Ability strength = new Ability("Strength", 200);
        Coffer coffer = new Coffer(50);
        Mana mana = new Mana(300);
        hero = new Sorcerer("Gandalf", new Level(34), new UncappedHealthPower(100), mana, coffer, agility, dexterity, strength);
    }

    @Test
    public void buyGearItems() {
        Market market = new Market();
        GearItem toBuy = market.getGearItems().get(0);
        hero.getCoffer().setNumCoins(GearItem.mostExpensiveGearItem + 100);
        try {
            toBuy.buy(market, hero);
        } catch (NotEnoughCoinsException | BeneathLevelException e) {
            e.printStackTrace();
            fail();
        }

        assertFalse(market.hasGearItem(toBuy));
        assertTrue(hero.getGearItemList().containsGearItem(toBuy));
        assertTrue(hero.getActiveGearItems().gearItemIsActive(toBuy)); // this was the first item bought, it should be active
    }

    @Test
    public void buyTooExpensive() {
        Market market = new Market();
        GearItem toBuy = market.getGearItems().get(0);
        hero.getCoffer().setNumCoins(0);
        try {
            toBuy.buy(market, hero);
            fail();
        } catch (NotEnoughCoinsException e) {
            // passed
        } catch (BeneathLevelException e) {
            fail();
        }
    }

    @Test
    public void buyTooAdvancedItem() {
        Market market = new Market();
        GearItem toBuy = market.getGearItems().get(0);
        hero.getCoffer().setNumCoins(GearItem.mostExpensiveGearItem + 100);
        Level nextMinLevel = new Level(hero.getLevel().getLevel() + 1);
        toBuy.setMinLevel(nextMinLevel);
        try {
            toBuy.buy(market, hero);
            fail();
        } catch (NotEnoughCoinsException e) {
            fail();
        } catch (BeneathLevelException e) {
            // passed
        }
    }

    @Test
    public void buyUnownedItem() {
        Market market = new Market();
        GearItem toBuy = new Armor("DefinitelyNot", 100, new Level(1), 100);
        hero.getCoffer().setNumCoins(GearItem.mostExpensiveGearItem + 100);
        Level nextMinLevel = new Level(hero.getLevel().getLevel() + 1);
        toBuy.setMinLevel(nextMinLevel);
        int wasCaught = 0;
        try {
            toBuy.buy(market, hero);
            fail();
        } catch (NotEnoughCoinsException | BeneathLevelException e) {
            fail();
        } catch (NonOwnedGearItemException e) {
            // passed
            wasCaught = 1;
        }
        assertEquals(wasCaught, 1);
    }

    @Test
    public void buyPotion() {
        Market market = new Market();
        GearItem potion = market.getPotions().get(0);
        hero.getCoffer().setNumCoins(GearItem.mostExpensiveGearItem + 100);
        try {
            potion.buy(market, hero);
        } catch (NotEnoughCoinsException | BeneathLevelException e) {
            e.printStackTrace();
            fail();
        }

        assertFalse(market.hasGearItem(potion));
        assertTrue(hero.getGearItemList().containsGearItem(potion));
        assertTrue(hero.getActiveGearItems().gearItemIsActive(potion));
        assertTrue(hero.getActiveGearItems().hasActivePotions());
    }

    @Test
    public void buySpell() {
        Market market = new Market();
        GearItem spell = market.getSpells().get(0);
        hero.getCoffer().setNumCoins(GearItem.mostExpensiveGearItem + 100);
        try {
            spell.buy(market, hero);
        } catch (NotEnoughCoinsException | BeneathLevelException e) {
            e.printStackTrace();
            fail();
        }

        assertFalse(market.hasGearItem(spell));
        assertTrue(hero.getGearItemList().containsGearItem(spell));
        assertTrue(hero.getActiveGearItems().gearItemIsActive(spell));
        assertTrue(hero.getActiveGearItems().hasActiveSpells());
    }
}
