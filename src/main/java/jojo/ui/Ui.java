package jojo.ui;

import java.util.Scanner;

import jojo.task.Task;

/**
 * Handles the user interface interactions of the application.
 * Responsible for reading user input and displaying messages to the console.
 */
public class Ui {
    private final String horizontalLine = "____________________________________________________________";
    private final Scanner scanner;

    /**
     * Initializes the UI component and sets up the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the welcome message and a horizontal line to the user.
     */
    public String showHello() {
        return horizontalLine + "\n Hello! I'm JoJo\n What can I do for you?\n" + horizontalLine;
    }

    /**
     * Returns the goodbye message when the user exits the application.
     */
    public String showGoodbye() {
        return " Bye. Hope to see you again soon!";
    }

    /**
     * Returns a horizontal divider line.
     */
    public String showLine() {
        return horizontalLine;
    }

    /**
     * Returns a confirmation message after a task has been successfully added.
     *
     * @param task       The task that was added to the list.
     * @param totalTasks The total number of tasks currently in the list.
     */
    public String showAddedTask(Task task, int totalTasks) {
        return " Got it. I've added this task:\n   " + task
                + "\n Now you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Reads the command entered by the user.
     *
     * @return The full command string entered by the user.
     */
    public String readCmd() {
        return scanner.nextLine();
    }

    /**
     * Returns the error message.
     *
     * @param msg The error message to be displayed.
     */
    public String showErr(String msg) {
        return msg;
    }

    /**
     * Returns an error message indicating that the storage file could not be loaded
     * and a new file has been created.
     */
    public String showLoadingError() {
        return horizontalLine + "\n No file found. Created new file: jojo.txt\n" + horizontalLine;
    }

    /**
     * Returns found tasks in the list if tasks matching the keyword are found.
     */
    public String showFoundTasks(java.util.ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return " No matching tasks found.";
        } else {
            StringBuilder sb = new StringBuilder(" Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append("\n ").append(i + 1).append(".").append(tasks.get(i));
            }
            return sb.toString();
        }
    }

    /**
     * Returns the list of tasks.
     */
    public String showTaskList(jojo.task.TaskList tasks) {
        StringBuilder sb = new StringBuilder(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\n ").append(i + 1).append(". ").append(tasks.get(i));
        }
        return sb.toString();
    }

    /**
     * Returns a message after a task has been successfully deleted.
     */
    public String showDeletedTask(Task task, int totalTasks) {
        return " Noted. I've removed this task:\n   " + task
                + "\n Now you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Returns a message after a task has been successfully marked.
     */
    public String showMarkedTask(Task task) {
        return " Nice! I've marked this task as done:\n   " + task;
    }

    /**
     * Returns a message after a task has been successfully unmarked.
     */
    public String showUnmarkedTask(Task task) {
        return " OK, I've marked this task as not done yet:\n   " + task;
    }
}
