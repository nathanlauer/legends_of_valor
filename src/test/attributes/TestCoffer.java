package test.attributes;

import main.utils.Coffer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class TestCoffer
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/2/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class TestCoffer {
    @Test
    public void setNumCoins() {
        Coffer coffer = new Coffer(10);
        assertEquals(10, coffer.getNumCoins());

        coffer.setNumCoins(30);
        assertEquals(30, coffer.getNumCoins());

        // Coffers can actually become negative
        coffer.setNumCoins(-5);
        assertEquals(-5, coffer.getNumCoins());
    }

    @Test
    public void addCoins() {
        Coffer coffer = new Coffer(10);
        assertEquals(10, coffer.getNumCoins());

        coffer.addCoins(10);
        assertEquals(20, coffer.getNumCoins());

        try {
            coffer.addCoins(-15);
            fail();
        } catch (IllegalArgumentException e) {
            // passed
            assertEquals(20, coffer.getNumCoins());
        }
    }

    @Test
    public void removeCoins() {
        Coffer coffer = new Coffer(10);
        assertEquals(10, coffer.getNumCoins());

        coffer.removeCoins(5);
        assertEquals(5, coffer.getNumCoins());

        try {
            coffer.removeCoins(-2);
            fail();
        } catch (IllegalArgumentException e) {
            // passed
            assertEquals(5, coffer.getNumCoins());
        }
    }


}
