package gin.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import gin.task.*;

/**
 * Handles reading and writing of tasks to a persistent file.
 * Tasks are stored in a pipe-delimited format, one per line.
 */
public class Storage {

    /** Path to the file used for task persistence. */
    private final String filePath;

    /**
     * Constructs a Storage instance for the given file path.
     *
     * @param filePath the path to the persistence file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file. Creates the file and parent directories
     * if they do not yet exist.
     *
     * @return a list of tasks loaded from the file
     * @throws IOException if the file cannot be read
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);

        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
            return tasks;
        }

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String userInput = s.nextLine();
            tasks.add(parseTask(userInput));
        }
        s.close();
        return tasks;
    }

    /**
     * Saves all tasks in the given list to the file, overwriting existing contents.
     * Creates parent directories if they do not yet exist.
     *
     * @param tasks the task list to save
     * @throws IOException if the file cannot be written
     */
    public void saveList(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            fw.write(task.toFileString() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Parses a single line from the save file into a Task object.
     * Returns null if the line is malformed or the task type is unrecognised.
     *
     * @param line a pipe-delimited line from the save file
     * @return the parsed Task, or null if the line is invalid
     */
    private Task parseTask(String userInput) {
        String[] parts = userInput.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task = null;

        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            if (parts.length >= 4) {
                task = new Deadline(description, parts[3]);
            }
            break;
        case "E":
            if (parts.length >= 5) {
                task = new Event(description, parts[3], parts[4]);
            }
            break;
        default:
            break;
        }

        if (task != null && isDone) {
            task.markDone();
        }
        return task;
    }
}
