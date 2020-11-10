package main.fight;

import main.legends.Hero;
import main.legends.Monster;
import main.utils.GetUserListNumericalInput;
import main.utils.GetUserNumericInput;

import java.util.*;
import java.util.stream.Stream;

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
    private final Scanner scanner;

    /**
     * Standard constructor, initializing this class with all relevant Monsters and Heroes.
     * Note that the constructor does not perform a pairing, and clients are expected
     * to separately invoke the initialPairing() method.
     *
     * @param heroes the Heroes involved in this fight
     * @param monsters the Monsters involved in this fight
     */
    public PairHeroesAndMonsters(Scanner scanner, List<Hero> heroes, List<Monster> monsters) {
        this.scanner = scanner;
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
        GetUserNumericInput userNumericInput = new GetUserNumericInput(scanner, prompt, options);
        int chosen = userNumericInput.run();
        if(chosen == 0) {
            // Generate a pairing here
            initialHeroesToMonstersPairing();
            System.out.println("Alright, Heroes and Monsters have been matched up randomly. Let's fight!");
        } else {
            // Walk the user through the process of pairing Heroes vs Monsters.
            System.out.println("Ok, let's match each of your Heroes against the Monsters.");
            for(Hero hero : heroes) {
                userPairMonstersForHero(hero);
            }
            System.out.println("Finished. Let's fight!");
        }

        // In either case, generate an internal pairing of Monsters to Heroes.
        initialMonstersToHeroesPairing();
    }

    /**
     * Helper method which checks to see if this Hero is paired with at least one Monster.
     * If so, does nothing. If not, and there are remaining Monsters that are still alive,
     * then auto re-pairs the Hero against each of the remaining Monsters.
     *
     * @param hero the Hero to check
     *
     * This method is analogous to the checkValidityForMonster() method
     */
    public void checkValidityForHero(Hero hero) {
        // Filter out dead Monsters
        heroesMonsters.get(hero).removeIf(Monster::hasDied);

        // Check if there is at least one Monster that this Hero is facing that is still alive
        for(Monster monster : this.heroesMonsters.get(hero)) {
            if(monster.isAlive()) {
                return; // no re-pairing required
            }
        }

        // No more living Monsters. Clear the previous List of Monsters this Hero faced
        heroesMonsters.get(hero).clear();

        // Check if there are remaining Monsters still alive, and if so, assign them to this Hero.
        Stream<Monster> livingMonsters = monsters.stream().filter(Monster::isAlive);
        livingMonsters.forEach(monster -> addMonsterToHero(hero, monster));
    }

    /**
     * Helper method which checks to see if this Monster is paired with at least one
     * Hero. If so, does nothing. If not, and there are remaining Heroes that are still
     * alive, then auto re-pairs this Monster with the remaining Heroes.
     *
     * @param monster the Monster to check
     *
     * This method is analogous to the checkValidityForHero() method
     */
    public void checkValidityForMonster(Monster monster) {
        // Filter out fainted Heroes
        monstersHeroes.get(monster).removeIf(Hero::hasFainted);

        // Check if there is at least one Hero that this Monster is facing that is still alive
        for(Hero hero : monstersHeroes.get(monster)) {
            if(!hero.hasFainted()) {
                return; // no re-pairing required
            }
        }

        // No more living Heroes. Clear the previous List of Heroes this Monster faced
        monstersHeroes.get(monster).clear();

        // Otherwise, check if there are remaining Heroes still alive
        Stream<Hero> livingHeroes = heroes.stream().filter(Hero::hasFainted);
        livingHeroes.forEach(hero -> addHeroToMonster(monster, hero));
    }

    /**
     * For the passed in Hero, retrieves the List of paired Monsters.
     * @param hero the Hero in question
     * @return List of Monsters that this Hero is paired with
     */
    public List<Monster> getMonstersForHero(Hero hero) {
        List<Monster> stillAlive = new ArrayList<>();
        for(Monster monster : heroesMonsters.get(hero)) {
            if(monster.isAlive()) {
                stillAlive.add(monster);
            }
        }
        return stillAlive;
    }

    /**
     * For the passed in Monster, retrieves the List of paired Heroes.
     * @param monster the Monster in question
     * @return List of Heroes that this Monster is paired with.
     */
    public List<Hero> getHeroesForMonster(Monster monster) {
        List<Hero> stillAlive = new ArrayList<>();
        for(Hero hero : monstersHeroes.get(monster)) {
            if(!hero.hasFainted()) {
                stillAlive.add(hero);
            }
        }
        return stillAlive;
    }

    /**
     * For the given Hero, prompts the user to pair said Hero with
     * a list of Monsters
     * @param hero the Hero in question
     */
    private void userPairMonstersForHero(Hero hero) {
        String prompt = "Which Monsters do you want " + hero.getName() + " to fight?";
        List<String> options = new ArrayList<>();
        options.add("All"); // for user convenience, will be option 1

        for(Monster monster : this.monsters) {
            options.add(monster.getName());
        }

        List<Integer> selected = new GetUserListNumericalInput(scanner, prompt, options).run();
        if(selected.contains(1)) { // pair all Monsters with this Hero
            for(Monster monster : monsters) {
                addMonsterToHero(hero, monster);
            }
        } else {
            for(Integer chosen : selected) { // pair only the selected Monsters with this Hero
                chosen -= 2; // account for "All" option and that the list is 1-indexed!
                Monster monster = monsters.get(chosen);
                addMonsterToHero(hero, monster);
            }
        }
    }


    /**
     * Private helper method that pairs Monsters and Heroes initially.
     * That is, it performs the following steps:
     * 1) For each Monster, clears the List of Heroes
     * 2) For each Monster, assigns the index-corresponding Hero to said Monster
     * 3) For any surplus Monsters, assigns all Heroes to that Monster.
     *
     * This method is analogous to the initialHeroesToMonstersPairing() method
     */
    private void initialMonstersToHeroesPairing() {
        // Ensure previous Heroes List is clear
        monstersHeroes.forEach((monsters, heroesList) -> heroesList.clear());

        // Now assign Heroes to Monster, starting with a 1v1 strategy, and
        // switching to a 1vMany strategy once appropriate
        for(int i = 0; i < monsters.size(); i++) {
            if(i >= heroes.size()) { // surpassed number of Heroes, assign 1vMany
                monstersHeroes.put(monsters.get(i), heroes);
            } else { // 1v1 for this Monster/Hero pair
                addHeroToMonster(monsters.get(i), heroes.get(i));
            }
        }
    }

    /**
     * Private helper method that pairs Heroes and Monsters initially.
     * That is, it performs the following steps:
     * 1) For each Hero, clears the List of Monsters
     * 2) For each Hero, assigns the index-corresponding Monster to said Hero
     * 3) For any surplus Heroes, assigns all Monsters to that Hero
     *
     * This method is analogous to the initialMonstersToHeroesPairing() method
     */
    private void initialHeroesToMonstersPairing() {
        // Ensure previous Monster List is clear
        heroesMonsters.forEach((hero, monsterList) -> monsterList.clear());

        // Now assign Monsters to Heroes, starting with a 1v1 strategy, and
        // switching to a 1vMany strategy once appropriate
        for(int i = 0; i < heroes.size(); i++) {
            if(i >= monsters.size()) { // surpassed number of Monsters, assign 1vMany
                heroesMonsters.put(heroes.get(i), monsters);
            } else { // 1v1 for this Hero/Monster pair
                addMonsterToHero(heroes.get(i), monsters.get(i));
            }
        }
    }

    /**
     * Adds the passed in Monster to the List of Monsters that the
     * passed in Hero is paired with.
     * @param hero the Hero in question
     * @param monster the Monster to add as a pairing with this Hero
     */
    private void addMonsterToHero(Hero hero, Monster monster) {
        if(heroesMonsters.containsKey(hero)) {
            List<Monster> pairedMonsters = heroesMonsters.get(hero);
            pairedMonsters.add(monster);
        } else {
            List<Monster> pairedMonsters = new ArrayList<>(Collections.singletonList(monster));
            heroesMonsters.put(hero, pairedMonsters);
        }
    }

    /**
     * Adds the passed in Hero to the List of Heroes that the passed
     * in Monster is paired with.
     * @param monster The Monster in question
     * @param hero the Hero to add as a pairing with this Monster
     */
    private void addHeroToMonster(Monster monster, Hero hero) {
        if(monstersHeroes.containsKey(monster)) {
            List<Hero> pairedHeroes = monstersHeroes.get(monster);
            pairedHeroes.add(hero);
        } else {
            List<Hero> pairedHeroes = new ArrayList<>(Collections.singletonList(hero));
            monstersHeroes.put(monster, pairedHeroes);
        }
    }
}
