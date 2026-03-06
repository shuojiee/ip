package gin.command;

import gin.exception.GinException;
import gin.storage.Storage;
import gin.task.Task;
import gin.task.TaskList;
import gin.ui.Ui;

/**
 * Represents a command to search for tasks whose descriptions contain a given keyword.
 */
public class FindCommand extends Command {

    /** The keyword to search for in task descriptions. */
    private final String keyword;

    /**
     * Constructs a FindCommand with the given search keyword.
     *
     * @param keyword the keyword to search for (case-insensitive)
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches the task list for tasks matching the keyword and prints the results.
     * If no matches are found, prints a message indicating so.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler (unused)
     * @throws GinException if an error occurs during search
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GinException {
        TaskList results = tasks.find(keyword);
        if (results.getSize() == 0) {
            ui.printMessage("No matching tasks found.");
            return;
        }
        ui.printMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < results.getSize(); i++) {
            System.out.print("    " + (i + 1) + ". ");
            results.getTask(i).printTask();
        }
    }
}
