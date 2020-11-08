package test.fight;

import main.attributes.Ability;
import main.fight.*;
import main.fight.CastSpell;
import main.legends.Hero;
import main.legends.Monster;
import main.market_and_gear.*;
import main.utils.BeneathLevelException;
import main.utils.NotEnoughCoinsException;
import org.junit.jupiter.api.Test;
import test.utils.LegendBuilder;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class testFightMoves
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/7/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class testFightMoves {
    @Test
    public void testSwitchWeapon() {
        Hero hero = LegendBuilder.exampleHero();
        Market firstMarket = new Market();
        Weapon prevWeapon = (Weapon)firstMarket.getWeapons().get(0);
        Market secondMarket = new Market();
        Weapon currentWeapon = (Weapon)secondMarket.getWeapons().get(0);

        if(prevWeapon.equals(currentWeapon)) {
            currentWeapon.setName("DifferentName");
        }
        try {
            prevWeapon.buy(firstMarket, hero);
            currentWeapon.buy(secondMarket, hero);
        } catch (NotEnoughCoinsException e) {
            e.printStackTrace();
            System.out.println("Hero coffer: " + hero.getCoffer().getNumCoins());
            System.out.println("Prev Weapon cost: " + prevWeapon.getPrice());
            System.out.println("Current Weapon cost: " + currentWeapon.getPrice());
            fail();
        } catch (BeneathLevelException e) {
            e.printStackTrace();
            fail();
        }
        hero.getActiveGearItems().deactivateWeapon();
        hero.getActiveGearItems().activateWeapon(prevWeapon);
        assertEquals(prevWeapon, hero.getActiveGearItems().getWeapon());

        FightMove move = new SwitchWeapon(hero, currentWeapon);
        try {
            move.execute();
        } catch (InvalidFightMoveException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(currentWeapon, hero.getActiveGearItems().getWeapon());
    }

    @Test
    public void testSwitchArmor() {
        Hero hero = LegendBuilder.exampleHero();
        Market firstMarket = new Market();
        Armor prevArmor = (Armor) firstMarket.getArmor().get(0);
        Market secondMarket = new Market();
        Armor currentArmor = (Armor)secondMarket.getArmor().get(0);

        // In case we randomly received the same Armor
        if(currentArmor.equals(prevArmor)) {
            currentArmor.setName("DifferentArmor");
        }

        try {
            prevArmor.buy(firstMarket, hero);
            currentArmor.buy(secondMarket, hero);
        } catch (NotEnoughCoinsException e) {
            e.printStackTrace();
        } catch (BeneathLevelException e) {
            e.printStackTrace();
            fail();
        }
        hero.getActiveGearItems().removeArmor();
        hero.getActiveGearItems().putOnArmor(prevArmor);
        assertEquals(prevArmor, hero.getActiveGearItems().getArmor());

        FightMove move = new SwitchArmor(hero, currentArmor);
        try {
            move.execute();
        } catch (InvalidFightMoveException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(currentArmor, hero.getActiveGearItems().getArmor());
    }

    @Test
    public void testAttack() {
        Hero hero = LegendBuilder.exampleHero();
        Market market = new Market();
        Weapon weapon = (Weapon)market.getWeapons().get(0);
        Monster monster = LegendBuilder.exampleMonster();
        try {
            weapon.buy(market, hero);
        } catch (NotEnoughCoinsException | BeneathLevelException e) {
            e.printStackTrace();
            fail();
        }

        // Set the Monster's dodge ability to zero, for a deterministic tes
        monster.getAgility().setAbilityValue(0);

        Attack attack = new Attack(hero, new ArrayList<>(Collections.singletonList(monster)));
        double prevMonsterHp = monster.getHealthPower().getHealthPower();

        try {
            attack.execute();
        } catch (InvalidFightMoveException e) {
            e.printStackTrace();
            fail();
        }

        double currentMonsterHp = monster.getHealthPower().getHealthPower();
        assertNotEquals(prevMonsterHp, currentMonsterHp);
        double expected = prevMonsterHp - (attack.getDamagePerReceiver() - monster.getDefenseAmount())*0.05;
        assertEquals(expected, currentMonsterHp);
    }

    @Test
    public void testCastSpell() {
        Hero hero = LegendBuilder.exampleHero();
        Monster monster = LegendBuilder.exampleMonster();
        Market market = new Market();
        Spell spell = (Spell)market.getSpells().get(0);
        double initialMana = hero.getMana().getManaAmount();

        // Set the Monster's dodge chance to zero, for a deterministic test
        monster.getAgility().setAbilityValue(0);
        double prevMonsterHp = monster.getHealthPower().getHealthPower();

        // Retrieve the Monster's original ability
        // The spell should also have reduced some Ability value
        Ability toReduce = monster.matchAbility(spell.getAbility());
        double expectedNewValue = toReduce.getAbilityValue() - (toReduce.getAbilityValue() * 0.1);

        // Purchase the spell
        try {
            spell.buy(market, hero);
        } catch (NotEnoughCoinsException | BeneathLevelException e) {
            e.printStackTrace();
            fail();
        }

        FightMove castSpell = new CastSpell(spell, hero, monster);

        // after the spell, damage should have been done
        double expectedDamage = spell.getDamage() + (hero.getDexterity().getAbilityValue() / 10000.0) * spell.getDamage() - monster.getDefenseAmount();
        expectedDamage = expectedDamage * 0.05;

        try {
            castSpell.execute();
        } catch (InvalidFightMoveException e) {
            e.printStackTrace();
            fail();
        }

        double currentMonsterHp = monster.getHealthPower().getHealthPower();
        if(currentMonsterHp != (prevMonsterHp - expectedDamage)) {
            System.out.println(spell.getName());
        }
        assertEquals(currentMonsterHp, prevMonsterHp - expectedDamage);

        // The spell should also have reduced some Ability value
        assertEquals(expectedNewValue, toReduce.getAbilityValue());

        // And the Hero should have lost some Mana
        double expectedMana = initialMana - spell.getMana().getManaAmount();
        if(expectedMana < 0) { expectedMana = 0.0; }
        assertEquals(expectedMana, hero.getMana().getManaAmount());
    }
}
