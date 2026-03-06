package gin.ui;

import java.util.Scanner;

/**
 * Handles all user-facing input and output for the Gin application.
 */
public class Ui {

    /** Scanner for reading user input from standard input. */
    private final Scanner scanner;

    /** Horizontal separator line printed between interactions. */
    private final String HORIZONTAL_LINE = "-".repeat(60);

    /** Constructs a Ui instance with a new Scanner on standard input. */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the user, trimming leading/trailing whitespace.
     *
     * @return the trimmed input string
     */
    public String readInput() {
        return scanner.nextLine().trim();
    }

    /** Prints the greeting message shown at application startup. */
    public void printGreeting() {
        showLine();
        System.out.println("    Hello! I'm Gin.\n    What can I do for you?");
        showLine();
    }

    /** Prints a horizontal separator line. */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints an error message to standard output.
     *
     * @param message the error message to display
     */
    public void printError(String message) {
        System.out.println("    " + message);
    }

    /** Prints an error message indicating that the save file could not be loaded. */
    public void printLoadFileError() {
        System.out.println("    Failed to load tasks from file. Starting with an empty list.");
    }

    /**
     * Prints a general message to standard output.
     *
     * @param message the message to display
     */
    public void printMessage(String message) {
        System.out.println("    " + message);
    }
}
