package gin.command;

import gin.task.*;
import gin.ui.Ui;
import gin.storage.Storage;
import java.io.IOException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.saveList(tasks);
        ui.printMessage("Got it. I've deleted this task:");
        task.printTask();
        ui.printMessage("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
