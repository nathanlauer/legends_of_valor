package main.utils;

/**
 * Class Coffer is a holder for money (in this game, we use coins for money, as there
 * is only a single currency throughout the game).
 *
 * Here, we actually allow a Coffer to have a negative number of coins. It is up to
 * client classes to decide whether or not that should be a valid state.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class Coffer {
    private int numCoins;

    /**
     * Empty constructor for a Coffer. Sets the number of coins to zero.
     */
    public Coffer() {
        this(0);
    }

    /**
     * Standard constructor for a Coffer. Sets numCoins to the passed in value.
     * @param numCoins number of coins in this Coffer.
     */
    public Coffer(int numCoins) {
        Validations.nonNegative(numCoins, "numCoins");
        this.numCoins = numCoins;
    }

    /**
     *
     * @return the number of coins in this Coffer
     */
    public int getNumCoins() {
        return numCoins;
    }

    /**
     * Sets the number of coins in this Coffer to the passed in value
     * @param coins the new number of coins in this Coffer.
     */
    public void setNumCoins(int coins) {
        numCoins = coins;
    }

    /**
     * Adds the passed in number of coins to this coffer.
     * Throws an IllegalArgumentException if coins is negative.
     * @param coins number of coins to be added
     */
    public void addCoins(int coins) {
        Validations.nonNegative(coins, "coins");
        this.setNumCoins(this.getNumCoins() + coins);
    }

    /**
     * Removes the passed number of coins from this Coffer.
     * Note that Coffers may become negative, and it is left up to
     * client classes to decide whether or not such a state is allowed.
     *
     * Throws an IllegalArgumentException if coins is negative.
     * @param coins number of coins to remove from this Coffer.
     */
    public void removeCoins(int coins) {
        Validations.nonNegative(coins, "coins");
        this.setNumCoins(this.getNumCoins() - coins);
    }

    /**
     * Indicates whether or not this Coffer contains enough coins, that is,
     * more or the same number of coins than the passed value.
     * @param coins number of coins in question
     * @return true if numCoins >= coins, false otherwise
     */
    public boolean hasEnoughCoins(int coins) {
        return numCoins >= coins;
    }

    /**
     * @return String representation of this Coffer object.
     */
    @Override
    public String toString() {
        return "Coffer, numCoins: " + this.getNumCoins();
    }

    /**
     * Defines equality for two Coffer objects.
     *
     * @param o Other object in consideration for equality
     * @return true if o is an instance of Coffer, and they have the same number of coins.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Coffer)) {
            return false;
        }

        Coffer other = (Coffer) o;
        return this.getNumCoins() == other.getNumCoins();
    }
}
