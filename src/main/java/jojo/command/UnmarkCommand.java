package jojo.command;

import jojo.exception.JoJoException;
import jojo.storage.Storage;
import jojo.task.TaskList;
import jojo.ui.Ui;

/**
 * Represents a command to mark a previously completed task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates a command to unmark the task at the specified index.
     *
     * @param index The zero-based index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task as not done,
     * displaying a confirmation message, and saving the updated list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws JoJoException If the index provided is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JoJoException {
        tasks.unmark(index);
        storage.save(tasks);
        return ui.showUnmarkedTask(tasks.get(index));
    }
}
