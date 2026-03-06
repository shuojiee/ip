package gin.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    private final String HORIZONTAL_LINE = "-".repeat(60);

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readInput() {
        return scanner.nextLine().trim();
    }

    public void printGreeting() {
        showLine();
        System.out.println("    Hello! I'm Gin.\n    What can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void printError(String message) {
        System.out.println("    " + message);
    }

    public void printLoadFileError() {
        System.out.println("    Failed to load tasks from file. Starting with an empty list.");
    }

    public void printMessage(String message) {
        System.out.println("    " + message);
    }
}
