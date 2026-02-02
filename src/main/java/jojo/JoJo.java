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
        try {
            tasks = new TaskList(storage.load());
        } catch (java.io.IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
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
        ui.showHello();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCmd();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);

                isExit = c.isExit();
            } catch (JoJoException e) {
                ui.showErr(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.showErr(" OOPS!!! That task number doesn't exist in your list.");
            } finally {
                ui.showLine();
            }
        }
    }

}
