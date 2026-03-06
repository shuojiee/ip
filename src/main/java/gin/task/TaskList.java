package gin.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to add, delete, retrieve, and search tasks.
 */
public class TaskList {

    /** The internal list of tasks. */
    private final ArrayList<Task> tasks;

    /** Constructs an empty TaskList. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList from an existing list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the given 0-based index.
     *
     * @param index the 0-based index of the task to remove
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return size of the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at the given 0-based index.
     *
     * @param index the 0-based index of the task
     * @return the task at the given index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns a new TaskList containing all tasks whose descriptions
     * contain the given keyword (case-insensitive).
     *
     * @param keyword the search keyword
     * @return a TaskList of matching tasks
     */
    public TaskList find(String keyword) {
        TaskList results = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.addTask(task);
            }
        }
        return results;
    }
}
