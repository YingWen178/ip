package jojo.command;

import jojo.storage.Storage;
import jojo.task.TaskList;
import jojo.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command by displaying the goodbye message.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    /**
     * Returns true to indicate that the application should exit.
     *
     * @return Always returns true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
