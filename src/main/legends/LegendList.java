package main.legends;

import main.attributes.*;
import main.utils.Coffer;
import main.utils.GetUserListNumericalInput;
import main.utils.GetUserNumericInput;
import main.utils.Output;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class LegendList contains a List of all Legends available in the game.
 * It is a singleton class, and upon first call, reads in all Monsters
 * and Heroes from disk.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/8/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class LegendList {
    private static LegendList instance = null;
    private final List<Legend> legends;
    private final List<Hero> chosenHeroes;

    /**
     * @return the static instance of this class
     */
    public static LegendList getInstance() {
        if (instance == null) {
            instance = new LegendList();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private LegendList() {
        legends = new ArrayList<>();
        try {
            new ReadLegendsFromDisk().run();

        } catch (IOException e) {
            e.printStackTrace();
            // Shouldn't happen
        }

        //Collections.shuffle(legends); // so we don't have the same order every time
        List<Hero> allHeroes = getHeroes();
        this.chosenHeroes = chooseHeroes(allHeroes);
        /**Random random = new Random();
         int numHeroes = random.nextInt(3) + 1;
         this.chosenHeroes = allHeroes.subList(0, numHeroes);**/
    }

    /**
     * Player chooses a team of heroes.
     * Default max number of heroes: 3.
     */
    private List<Hero> chooseHeroes(List<Hero> allHeroes) {
        int heroNum = 0;
        List<Hero> heroChosen = new ArrayList<>();
        Output.printOutputables(allHeroes);
        List<String> options = new ArrayList<>();
        for (Hero hero : allHeroes) {
            options.add(hero.getName());
        }
        String prompt = "Please choose your team of Heroes. Up to 3 heroes.";
        List<Integer> chosen = new GetUserListNumericalInput(new Scanner(System.in), prompt, options).run();
        for (Integer i : chosen) {
            if (heroChosen.contains(allHeroes.get(i - 1))) {//The program only supports unique heroes.
                System.out.println("You can only have one " + allHeroes.get(i - 1).getName() + "in your team");
                continue;
            }
            if (heroNum == 3) {
                System.out.println("You can only choose 3 heroes at most.");
                break;
            }
            heroChosen.add(allHeroes.get(i - 1));
            heroNum++;
        }
        return heroChosen;
    }

    /**
     * print out the initial stats of the chosen heroes.
     */
    public void printChosenHeroes() {
        Output.printSeparator();
        System.out.println("Your team: ");
        Output.printOutputables(chosenHeroes);
        Output.printSeparator();
    }

    /**
     * @return a List of all Legends
     */
    public List<Legend> getLegends() {
        return legends;
    }

    /**
     * @return a List of every Hero
     */
    public List<Hero> getHeroes() {
        List<Hero> heroes = new ArrayList<>();
        for (Legend legend : legends) {
            if (legend instanceof Hero) {
                heroes.add((Hero) legend);
            }
        }
        return heroes;
    }

    /**
     * @return the list of chosen Heroes.
     */
    public List<Hero> getChosenHeroes() {
        return this.chosenHeroes;
    }

    /**
     * @return a List of Monsters of equal size to chosen Heroes. Attempts to pick Monsters
     * of lower level than the highest level of the chosen Heroes.
     */
    public List<Monster> getCorrespondingMonsters() {
        // Find the max level
        getChosenHeroes().sort(new HigherLevelComparator());
        Level max = getChosenHeroes().get(0).getLevel();

        // Filter out Monsters that have a Level that is too high
        Stream<Monster> monsterStream = getMonsters().stream()
                .filter(monster -> monster.getLevel().isLessThanOrEqual(max));

        List<Monster> output = monsterStream.collect(Collectors.toList());
        return output.subList(0, getChosenHeroes().size());
    }

    /**
     * Looks for a Hero with the passed in name.
     *
     * @param name Name of the requested Hero
     * @return Hero with the passed in name, or null if not found
     */
    public Hero findHeroByName(String name) {
        return getHeroes().stream()
                .filter(aHero -> aHero.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    /**
     * @return a List of every Monster.
     */
    public List<Monster> getMonsters() {
        List<Monster> monsters = new ArrayList<>();
        for (Legend legend : legends) {
            if (legend instanceof Monster) {
                monsters.add((Monster) legend);
            }
        }
        return monsters;
    }

    /**
     * Finds the Monster with the passed in name
     *
     * @param name Name of the desired Monster
     * @return the Monster that has the passed in name, or null if not found
     */
    public Monster findMonsterByName(String name) {
        return getMonsters().stream()
                .filter(monster -> monster.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    /**
     * Private class which is responsible for reading Monsters and Heroes from disk.
     */
    private class ReadLegendsFromDisk {
        private final File dragons;
        private final File exoskeletons;
        private final File spirits;
        private final File paladins;
        private final File sorcerers;
        private final File warriors;

        /**
         * Standard constructor - will throw an error if the expected files don't exist
         */
        public ReadLegendsFromDisk() {
            dragons = new File("data/dragons.txt");
            exoskeletons = new File("data/exoskeletons.txt");
            spirits = new File("data/spirits.txt");
            paladins = new File("data/paladins.txt");
            sorcerers = new File("data/sorcerers.txt");
            warriors = new File("data/warriors.txt");

            // Make sure that all the required files are in place!
            assert dragons.exists();
            assert exoskeletons.exists();
            assert spirits.exists();
            assert paladins.exists();
            assert sorcerers.exists();
            assert warriors.exists();
        }

        /**
         * Reads each of the Hero and Monster files from disk, and adds the results to the Legends list.
         */
        public void run() throws IOException {
            readHeroFile(paladins, "Paladins");
            readHeroFile(sorcerers, "Sorcerers");
            readHeroFile(warriors, "Warriors");
            readMonsterFile(dragons, "Dragons");
            readMonsterFile(exoskeletons, "Exoskeletons");
            readMonsterFile(spirits, "Spirits");
        }

        /**
         * Reads a List of Heroes from the passed in file
         *
         * @param file the relevant Hero file
         */
        private void readHeroFile(File file, String heroType) throws IOException {
            List<String> lines = Files.readAllLines(file.toPath());
            List<String> relevantLines = lines.subList(1, lines.size()); // Ignore header line
            for (String line : relevantLines) {
                line = line.trim();
                String[] items = line.split("\\s+"); // split by whitespace
                if (items.length <= 1) {
                    continue; // skip empty line
                }

                String name = items[0];
                Mana mana = new Mana(Integer.parseInt(items[1]));
                Ability strength = new Ability(AbilityType.STRENGTH, Integer.parseInt(items[2]));
                Ability agility = new Ability(AbilityType.AGILITY, Integer.parseInt(items[3]));
                Ability dexterity = new Ability(AbilityType.DEXTERITY, Integer.parseInt(items[4]));
                Coffer coffer = new Coffer(Integer.parseInt(items[5]));
                int startingExperience = Integer.parseInt(items[6]);
                Level level = new Level(1); // Heroes all start at level 1

                // Both Monsters and Heroes start with hp = 100 * level
                HealthPower hp = new UncappedHealthPower(100 * level.getLevel());

                // Create a hero, depending on file type
                Hero hero;
                switch (heroType) {
                    case "Paladins":
                        hero = new Paladin(name, level, hp, mana, coffer, strength, agility, dexterity);
                        break;
                    case "Sorcerers":
                        hero = new Sorcerer(name, level, hp, mana, coffer, strength, agility, dexterity);
                        break;
                    case "Warriors":
                        hero = new Warrior(name, level, hp, mana, coffer, strength, agility, dexterity);
                        break;
                    default:
                        throw new IOException("Unknown hero file type");
                }
                hero.setStartingExperience(startingExperience);
                legends.add(hero);
            }
        }

        /**
         * Reads a List of Monsters from the passed in file
         *
         * @param file the relevant Monster file
         */
        private void readMonsterFile(File file, String monsterType) throws IOException {
            List<String> lines = Files.readAllLines(file.toPath());
            List<String> relevantLines = lines.subList(1, lines.size()); // Ignore header line
            for (String line : relevantLines) {
                line = line.trim();
                String[] items = line.split("\\s+"); // split by whitespace
                if (items.length <= 1) {
                    continue; // No data on this line
                }

                String name = items[0];
                Level level = new Level(Integer.parseInt(items[1]));
                Ability strength = new Ability(AbilityType.STRENGTH, Integer.parseInt(items[2]));
                Ability defense = new Ability(AbilityType.DEFENSE, Integer.parseInt(items[3]));
                Ability agility = new Ability(AbilityType.AGILITY, Integer.parseInt(items[4]));

                // Both Monsters and Heroes start with hp = 100 * level
                HealthPower hp = new UncappedHealthPower(100 * level.getLevel());

                // Create a hero, depending on file type
                Monster monster;
                switch (monsterType) {
                    case "Dragons":
                        monster = new Dragon(name, level, hp, strength, defense, agility);
                        break;
                    case "Exoskeletons":
                        monster = new Exoskeleton(name, level, hp, strength, defense, agility);
                        break;
                    case "Spirits":
                        monster = new Spirit(name, level, hp, strength, defense, agility);
                        break;
                    default:
                        throw new IOException("Unknown Monster file type");
                }
                legends.add(monster);
            }
        }
    }
}
