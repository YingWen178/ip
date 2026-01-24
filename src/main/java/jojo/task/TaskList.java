package jojo.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

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
}
