package jojo.task;

import java.util.ArrayList;

/**
 * Manages the list of tasks.
 * Contains operations to add, delete, and modify tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

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
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task object to be added.
     */
    public void add(Task task) {
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
        return tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The zero-based index of the task to mark.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public void mark(int index) throws IndexOutOfBoundsException {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The zero-based index of the task to unmark.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public void unmark(int index) throws IndexOutOfBoundsException {
        tasks.get(index).unmark();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The total count of tasks.
     */
    public int size() {
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
        return tasks.get(index);
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return The ArrayList containing all tasks.
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }
}
