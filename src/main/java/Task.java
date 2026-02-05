public class Task {
    private final String description;
    private Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getStatus() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (this.isDone) ? "[X]" : "[ ]";
    }

    public String getTaskIcon() {
        return "[ ]";
    }

    public void printTask() {
        System.out.print("        " + this.getTaskIcon() + this.getStatusIcon() + " " + this.getDescription());
    }
}
