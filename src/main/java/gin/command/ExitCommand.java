package gin.command;

import gin.task.TaskList;
import gin.ui.Ui;
import gin.storage.Storage;

/**
 * Represents a command to exit the Gin application.
 */
public class ExitCommand extends Command {

    /**
     * Prints a farewell message to the user.
     *
     * @param tasks   the current task list (unused)
     * @param ui      the UI handler for output
     * @param storage the storage handler (unused)
     */
   @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Signals the application to exit after this command.
     *
     * @return true always
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
