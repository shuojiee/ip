package gin.command;

import java.io.IOException;
import gin.exception.GinException;
import gin.ui.Ui;
import gin.storage.Storage;
import gin.task.Task;
import gin.task.TaskList;

/**
 * Represents a command to display all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Prints all tasks in the list to standard output, numbered from 1.
     * If the list is empty, prints a message indicating so.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler (unused)
     */
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
