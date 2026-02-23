package gin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import gin.exception.GinException;
import gin.task.Deadline;
import gin.task.Event;
import gin.task.Task;
import gin.task.ToDo;
import gin.storage.Storage;

public class Gin {
    private static final String HORIZONTAL_LINE = "_".repeat(60);
    private static final String FILE_PATH = "./data/tasks.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final Storage storage = new Storage(FILE_PATH);

    public static void main(String[] args) {

        try {
            tasks = storage.loadTasks();
        } catch (IOException e) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    Failed to load tasks.");
            System.out.println(HORIZONTAL_LINE);
        }

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
            } catch (GinException | IOException e) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("    " + e.getMessage());
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }

    private static void addTask(String userInput) throws GinException, IOException {
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

    private static void addToDo(String description) throws IOException {
        Task newTask = new ToDo(description);

        tasks.add(newTask);
        saveList();

        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Got it. I've added this task:");
        newTask.printTask();
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addDeadline(String description) throws GinException, IOException {
        String[] deadlineParts = description.split(" /by ", 2);

        if (deadlineParts.length < 2) {
            throw new GinException("    gin.task.Deadline cannot be empty!");
        }

        String deadlineDescription = deadlineParts[0];
        String deadlineTime = deadlineParts[1];

        Task newTask = new Deadline(deadlineDescription, deadlineTime);

        tasks.add(newTask);
        saveList();

        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Got it. I've added this task:");
        newTask.printTask();
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addEvent(String description) throws GinException, IOException {
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

        tasks.add(newTask);
        saveList();

        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Got it. I've added this task:");
        newTask.printTask();
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void deleteTask(String userInput) throws GinException, IOException {
        String[] parts = userInput.split(" ");
        if (parts.length < 2) {
            throw new GinException("    Please specify a task number to delete.");
        }

        int taskIndex = Integer.parseInt(parts[1]);

        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            throw new GinException("    Please input a valid task number.");
        }

        Task removedTask = tasks.remove(taskIndex - 1);
        saveList();

        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Noted, I've removed this task:");
        System.out.println("        ");
        removedTask.printTask();
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    List is empty.");
            System.out.println(HORIZONTAL_LINE);
        } else {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    Here are the tasks in your list:");

            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i - 1);
                System.out.print("    " + i + ". ");
                task.printTask();
            }

            System.out.println(HORIZONTAL_LINE);
        }
    }

    private static void markTask(String userInput, boolean isDone) throws IOException {
        String[] userInputParts = userInput.split(" ");
        int taskIndex = Integer.parseInt(userInputParts[1]);

        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    Invalid task index. Please enter a valid task.");
            System.out.println(HORIZONTAL_LINE);
        } else {
            Task task = tasks.get(taskIndex - 1);

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
            saveList();
        }
    }

    private static void saveList() throws IOException {
        storage.saveList(tasks);
    }
}
