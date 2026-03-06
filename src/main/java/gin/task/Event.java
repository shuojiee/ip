package gin.task;

/**
 * Represents a task that spans a specific date range.
 */
public class Event extends Task {

    /** The start date and/or time of this event. */
    private String from;

    /** The end date and/or time of this event. */
    private String to;

    /**
     * Constructs an Event task with the given description, start date, and end date.
     *
     * @param description the description of the event
     * @param from        the start date string in a recognised format (e.g. yyyy-MM-dd)
     * @param to          the end date string in a recognised format (e.g. yyyy-MM-dd)
     * @throws GinException if either date string cannot be parsed
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getTime() {
        return "(from: " + from + " to: " + to + ")";
    }

    public void setTime(String from, String to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type icon for an Event task.
     *
     * @return "[E]"
     */
    @Override
    public String getTaskIcon() {
        return "[E]";
    }

    /**
     * Prints this task to standard output with its index, type, status, description, and date range.
     *
     * @param index the 1-based display index of this task
     */
    @Override
    public void printTask() {
        super.printTask();
        System.out.println(" " + this.getTime());
    }

    /**
     * Returns a formatted string for saving this task to file.
     *
     * @return file-format string in the form "E | done | description | yyyy-MM-dd | yyyy-MM-dd"
     */
    @Override
    public String toFileString() {
        return "E | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + from + " | " + to;
    }
}
