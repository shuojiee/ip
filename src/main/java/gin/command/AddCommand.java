package gin.command;

import gin.task.*;
import gin.ui.Ui;
import gin.storage.Storage;
import gin.exception.GinException;
import java.io.IOException;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(task);
        storage.saveList(tasks);
        ui.printMessage("Got it. I've added this task:");
        task.printTask();
        ui.printMessage("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
