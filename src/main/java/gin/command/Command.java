package gin.command;

import gin.task.TaskList;
import gin.ui.Ui;
import gin.storage.Storage;
import gin.exception.GinException;
import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws GinException, IOException;

    public boolean isExit() {
        return false;
    }
}
