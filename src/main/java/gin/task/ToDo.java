package gin.task;

/**
 * Represents a basic to-do task with no date or time attached.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description the description of the to-do task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the type icon for a ToDo task.
     *
     * @return "[T]"
     */
    @Override
    public String getTaskIcon() {
        return "[T]";
    }

    /**
     * Prints this task to standard output with its index, type, status, and description.
     *
     * @param index the 1-based display index of this task
     */
    @Override
    public void printTask() {
        super.printTask();
        System.out.println();
    }

    /**
     * Returns a formatted string for saving this task to file.
     *
     * @return file-format string in the form "T | done | description"
     */
    @Override
    public String toFileString() {
        return "T | " + (getStatus() ? "1" : "0") + " | " + getDescription();
    }
}
