package jojo.command;

import jojo.storage.Storage;
import jojo.task.Task;
import jojo.task.TaskList;
import jojo.ui.Ui;
import java.util.ArrayList;

/**
 * Represents a command to find tasks by a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.find(keyword);
        ui.showFoundTasks(foundTasks);
    }
}