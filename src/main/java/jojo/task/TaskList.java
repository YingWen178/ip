package jojo.task;

import java.util.ArrayList;

/**
 * Manages the list of tasks.
 * Contains operations to add, delete, and modify tasks in the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList initialized with an existing list of tasks.
     *
     * @param tasks The ArrayList of tasks to load.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Initial task list should not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task object to be added.
     */
    public void add(Task task) {
        assert task != null : "Cannot add a null task";
        tasks.add(task);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index The zero-based index of the task to be removed.
     * @return The Task object that was removed.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public Task remove(int index) throws IndexOutOfBoundsException {
        assert index >= 0 : "Index should be non-negative";
        return tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The zero-based index of the task to mark.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public void mark(int index) throws IndexOutOfBoundsException {
        assert index >= 0 : "Index should be non-negative";
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The zero-based index of the task to unmark.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public void unmark(int index) throws IndexOutOfBoundsException {
        assert index >= 0 : "Index should be non-negative";
        tasks.get(index).unmark();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The total count of tasks.
     */
    public int size() {
        assert tasks != null : "Internal tasks list should not be null";
        return tasks.size();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The zero-based index of the task to retrieve.
     * @return The Task object at the specified index.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        assert index >= 0 : "Index should be non-negative";
        return tasks.get(index);
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return The ArrayList containing all tasks.
     */
    public ArrayList<Task> getAll() {
        assert tasks != null : "Internal tasks list should not be null";
        return tasks;
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks matching the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.toString().contains(keyword)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }
}
