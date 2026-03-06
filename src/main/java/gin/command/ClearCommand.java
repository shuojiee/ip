package gin.command;

import gin.storage.Storage;
import gin.task.TaskList;
import gin.ui.Ui;
import gin.exception.GinException;
import java.io.IOException;

public class ClearCommand extends Command {
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
