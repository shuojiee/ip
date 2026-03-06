package gin.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import gin.task.*;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public void saveList(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
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
