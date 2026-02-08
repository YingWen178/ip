package jojo;

import jojo.command.Command;
import jojo.exception.JoJoException;
import jojo.parser.Parser;
import jojo.storage.Storage;
import jojo.task.TaskList;
import jojo.ui.Ui;

/**
 * Represents the main entry point for the JoJo task management application.
 * JoJo is a personal assistant chatbot that helps users keep track of various tasks.
 */
public class JoJo {
    private static final String FILE_PATH = "jojo.txt";
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Initializes a new JoJo instance with the specified file path for data storage.
     * If the storage file cannot be loaded, a new empty task list is initialized.
     *
     * @param filePath The path to the file where tasks are saved and loaded from.
     */
    public JoJo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        assert ui != null : "Ui should be initialized";
        assert storage != null : "Storage should be initialized";
        try {
            tasks = new TaskList(storage.load());
        } catch (java.io.IOException e) {
            System.out.println(ui.showLoadingError());
            tasks = new TaskList();
        }
        assert tasks != null : "TaskList should be initialized";
    }

    /**
     * Generates a response to user input.
     *
     * @param input The user input string.
     * @return The response message from JoJo.
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be null";
        try {
            Command c = Parser.parse(input);
            assert c != null : "Parser should return a valid Command";
            return c.execute(tasks, ui, storage);
        } catch (JoJoException e) {
            return ui.showErr(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            return ui.showErr(" OOPS!!! That task number doesn't exist in your list.");
        }
    }

    /**
     * Checks if the given input is an exit command.
     *
     * @param input The user input string.
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit(String input) {
        try {
            Command c = Parser.parse(input);
            return c.isExit();
        } catch (JoJoException e) {
            return false;
        }
    }

    /**
     * The main method that serves as the starting point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new JoJo(FILE_PATH).run();
    }

    /**
     * Starts the application loop.
     * Reads user commands, parses them, and executes the corresponding actions
     * until an exit command is received.
     */
    public void run() {
        System.out.println(ui.showHello());
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCmd();
                Command c = Parser.parse(fullCommand);
                String response = c.execute(tasks, ui, storage);
                System.out.println(ui.showLine());
                System.out.println(response);

                isExit = c.isExit();
            } catch (JoJoException e) {
                System.out.println(ui.showErr(e.getMessage()));
            } catch (IndexOutOfBoundsException e) {
                System.out.println(ui.showErr(" OOPS!!! That task number doesn't exist in your list."));
            } finally {
                if (!isExit) {
                    System.out.println(ui.showLine());
                }
            }
        }
    }

}
