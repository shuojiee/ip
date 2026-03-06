package gin.command;

import gin.task.TaskList;
import gin.ui.Ui;
import gin.storage.Storage;
import gin.exception.GinException;
import java.io.IOException;

/**
 * Represents an abstract executable command.
 * All user commands extend this class and implement {@link #execute}.
 */
public abstract class Command {

    /**
     * Executes this command, mutating the task list, printing output via the UI,
     * and persisting state via storage as appropriate.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler for persistence
     * @throws GinException if a logical error occurs (e.g. invalid index)
     * @throws Exception    if an unexpected error occurs (e.g. I/O failure)
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws GinException, IOException;

    /**
     * Returns whether this command signals the application to exit.
     * Returns false by default; overridden by {@link ExitCommand}.
     *
     * @return true if the application should exit after this command
     */
    public boolean isExit() {
        return false;
    }
}
