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
     * Displays the welcome message and a horizontal line to the user.
     */
    public void showHello() {
        System.out.println(horizontalLine);
        System.out.println(" Hello! I'm JoJo");
        System.out.println(" What can I do for you?");
        System.out.println(horizontalLine);
    }

    /**
     * Displays the goodbye message when the user exits the application.
     */
    public void showGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * Prints a horizontal divider line to the console.
     */
    public void showLine() {
        System.out.println(horizontalLine);
    }

    /**
     * Displays a confirmation message after a task has been successfully added.
     *
     * @param task       The task that was added to the list.
     * @param totalTasks The total number of tasks currently in the list.
     */
    public void showAddedTask(Task task, int totalTasks) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
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
     * Prints the error message to the console.
     *
     * @param msg The error message to be displayed.
     */
    public void showErr(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays an error message indicating that the storage file could not be loaded
     * and a new file has been created.
     */
    public void showLoadingError() {
        showLine();
        System.out.println(" No file found. Created new file: jojo.txt");
        showLine();
    }
}
