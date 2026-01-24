package jojo.command;

import jojo.storage.Storage;
import jojo.task.TaskList;
import jojo.task.Todo;
import jojo.ui.Ui;

public class AddTodoCommand extends Command {
    private String desc;
    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo t = new Todo(desc);
        tasks.add(t);
        ui.showAddedTask(t, tasks.size());
        storage.save(tasks);
    }
}