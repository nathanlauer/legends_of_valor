package main.fight;

import main.legends.Hero;
import main.legends.Monster;
import main.utils.GetUserNumericInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class PairHeroesAndMonsters is a class which encapsulates the responsibility of
 * pairing Heroes vs Monsters in a fight. There are a number of points contained here.
 * Nominally, one Hero fights one Monster. However, some caveats:
 * 1) At the outset, the user if they'd like to perform the pairing. If not, this class
 * will randomly pair Heroes vs Monsters. If yes, this class walks the user through
 * the process of pairing Heroes vs Monsters.
 * 2) It is assumed that at the outset the number of Heroes and the number of Monsters
 * are the same, although that doesn't actually really matter here. It is possible to
 * initialize this with a differing number of Heroes and Monsters. However, there MUST
 * be at least one Hero, and at least one Monster.
 * 3) Once a pairing is generated, this class maintains that pairing, and exposes methods
 * for retrieving pairings for individual Heroes and Monsters.
 * 4) It is possible for a single Hero to be paired with multiple Monsters, and vise versa.
 * In such a scenario, an attack from said Hero would be spread amongst each of the Monsters.
 * However, that spreading is not this class's responsibility. This class is just responsible
 * for maintaining the pairing.
 * 5) If all Monsters paired with a given Hero die, then we arrive at a state where the
 * relevant Hero is not paired with a Monster. In such a scenario, we want to prompt
 * the user to indicate what that Hero should do - said Hero can be paired with another
 * Monster, or all Monsters, or some subset of the other Monsters. This class exposes
 * a method to check the "validity" of the opposing Monsters for a Hero, and if
 * necessary, prompt the user to re-pair the relevant Hero.
 * 6) Similarly, it is possible that all Heroes paired with a Monster have fainted,
 * and in such a scenario, the Monster in question needs to be re-paired with other
 * Heroes. Unlike Heroes, we don't prompt the user, as Monsters are outside of the
 * user's control. Instead, we simply pair that Monster with every other Hero. That
 * is, said Monster will spread its attack amongst the remaining Heroes.
 *
 * Notes:
 * - This class maintains references to the passed in heroes and monsters lists,
 * and thus will know about updates to those Lists, such as the HealthPower of
 * the contained Legends. However, this class will not modify that List, so
 * clients can rest assured that passing a reference to this class will not
 * change any of the underlying data contained in those Lists.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/8/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class PairHeroesAndMonsters {
    private final HashMap<Hero, List<Monster>> heroesMonsters;
    private final HashMap<Monster, List<Hero>> monstersHeroes;
    private final List<Hero> heroes;
    private final List<Monster> monsters;

    /**
     * Standard constructor, initializing this class with all relevant Monsters and Heroes.
     * Note that the constructor does not perform a pairing, and clients are expected
     * to separately invoke the initialPairing() method.
     *
     * @param heroes the Heroes involved in this fight
     * @param monsters the Monsters involved in this fight
     */
    public PairHeroesAndMonsters(List<Hero> heroes, List<Monster> monsters) {
        this.heroes = heroes;
        this.monsters = monsters;
        this.heroesMonsters = new HashMap<>();
        this.monstersHeroes = new HashMap<>();
    }

    /**
     * Constructs an initial pairing of Heroes to Monster. This happens in one of two ways:
     * 1) The user indicates that they want to do the pairing themselves. In this case,
     * this method walks the user through the process of setting that up.
     * 2) The user indicates that they want to defer responsibility of pairing to this class.
     * In this class, we pair Heroes and Monsters 1v1. The exception is if there are more
     * Heroes than Monsters, or vise versa. If more Heroes, then the remaining Heroes are
     * spread out amongst all Monster. If more Monsters, then the remaining Monsters are
     * spread out amongst all Heroes.
     */
    public void initialPairing() {
        // Prompt the user to determine whether they want to perform the pairing, or
        // this class should do the pairing itself
        String prompt = "How would you like to pair your Heroes vs Monsters?";
        List<String> options = new ArrayList<>();
        options.add("I don't want to do the pairing - just pick a random pairing and let's go!");
        options.add("I'll do the pairing myself.");
        GetUserNumericInput userNumericInput = new GetUserNumericInput(prompt, options);
        int chosen = userNumericInput.run();
        if(chosen == 0) {
            // Generate a pairing here
            // TODO
        } else {
            // Walk the user through the process of pairing Heroes vs Monsters.
            // TODO
        }

        // In either case, generate an internal pairing of Monsters to Heroes.
        // TODO
    }

    public void checkValidityForHero(Hero hero) {

    }

    /**
     * For the passed in Hero, retrieves the List of paired Monsters.
     * @param hero the Hero in question
     * @return List of Monsters that this Hero is paired with
     */
    public List<Monster> getMonstersForHero(Hero hero) {
        return heroesMonsters.get(hero);
    }

    /**
     * For the passed in Monster, retrieves the List of paired Heroes.
     * @param monster the Monster in question
     * @return List of Heroes that this Monster is paired with.
     */
    public List<Hero> getHeroesForMonster(Monster monster) {
        return monstersHeroes.get(monster);
    }
}
