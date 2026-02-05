public class Deadline extends Task {
    private String by;

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

    @Override
    public String getTaskIcon() {
        return "[D]";
    }

    public void printTask() {
        super.printTask();
        System.out.println(" " + this.getBy());
    }
}
