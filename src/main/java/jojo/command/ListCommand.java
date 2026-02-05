package jojo.command;

import jojo.storage.Storage;
import jojo.task.TaskList;
import jojo.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by iterating through the task list and printing each task.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
