package gin;

import gin.storage.Storage;
import gin.task.TaskList;
import gin.ui.Ui;
import gin.parser.Parser;
import gin.command.Command;
import gin.exception.GinException;

/**
 * Main class for the Gin chatbot application.
 * Initialises the UI, storage, and task list, then runs the main input loop.
 */
public class Gin {

    /** The list of tasks managed during this session. */
    private TaskList tasks;

    /** Handles file reading and writing for task persistence. */
    private final Storage storage;

    /** Handles user input and output. */
    private final Ui ui;

    /**
     * Constructs a Gin instance, loading tasks from the given file path.
     * If the file cannot be loaded, starts with an empty task list.
     *
     * @param filepath path to the file used for task persistence
     */
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

    /**
     * Starts the main loop, reading and executing commands until the user exits.
     */
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

    /**
     * Entry point of the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Gin("./data/tasks.txt").run();
    }
}
