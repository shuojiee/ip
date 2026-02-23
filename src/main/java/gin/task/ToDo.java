package gin.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskIcon() {
        return "[T]";
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.println();
    }

    @Override
    public String toFileString() {
        return "T | " + (getStatus() ? "1" : "0") + " | " + getDescription();
    }
}
