package test.market_and_gear;

import main.attributes.*;
import main.legends.Hero;
import main.legends.Sorcerer;
import main.market_and_gear.Armor;
import main.market_and_gear.GearItem;
import main.market_and_gear.Market;
import main.utils.Coffer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Class TestSellingGearItems
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/4/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestSellingGearItems {
    private final Hero hero;

    public TestSellingGearItems() {
        Ability agility = new Ability(AbilityType.AGILITY, 1000);
        Ability dexterity = new Ability(AbilityType.DEXTERITY, 900);
        Ability strength = new Ability(AbilityType.STRENGTH, 200);
        Coffer coffer = new Coffer(50);
        Mana mana = new Mana(300);
        hero = new Sorcerer("Gandalf", new Level(34), new UncappedHealthPower(100), mana, coffer, agility, dexterity, strength);
    }

    @Test
    public void sellItem() {
        int originalPrice = 100;
        GearItem toSell = new Armor("Armor", originalPrice, new Level(1), 100);
        hero.getCoffer().setNumCoins(0);
        hero.getGearItemList().addGearItem(toSell);
        hero.getActiveGearItems().putOnArmor((Armor)toSell);

        Market market = new Market();
        toSell.sell(market, hero);

        assertEquals(hero.getCoffer().getNumCoins(), originalPrice/2);
        assertFalse(hero.getActiveGearItems().hasActiveArmor());
        assertFalse(hero.getActiveGearItems().gearItemIsActive(toSell));
        assertFalse(hero.getGearItemList().containsGearItem(toSell));
    }
}
