package gin.command;

import gin.storage.Storage;
import gin.task.TaskList;
import gin.ui.Ui;
import gin.exception.GinException;
import java.io.IOException;

/**
 * Represents a command to remove all tasks from the task list.
 */
public class ClearCommand extends Command {

    /**
     * Clears all tasks from the list, saves to storage, and prints a confirmation message.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler for persistence
     * @throws GinException if the task list could not be saved
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GinException {
        int size = tasks.getSize();
        for (int i = size - 1; i >= 0; i--) {
            tasks.deleteTask(i);
        }

        try {
            storage.saveList(tasks);
        } catch (IOException e) {
            throw new GinException("    Failed to save tasks after clearing: " + e.getMessage());
        }
        ui.printMessage("All " + size + " task(s) have been cleared.");
    }
}
