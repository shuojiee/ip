import java.util.Scanner;

public class Gin {
    private static final String HORIZONTAL_LINE = "_".repeat(60);
    private static final int MAX_NUMBER_OF_TASKS = 100;

    private static final Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Gin.\nWhat can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        while (true) {
            String userInput = scanner.nextLine().trim();
            String command = userInput.split(" ")[0];

            switch (command) {
            case "bye":
                System.out.println(HORIZONTAL_LINE);
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);
                scanner.close();
                return;
            case "list":
                listTasks();
                break;
            case "mark":
                markTask(userInput, true);
                break;
            case "unmark":
                markTask(userInput, false);
                break;
            case "todo":
            case "deadline":
            case "event":
                addTask(userInput);
                break;
            default:
                System.out.println("Please input a valid command of the form [action] [description].");
                break;
            }
        }
    }

    private static void addTask(String userInput) {
        String[] parts = userInput.split(" ", 2);

        if (parts.length < 2) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Task description cannot be empty.");
            System.out.println(HORIZONTAL_LINE);
            return;
        }

        String command = parts[0];
        String description = parts[1];

        switch(command) {
        case "todo":
            addToDo(description);
            break;
        case "deadline":
            addDeadline(description);
            break;
        case "event":
            addEvent(description);
            break;
        default:
            System.out.println("Please input a valid command of the form: [task type] [description].");
            break;
        }
    }

    private static void addToDo(String description) {
        Task newTask = new ToDo(description);

        tasks[taskCount] = newTask;
        taskCount++;

        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Got it. I've added this task:");
        newTask.printTask();
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addDeadline(String description) {
        String[] deadlineParts = description.split(" /by ", 2);
        if (deadlineParts.length < 2) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    Deadline format should be: deadline [description] /by [time]");
            System.out.println(HORIZONTAL_LINE);
            return;
        }

        String deadlineDescription = deadlineParts[0];
        String deadlineTime = deadlineParts[1];

        Task newTask = new Deadline(deadlineDescription, deadlineTime);

        tasks[taskCount] = newTask;
        taskCount++;

        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Got it. I've added this task:");
        newTask.printTask();
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addEvent(String description) {
        String[] eventParts = description.split(" /from ", 2);
        if (eventParts.length < 2) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    Event format should be: event [description] /from [start time] /to [end time]");
            System.out.println(HORIZONTAL_LINE);
            return;
        }

        String[] eventTimeParts = eventParts[1].split(" /to ", 2);
        if (eventTimeParts.length < 2) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    Event format should be: event [description] /from [start time] /to [end time]");
            System.out.println(HORIZONTAL_LINE);
            return;
        }

        String eventDescription = eventParts[0];
        String eventStartTime = eventTimeParts[0];
        String eventEndTime = eventTimeParts[1];

        Task newTask = new Event(eventDescription, eventStartTime, eventEndTime);

        tasks[taskCount] = newTask;
        taskCount++;

        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Got it. I've added this task:");
        newTask.printTask();
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void listTasks() {
        if (taskCount <= 0) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    list is empty.");
            System.out.println(HORIZONTAL_LINE);
        } else {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    Here are the tasks in your list:");

            for (int i = 1; i <= taskCount; i++) {
                Task task = tasks[i - 1];
                System.out.print("    " + i + ". ");
                task.printTask();
            }

            System.out.println(HORIZONTAL_LINE);
        }
    }

    private static void markTask(String userInput, boolean isDone) {
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


