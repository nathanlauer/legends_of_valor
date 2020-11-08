package main.fight;

import main.attributes.Ability;
import main.legends.Hero;
import main.legends.Legend;
import main.legends.NonOwnedGearItemException;
import main.market_and_gear.Potion;
import main.utils.Validations;

/**
 * Class UsePotion is a type of Internal FightMove where a Hero uses a Potion
 * to boost some of their Abilities
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/6/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class UsePotion extends InternalMove {
    private final Potion potion;

    /**
     * Standard Constructor
     * @param legend the Legend using this Potion
     * @param potion the Potion to be used
     */
    public UsePotion(Legend legend, Potion potion) {
        super(legend);
        this.potion = potion;
    }

    /**
     * Executes this FightMove
     */
    @Override
    public void execute() throws InvalidFightMoveException, NonOwnedGearItemException {
        Hero hero = (Hero)this.getExecutor();
        Validations.heroIsAlive(hero);
        Validations.heroOwnsGearItem(hero, potion);
        Validations.potionUnused(potion);

        // Boost Hero's abilities
        for(Ability ability : potion.getAbilities()) {
            Ability relevantAbility = hero.matchAbility(ability);
            relevantAbility.increaseAbilityBy(potion.getIncrementAmount());
        }

        potion.markUsed();
    }
}
