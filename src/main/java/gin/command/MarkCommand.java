package gin.command;

import gin.task.TaskList;
import gin.task.Task;
import gin.ui.Ui;
import gin.storage.Storage;
import gin.exception.GinException;
import java.io.IOException;

public class MarkCommand extends Command {
    private final int index;
    private final boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GinException, IOException {
        try {
            Task task = tasks.getTask(index);
            if (isDone) {
                task.markDone();
                ui.printMessage("Nice! I've marked this task as done:");
            } else {
                task.markNotDone();
                ui.printMessage("OK, I've marked this task as not done:");
            }

            task.printTask();
            storage.saveList(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new GinException("    Please enter a valid task index.");
        }
    }
}
