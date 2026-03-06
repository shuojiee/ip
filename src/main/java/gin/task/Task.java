package gin.task;

/**
 * Represents an abstract task with a description and completion status.
 * Subclasses define the specific task type (e.g. ToDo, Deadline, Event).
 */
public class Task {

    /** The description of this task. */
    private final String description;

    /** Whether this task has been marked as done. */
    private Boolean isDone;

    /**
     * Constructs a Task with the given description, initially not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Marks this task as done. */
    public void markDone() {
        this.isDone = true;
    }

    /** Marks this task as not done. */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether this task is done.
     *
     * @return true if done, false otherwise
     */
    public Boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns the status icon for this task.
     *
     * @return "[X]" if done, "[ ]" otherwise
     */
    public String getStatusIcon() {
        return (this.isDone) ? "[X]" : "[ ]";
    }

    /**
     * Returns the type icon for this task.
     * Subclasses should override this to return their specific icon.
     *
     * @return "[?]" by default
     */
    public String getTaskIcon() {
        return "[?]";
    }

    /**
     * Prints this task to standard output, including its index, type icon,
     * status icon, and description.
     *
     * @param index the 1-based display index of this task
     */
    public void printTask() {
        System.out.print("        " + this.getTaskIcon() + this.getStatusIcon() + " " + this.getDescription());
    }

    /**
     * Returns a formatted string suitable for saving this task to file.
     * Subclasses must override this to include their specific fields.
     *
     * @return file-format string representation of this task
     */
    public String toFileString() {
        return "? | " + (getStatus() ? "1" : "0") + " | " + description;
    }
}
