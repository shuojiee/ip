package gin;

import java.util.Scanner;
import gin.exception.GinException;
import gin.task.Deadline;
import gin.task.Event;
import gin.task.Task;
import gin.task.ToDo;

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

            try {
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
                case "delete":
                    deleteTask(userInput);
                    break;
                default:
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("    Please input a valid command of the form [action] [description].");
                    System.out.println(HORIZONTAL_LINE);
                    break;
                }
            } catch (GinException e) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("    " + e.getMessage());
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }

    private static void addTask(String userInput) throws GinException {
        String[] parts = userInput.split(" ", 2);

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new GinException("    The description of a task cannot be empty!");
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
            System.out.println("    Please input a valid command of the form: [task type] [description].");
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

    private static void addDeadline(String description) throws GinException {
        String[] deadlineParts = description.split(" /by ", 2);

        if (deadlineParts.length < 2) {
            throw new GinException("    gin.task.Deadline cannot be empty!");
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

    private static void addEvent(String description) throws GinException {
        String[] eventParts = description.split(" /from ", 2);
        if (eventParts.length < 2) {
            throw new GinException("    gin.task.Event input must contain both the description and times!");
        }

        String[] eventTimeParts = eventParts[1].split(" /to ", 2);
        if (eventTimeParts.length < 2) {
            throw new GinException("    gin.task.Event input must contain both the start time and end time!");
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

    private static void deleteTask(String userInput) throws GinException {
        String[] parts = userInput.split(" ");
        if (parts.length < 2) {
            throw new GinException("    Please specify a task number to delete.");
        }

        int taskIndex = Integer.parseInt(parts[1]);

        if (taskIndex <= 0 || taskIndex > taskCount) {
            throw new GinException("    Please input a valid task number.");
        }

        Task removedTask = tasks[taskIndex - 1];

        for (int i = taskIndex - 1; i < taskCount - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[taskCount - 1]= null;
        taskCount--;

        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Noted, I've removed this task:");
        System.out.println("        ");
        removedTask.printTask();
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void listTasks() {
        if (taskCount <= 0) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    List is empty.");
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


