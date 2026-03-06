package gin.command;

import gin.task.*;
import gin.ui.Ui;
import gin.storage.Storage;
import java.io.IOException;

/**
 * Represents a command to delete a specific task from the task list by index.
 */
public class DeleteCommand extends Command {

    /** The 0-based index of the task to delete. */
    private final int index;

    /**
     * Constructs a DeleteCommand for the task at the given 0-based index.
     *
     * @param index the 0-based index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at the stored index, saves to storage, and prints a confirmation message.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler for persistence
     * @throws GinException if the index is out of range or the list could not be saved
     */
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
