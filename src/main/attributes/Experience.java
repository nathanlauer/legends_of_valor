package main.attributes;

import main.legends.Hero;

/**
 * Class Experience represents the amount of experience that a Hero has
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/10/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Experience {
    private int experience;
    private final Hero hero;

    /**
     * Constructor for a Hero that starts at experience 0.
     * @param hero the Hero for this experience
     */
    public Experience(Hero hero) {
        this(hero, 0);
    }

    /**
     * Standard constructor
     * @param hero the Hero for this experience
     * @param experience the amount of experience
     */
    public Experience(Hero hero, int experience) {
        this.experience = experience;
        this.hero = hero;
    }

    /**
     *
     * @return the experience of this Hero
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Sets the amount of experience to the passed in value
     * @param experience the new experience of this Hero
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Increases this Hero's experience by the indicated amount.
     * If the Hero now has enough experience, the Hero will level up.
     * @param amount the added amount of experience
     * @return true if the Hero leveled up, false otherwise.
     */
    public boolean increaseExperience(int amount) {
        this.setExperience(this.getExperience() + amount);

        if(getExperience() >= nextLevelUpExpAmount()) {
            // Level up!!
            Level level = hero.getLevel();
            level.incrementLevel();

            // Increase Mana by 10%
            Mana mana = hero.getMana();
            double nextMana = mana.getFullAmount() * 1.1;
            mana.setFullAmount(nextMana);
            mana.setMana(nextMana);

            // Upgrade HealthPower
            HealthPower healthPower = hero.getHealthPower();
            int nextHealthPower = level.getLevel() * 100;
            healthPower.setFullAmount(nextHealthPower);
            healthPower.setHealthPower(nextHealthPower);

            // Upgrade abilities by 5%
            hero.getStrength().increaseAbilityByPercentage(5);
            hero.getAgility().increaseAbilityByPercentage(5);
            hero.getDexterity().increaseAbilityByPercentage(5);

            // Upgrade special abilities an additional 5%
            for(Ability ability : hero.getSpecialAbilities()) {
                ability.increaseAbilityByPercentage(5);
            }
            return true;
        }
        return false;
    }

    /**
     *
     * @return  the amount of experience required before the Hero can Level up.
     */
    public int nextLevelUpExpAmount() {
        return hero.getLevel().getLevel() * 10;
    }

    /**
     * @return String representation of this Experience object.
     */
    @Override
    public String toString() {
        return String.valueOf(this.experience);
    }
}
