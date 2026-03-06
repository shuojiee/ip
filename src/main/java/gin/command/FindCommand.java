package gin.command;

import gin.exception.GinException;
import gin.storage.Storage;
import gin.task.Task;
import gin.task.TaskList;
import gin.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

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
