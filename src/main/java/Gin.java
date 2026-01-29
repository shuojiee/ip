import java.util.Scanner;

public class Gin {
    private static final String HORIZONTAL_LINE = "_".repeat(60);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Gin.\nWhat can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);
                break;
            }

            System.out.println(HORIZONTAL_LINE);
            System.out.println("    " + userInput);
            System.out.println(HORIZONTAL_LINE);
        }

        scanner.close();
    }
}


