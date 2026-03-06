package gin.command;

import java.io.IOException;
import gin.exception.GinException;
import gin.ui.Ui;
import gin.storage.Storage;
import gin.task.Task;
import gin.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            ui.printMessage("List is empty.");
        } else {
            ui.printMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                System.out.print("    " + (i + 1) + ". ");
                task.printTask();
            }
        }
    }
}
