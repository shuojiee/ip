package gin.command;

import gin.task.TaskList;
import gin.task.Task;
import gin.ui.Ui;
import gin.storage.Storage;
import gin.exception.GinException;
import java.io.IOException;

/**
 * Represents a command to mark or unmark a specific task as done.
 */
public class MarkCommand extends Command {

    /** The 0-based index of the task to mark or unmark. */
    private final int index;

    /** Whether to mark the task as done (true) or not done (false). */
    private final boolean isDone;

    /**
     * Constructs a MarkCommand for the task at the given 0-based index.
     *
     * @param index    the 0-based index of the task to mark or unmark
     * @param markDone true to mark as done, false to mark as not done
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Marks or unmarks the task, saves to storage, and prints a confirmation message.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler for persistence
     * @throws GinException if the index is out of range or the list could not be saved
     */
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
