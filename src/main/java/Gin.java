import java.util.Scanner;

public class Gin {
    private static final String HORIZONTAL_LINE = "_".repeat(60);
    private static String[] list = new String[100];
    private static int stringCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Gin.\nWhat can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);
                break;
            }

            if (userInput.equals("list")) {
                if (stringCount <= 0) {
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("    list is empty");
                    System.out.println(HORIZONTAL_LINE);
                } else {
                    System.out.println(HORIZONTAL_LINE);

                    for (int i = 1; i <= stringCount; i++) {
                        System.out.println("    " + i + ". " + list[i - 1]);
                    }

                    System.out.println(HORIZONTAL_LINE);
                }
            } else {
                list[stringCount] = userInput;

                System.out.println(HORIZONTAL_LINE);
                System.out.println("    added: " + userInput);
                System.out.println(HORIZONTAL_LINE);

                stringCount++;
            }
        }

        scanner.close();
    }
}


