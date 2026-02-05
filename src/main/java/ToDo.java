public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskIcon() {
        return "[T]";
    }

    public void printTask() {
        super.printTask();
        System.out.println();
    }
}
