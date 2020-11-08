package main.utils;

import java.util.Scanner;

/**
 * Class ScannerInstance is a Singleton class which exposes a Scanner for use
 * by a number of classes throughout this code base.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/8/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class ScannerInstance {
    private static ScannerInstance instance = null;
    private final Scanner scanner;

    /**
     *
     * @return the Singleton instance of this class
     */
    public static ScannerInstance getInstance() {
        if(instance == null) {
            instance = new ScannerInstance();
        }
        return instance;
    }

    /**
     * Private constructor, initializes the internal Scanner.
     */
    private ScannerInstance() {
        scanner = new Scanner(System.in);
    }

    /**
     *
     * @return The Scanner wrapped by this class
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Closes the Scanner. Once closed, the Scanner cannot be reopened.
     */
    public void close() {
        scanner.close();
    }
}
