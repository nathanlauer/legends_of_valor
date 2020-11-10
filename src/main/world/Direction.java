package main.world;

/**
 * Enum Direction encapsulates a direction for the Heroes to move
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public enum Direction {
    UP("up"),
    DOWN("down"),
    RIGHT("right"),
    LEFT("left");

    private final String name;

    Direction(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
