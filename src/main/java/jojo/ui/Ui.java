package jojo.ui;

import java.util.Scanner;

import jojo.task.Task;

/**
 * Handles the user interface interactions of the application.
 * Responsible for reading user input and displaying messages to the console.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String WELCOME_MESSAGE = "YARE YARE DAZE... I am JoJo.\nWhat do you want from me?";
    private static final String GOODBYE_MESSAGE = "Arrivederci. Don't bother me again unless it's important.";
    private static final String TASK_ADDED_MSG = "STAR PLATINUM! I've added this task to your stand:";
    private static final String TASK_DELETED_MSG = "ERASED! This task has been removed from existence:";
    private static final String TASK_MARKED_MSG = "YES! I AM! I've marked this task as done:";
    private static final String TASK_UNMARKED_MSG = "MUDA MUDA MUDA! I've marked this task as not done yet:";
    private static final String LOADING_ERROR_MSG = "Good Grief... No file found. I've created a new one: jojo.txt";
    private static final String MATCHING_TASKS_MSG = "Hmph. Here are the tasks that match your search:";
    private static final String NO_MATCHING_TASKS_MSG = "NANI?! No matching tasks found.";
    private static final String LIST_TASKS_MSG = "Here is your list of tasks:";
    private static final String DUPLICATE_TASK_MSG = "KONO DIO DA! This task already exists in your list:";

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
        return HORIZONTAL_LINE + "\n" + WELCOME_MESSAGE + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Returns the goodbye message when the user exits the application.
     */
    public String showGoodbye() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Returns a horizontal divider line.
     */
    public String showLine() {
        return HORIZONTAL_LINE;
    }

    /**
     * Returns a confirmation message after a task has been successfully added.
     *
     * @param task       The task that was added to the list.
     * @param totalTasks The total number of tasks currently in the list.
     */
    public String showAddedTask(Task task, int totalTasks) {
        return TASK_ADDED_MSG + "\n   " + task
                + "\nNow you have " + totalTasks + " tasks in the list.";
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
        return HORIZONTAL_LINE + "\n" + LOADING_ERROR_MSG + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Returns found tasks in the list if tasks matching the keyword are found.
     */
    public String showFoundTasks(java.util.ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return NO_MATCHING_TASKS_MSG;
        } else {
            StringBuilder sb = new StringBuilder(MATCHING_TASKS_MSG);
            for (int i = 0; i < tasks.size(); i++) {
                sb.append("\n").append(i + 1).append(".").append(tasks.get(i));
            }
            return sb.toString();
        }
    }

    /**
     * Returns the list of tasks.
     */
    public String showTaskList(jojo.task.TaskList tasks) {
        StringBuilder sb = new StringBuilder(LIST_TASKS_MSG);
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\n").append(i + 1).append(". ").append(tasks.get(i));
        }
        return sb.toString();
    }

    /**
     * Returns a message after a task has been successfully deleted.
     */
    public String showDeletedTask(Task task, int totalTasks) {
        return TASK_DELETED_MSG + "\n   " + task
                + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Returns a message after a task has been successfully marked.
     */
    public String showMarkedTask(Task task) {
        return TASK_MARKED_MSG + "\n   " + task;
    }

    /**
     * Returns a message after a task has been successfully unmarked.
     */
    public String showUnmarkedTask(Task task) {
        return TASK_UNMARKED_MSG + "\n   " + task;
    }

    /**
     * Returns a message when a duplicate task is detected.
     *
     * @param task The duplicate task.
     */
    public String showDuplicateTask(Task task) {
        return DUPLICATE_TASK_MSG + "\n   " + task;
    }
}
