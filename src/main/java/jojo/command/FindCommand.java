package jojo.command;

import java.util.ArrayList;

import jojo.storage.Storage;
import jojo.task.Task;
import jojo.task.TaskList;
import jojo.ui.Ui;

/**
 * Represents a command to find tasks by a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a command to find tasks containing the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching for tasks that match the keyword.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @return A message containing the found tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.find(keyword);
        return ui.showFoundTasks(foundTasks);
    }
}
