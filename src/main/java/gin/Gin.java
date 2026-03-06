package gin;

import gin.storage.Storage;
import gin.task.TaskList;
import gin.ui.Ui;
import gin.parser.Parser;
import gin.command.Command;
import gin.exception.GinException;

public class Gin {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Gin(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            ui.printLoadFileError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                ui.showLine();
                Command c = Parser.parse(userInput);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (GinException e) {
                ui.printError(e.getMessage());
            } catch (Exception e) {
                ui.printError("An unexpected error occurred.");
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Gin("./data/tasks.txt").run();
    }
}
