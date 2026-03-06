package gin.command;

import gin.task.*;
import gin.ui.Ui;
import gin.storage.Storage;
import gin.exception.GinException;
import java.io.IOException;

/**
 * Represents a command to add a new task to the task list.
 */
public class AddCommand extends Command {

    /** The task to be added. */
    private final Task task;

    /**
     * Constructs an AddCommand with the task to add.
     *
     * @param task the task to add to the list
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the list, saves to storage, and prints a confirmation message.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler for persistence
     * @throws GinException if the task list could not be saved
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(task);
        storage.saveList(tasks);
        ui.printMessage("Got it. I've added this task:");
        task.printTask();
        ui.printMessage("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
