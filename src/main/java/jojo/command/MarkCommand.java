package jojo.command;

import jojo.exception.JoJoException;
import jojo.storage.Storage;
import jojo.task.TaskList;
import jojo.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a command to mark the task at the specified index.
     *
     * @param index The zero-based index of the task.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task as done and saving the list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws JoJoException If the index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JoJoException {
        tasks.mark(index);
        storage.save(tasks);
        return ui.showMarkedTask(tasks.get(index));
    }
}
