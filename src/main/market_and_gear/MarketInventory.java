package main.market_and_gear;

import main.attributes.Ability;
import main.attributes.Level;
import main.attributes.Mana;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class MarketInventory is a Singleton class that contains every possible
 * GearItem that can be bought or sold in the game.
 *
 * The first time some client calls the getInstance static method on this class,
 * a private instance is created internally. When that happens, data is read
 * from disk, and should that fail, an error will occur. However, an error
 * should not occur, and, since there is only a single instance of this class,
 * data will only be read from disk once. Any later times that a client
 * calls the getInstance method of this class, the instance will have been
 * previously created, and so data will not be read in from disk again.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/3/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class MarketInventory {
    private static MarketInventory instance = null;
    private final List<GearItem> gearItems;

    /**
     *
     * @return Singleton instance of the MarketInventory class.
     */
    public static MarketInventory getInstance() {
        if(instance == null) {
            instance = new MarketInventory();
        }
        return instance;
    }

    /**
     * Private constructor. Initializes the MarketInventory with
     * all GearItems.
     */
    private MarketInventory() {
        gearItems = new ArrayList<>();
        try {
            new ReadDataFromDisk().run();
        } catch (IOException e) {
            e.printStackTrace();
            // Shouldn't happen. Note that if the input files are missing, this won't catch that.
        }
    }

    /**
     *
     * @return a List of every GearItem contained in the game.
     */
    public List<GearItem> getAllGearItems() {
        return gearItems;
    }

    /**
     *
     * @return all GearItems that are Weapons
     */
    public List<GearItem> getAllWeapons() {
        Stream<GearItem> weapons = gearItems.stream().filter(gearItem -> gearItem instanceof Weapon);
        return weapons.collect(Collectors.toList());
    }

    /**
     *
     * @return all GearItems that are Armor
     */
    public List<GearItem> getAllArmor() {
        Stream<GearItem> armors = gearItems.stream().filter(gearItem -> gearItem instanceof Armor);
        return armors.collect(Collectors.toList());
    }

    /**
     *
     * @return all GearItems that are Potions
     */
    public List<GearItem> getAllPotions() {
        Stream<GearItem> potions = gearItems.stream().filter(gearItem -> gearItem instanceof Potion);
        return potions.collect(Collectors.toList());
    }

    /**
     *
     * @return all GearItems that are Spells
     */
    public List<GearItem> getAllSpells() {
        Stream<GearItem> spells = gearItems.stream().filter(gearItem -> gearItem instanceof Spell);
        return spells.collect(Collectors.toList());
    }

    /**
     * This is a private which encapsulates the process of reading all data from disk.
     */
    private class ReadDataFromDisk {
        File weaponry;
        File armory;
        File potions;
        File iceSpells;
        File fireSpells;
        File lightningSpells;

        /**
         * Constructor. Checks that the required files all exist.
         */
        public ReadDataFromDisk() {
            weaponry = new File("data/Weaponry.txt");
            armory = new File("data/Armory.txt");
            potions = new File("data/Potions.txt");
            iceSpells = new File("data/IceSpells.txt");
            fireSpells = new File("data/FireSpells.txt");
            lightningSpells = new File("data/LightningSpells.txt");

            // We need these files to operate
            assert(weaponry.exists());
            assert(armory.exists());
            assert(potions.exists());
            assert(iceSpells.exists());
            assert(fireSpells.exists());
            assert(lightningSpells.exists());
        }

        /**
         * Primary method of this class which coordinates the process of reading in
         * data files from disk.
         * @throws IOException if a read operation fails.
         */
        public void run() throws IOException {
            readWeaponry();
            readArmory();
            readPotions();
            readIceSpells();
            readFireSpells();
            readLightningSpells();
        }

        /**
         * Helper function which reads in weaponry
         */
        private void readWeaponry() throws IOException {
            List<String> lines = Files.readAllLines(weaponry.toPath());
            List<String> relevantLines = lines.subList(1, lines.size()); // Ignore header line
            for(String line : relevantLines) {
                line = line.trim();
                String[] items = line.split("\\s+"); // split by whitespace
                String name = items[0];
                int price = Integer.parseInt(items[1]);
                int minLevel = Integer.parseInt(items[2]);
                int damage = Integer.parseInt(items[3]);
                int numHands = Integer.parseInt(items[4]);
                GearItem weapon = new Weapon(name, price, new Level(minLevel), damage, numHands);
                gearItems.add(weapon);
            }
        }

        /**
         * Helper function which reads in armory
         */
        private void readArmory() throws IOException {
            List<String> lines = Files.readAllLines(armory.toPath());
            List<String> relevantLines = lines.subList(1, lines.size()); // Ignore header line
            for(String line : relevantLines) {
                line = line.trim();
                String[] items = line.split("\\s+"); // split by whitespace
                String name = items[0];
                int price = Integer.parseInt(items[1]);
                int minLevel = Integer.parseInt(items[2]);
                int defense = Integer.parseInt(items[3]);

                GearItem armor = new Armor(name, price, new Level(minLevel), defense);
                gearItems.add(armor);
            }
        }

        /**
         * Helper function which reads in potions
         */
        private void readPotions() throws IOException {
            List<String> lines = Files.readAllLines(potions.toPath());
            List<String> relevantLines = lines.subList(1, lines.size()); // Ignore header line
            for(String line : relevantLines) {
                line = line.trim();
                String[] items = line.split("\\s+"); // split by whitespace
                String name = items[0];
                int price = Integer.parseInt(items[1]);
                int minLevel = Integer.parseInt(items[2]);
                int incrementAmount = Integer.parseInt(items[3]);
                // Abilities: this part is trickier, as there may be a single ability,
                // multiple abilities, or a format "All ability/ability/...", where
                // there is an extra space!
                List<Ability> abilities = new ArrayList<>();
                String relevantAbilities = items[4];
                if(items[4].equals("All")) {
                    relevantAbilities = items[5];
                }
                String[] abilityStrings = relevantAbilities.split("/");
                for(String abilityName : abilityStrings) {
                    Ability ability = new Ability(abilityName, 0);
                    abilities.add(ability);
                }

                GearItem potion = new Potion(name, price, new Level(minLevel), abilities, incrementAmount);
                gearItems.add(potion);
            }
        }

        /**
         * Helper function which reads in ice spells
         */
        private void readIceSpells() throws IOException {
            List<String> lines = Files.readAllLines(iceSpells.toPath());
            List<String> relevantLines = lines.subList(1, lines.size()); // Ignore header line
            for(String line : relevantLines) {
                line = line.trim();
                String[] items = line.split("\\s+"); // split by whitespace
                String name = items[0];
                int price = Integer.parseInt(items[1]);
                int minLevel = Integer.parseInt(items[2]);
                int damage = Integer.parseInt(items[3]);
                int mana = Integer.parseInt(items[4]);

                GearItem spell = new IceSpell(name, price, new Level(minLevel), new Mana(mana), damage);
                gearItems.add(spell);
            }
        }

        /**
         * Helper function which reads in fire spells
         */
        private void readFireSpells() throws IOException {
            List<String> lines = Files.readAllLines(fireSpells.toPath());
            List<String> relevantLines = lines.subList(1, lines.size()); // Ignore header line
            for(String line : relevantLines) {
                line = line.trim();
                String[] items = line.split("\\s+"); // split by whitespace
                String name = items[0];
                int price = Integer.parseInt(items[1]);
                int minLevel = Integer.parseInt(items[2]);
                int damage = Integer.parseInt(items[3]);
                int mana = Integer.parseInt(items[4]);

                GearItem spell = new FireSpell(name, price, new Level(minLevel), new Mana(mana), damage);
                gearItems.add(spell);
            }
        }

        /**
         * Helper function which reads in lightning spells
         */
        private void readLightningSpells() throws IOException {
            List<String> lines = Files.readAllLines(lightningSpells.toPath());
            List<String> relevantLines = lines.subList(1, lines.size()); // Ignore header line
            for(String line : relevantLines) {
                line = line.trim();
                String[] items = line.split("\\s+"); // split by whitespace
                String name = items[0];
                int price = Integer.parseInt(items[1]);
                int minLevel = Integer.parseInt(items[2]);
                int damage = Integer.parseInt(items[3]);
                int mana = Integer.parseInt(items[4]);

                GearItem spell = new LightningSpell(name, price, new Level(minLevel), new Mana(mana), damage);
                gearItems.add(spell);
            }
        }
    }
}
