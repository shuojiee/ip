package gin.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import gin.task.Task;
import gin.task.Deadline;
import gin.task.Event;
import gin.task.ToDo;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String userInput = s.nextLine();
            tasks.add(parseTask(userInput));
        }
        s.close();
        return tasks;
    }

    public void saveList(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.toFileString() + System.lineSeparator());
        }
        fw.close();
    }

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
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            task = new Event(description, parts[3], parts[4]);
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
