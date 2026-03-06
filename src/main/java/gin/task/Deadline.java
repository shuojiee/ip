package gin.task;

/**
 * Represents a task that must be completed by a specific date.
 */
public class Deadline extends Task {

    /** The date and/or time by which this task must be completed. */
    private String by;

    /**
     * Constructs a Deadline task with the given description and due date string.
     *
     * @param description the description of the task
     * @param by          the due date string in a recognised format (e.g. yyyy-MM-dd)
     * @throws GinException if the date string cannot be parsed
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return "(by: " + by + ")";
    }

    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Returns the type icon for a Deadline task.
     *
     * @return "[D]"
     */
    @Override
    public String getTaskIcon() {
        return "[D]";
    }

    /**
     * Prints this task to standard output with its index, type, status, description, and due date.
     *
     * @param index the 1-based display index of this task
     */
    @Override
    public void printTask() {
        super.printTask();
        System.out.println(" " + this.getBy());
    }

    /**
     * Returns a formatted string for saving this task to file.
     *
     * @return file-format string in the form "D | done | description | yyyy-MM-dd"
     */
    @Override
    public String toFileString() {
        return "D | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + by;
    }
}
