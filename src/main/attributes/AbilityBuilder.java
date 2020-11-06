package main.attributes;

/**
 * Class AbilityBuilder provides a series of static methods that create
 * baseline Abilities.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class AbilityBuilder {
    /**
     *
     * @return a new Ability with name "Damage" and value 0
     */
    public static Ability baseDamageAbility() {
        return new Ability(AbilityType.DAMAGE, 0);
    }

    /**
     *
     * @return a new Ability with name "Defense" and value 0
     */
    public static Ability baseDefenseAbility() {
        return new Ability(AbilityType.DEFENSE, 0);
    }

    /**
     *
     * @return a new Ability with name "DodgeChance" and value 0
     */
    public static Ability baseDodgeChanceAbility() {
        return new Ability(AbilityType.DODGE_CHANCE, 0);
    }

    /**
     *
     * @return a new Ability with name "Agility" and value 0
     */
    public static Ability baseAgilityAbility() {
        return new Ability(AbilityType.AGILITY, 0);
    }

    /**
     *
     * @return a new Ability with name "Dexterity" and value 0
     */
    public static Ability baseDexterityAbility() {
        return new Ability(AbilityType.DEXTERITY, 0);
    }

    /**
     *
     * @return a new Ability with name "Strength" and value 0
     */
    public static Ability baseStrengthAbility() {
        return new Ability(AbilityType.STRENGTH, 0);
    }
}
