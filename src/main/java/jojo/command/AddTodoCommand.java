package jojo.command;

import jojo.storage.Storage;
import jojo.task.TaskList;
import jojo.task.Todo;
import jojo.ui.Ui;

public class AddTodoCommand extends Command {
    private final String description;
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo t = new Todo(description);
        tasks.add(t);
        ui.showAddedTask(t, tasks.size());
        storage.save(tasks);
    }
}