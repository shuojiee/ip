import java.util.Scanner;

public class Gin {
    private static final String HORIZONTAL_LINE = "_".repeat(60);
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;

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
            } else if (userInput.equals("list")) {
                listTasks();
            } else if (userInput.startsWith("mark ")) {
                markTask(userInput, true);
            } else if (userInput.startsWith("unmark ")) {
                markTask(userInput, false);
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    private static void addTask(String description) {
        tasks[taskCount] = new Task(description);

        System.out.println(HORIZONTAL_LINE);
        System.out.println("    added: " + description);
        System.out.println(HORIZONTAL_LINE);

        taskCount++;
    }

    private static void listTasks() {
        if (taskCount <= 0) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    list is empty.");
            System.out.println(HORIZONTAL_LINE);
        } else {
            System.out.println(HORIZONTAL_LINE);

            for (int i = 1; i <= taskCount; i++) {
                Task task = tasks[i - 1];
                System.out.println("    " + i + ". " + task.getStatusIcon() + " " + task.getDescription());
            }

            System.out.println(HORIZONTAL_LINE);
        }
    }

    private static void markTask(String userInput, Boolean isDone) {
        String[] userInputParts = userInput.split(" ");
        int taskIndex = Integer.parseInt(userInputParts[1]);

        if (taskIndex <= 0 || taskIndex > taskCount) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    Invalid task index. Please enter a valid task.");
            System.out.println(HORIZONTAL_LINE);
        } else {
            Task task = tasks[taskIndex - 1];

            if (isDone) {
                task.markDone();
                System.out.println(HORIZONTAL_LINE);
                System.out.println("    Nice! I've marked this task as done:");
            } else {
                task.markNotDone();
                System.out.println(HORIZONTAL_LINE);
                System.out.println("    OK, I've marked this task as not done yet:");
            }
            System.out.println("        " + task.getStatusIcon() + " " + task.getDescription());
            System.out.println(HORIZONTAL_LINE);
        }
    }
}


