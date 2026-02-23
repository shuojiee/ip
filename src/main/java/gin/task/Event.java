package gin.task;

public class Event extends Task {
    private String from;
    private String to;

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

    @Override
    public String getTaskIcon() {
        return "[E]";
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.println(" " + this.getTime());
    }

    @Override
    public String toFileString() {
        return "E | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + from + " | " + to;
    }
}
