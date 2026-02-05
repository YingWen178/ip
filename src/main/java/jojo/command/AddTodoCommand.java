package jojo.command;

import jojo.storage.Storage;
import jojo.task.TaskList;
import jojo.task.Todo;
import jojo.ui.Ui;

/**
 * Represents a command to add a new Todo task.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Creates a command to add a Todo task.
     *
     * @param description The description of the todo.
     */
    public AddTodoCommand(String description) {
        this.description = description;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Todo t = new Todo(description);
        tasks.add(t);
        storage.save(tasks);
        return ui.showAddedTask(t, tasks.size());
    }
}
