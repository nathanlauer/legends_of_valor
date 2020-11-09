package main.fight;

import main.attributes.Ability;
import main.attributes.Mana;
import main.legends.Hero;
import main.legends.Monster;
import main.legends.NonOwnedGearItemException;
import main.market_and_gear.Spell;
import main.utils.Validations;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class CastSpell is a type of External FightMove in which the executor
 * casts a spell. In the semantics of this game, only Heroes can cast spells,
 * so the executor must be a Hero, and the receivers are all Monsters.
 *
 * Unlike an attack, a Spell is cast against one particular Monster. An Attack
 * can be split amongst numerous different Heroes.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/6/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class CastSpell extends ExternalMove {
    private final Monster monster;
    private final Spell spell;

    /**
     * Standard constructor
     * @param executor the Hero casting this Spell
     * @param monster the Monster on the receiving end of this Spell.
     */
    public CastSpell(Spell spell, Hero executor, Monster monster) {
        super(executor, new ArrayList<>(Collections.singletonList(monster)));
        this.monster = monster;
        this.spell = spell;
    }

    /**
     * Executes this FightMove
     */
    @Override
    public void execute() throws InvalidFightMoveException, NonOwnedGearItemException {
        Hero hero = (Hero)this.getExecutor();
        Validations.heroIsAlive(hero);
        Validations.legendIsAlive(monster);
        Validations.heroOwnsGearItem(hero, this.spell);

        // Hero needs to have enough Mana
        if(!spell.heroHasEnoughMana(hero)) {
            throw new InvalidFightMoveException("Hero does not have enough Mana to cast this spell!");
        }

        // The amount of damage includes the Dexterity Ability of the Hero
        double damage = spell.getDamage() + spell.getDamage() * (hero.getDexterity().getAbilityValue() / 10000.0);
        monster.wasAttacked(damage);

        // Deduct Monster's relevant Ability by 10%
        Ability toReduce = monster.matchAbility(spell.getAbility());
        if(Validations.notNull(toReduce)) {
            toReduce.decreaseAbilityByPercentage(10);
        }

        // Deduct Hero's Mana
        Mana mana = spell.getMana();
        hero.getMana().decreaseManaBy(mana.getManaAmount());
    }
}
