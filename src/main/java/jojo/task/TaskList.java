package jojo.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    public void mark(int index) throws IndexOutOfBoundsException {
        tasks.get(index).markAsDone();
    }

    public void unmark(int index) throws IndexOutOfBoundsException {
        tasks.get(index).unmark();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    public ArrayList<Task> getAll() {
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
