package jojo.command;

import jojo.storage.Storage;
import jojo.task.TaskList;
import jojo.task.Todo;
import jojo.ui.Ui;

/**
 * Represents a command to add a new Todo task.
 */
public class AddTodoCommand extends Command {
    private String desc;

    /**
     * Creates a command to add a Todo task.
     *
     * @param desc The description of the todo.
     */
    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the command by adding the Todo task to the task list,
     * displaying a success message, and saving the updated list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo t = new Todo(desc);
        tasks.add(t);
        ui.showAddedTask(t, tasks.size());
        storage.save(tasks);
    }
}