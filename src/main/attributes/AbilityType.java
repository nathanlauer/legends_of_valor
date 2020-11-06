package main.attributes;

/**
 * Enum AbilityType specifies the type of Ability
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/6/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public enum AbilityType {
    ABILITY("Ability"),
    HEALTH("Health"),
    MANA("Mana"),
    STRENGTH("Strength"),
    DEXTERITY("Dexterity"),
    DEFENSE("Defense"),
    AGILITY("Agility");

    private final String name;

    /**
     * Local constructor to map String text to enum values
     * @param type Name of the relevant type
     */
    AbilityType(String type) {
        name = type;
    }

    /**
     *
     * @return the name of this enum type
     */
    public String getName() {
        return name;
    }

    /**
     * For the passed in text, returns the associated enum type
     * @param text name of the enum type desired
     * @return the enum type corresponding to the passed in name.
     */
    public static AbilityType fromStringName(String text) {
        for(AbilityType type : AbilityType.values()) {
            if(type.getName().equalsIgnoreCase(text)) {
                return type;
            }
        }

        // Not found, return the default type
        return AbilityType.ABILITY;
    }

}
