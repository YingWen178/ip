package jojo.command;

import jojo.exception.JoJoException;
import jojo.storage.Storage;
import jojo.task.Task;
import jojo.task.TaskList;
import jojo.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a command to delete the task at the specified index.
     *
     * @param index The zero-based index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by removing the task, showing a message, and saving.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws JoJoException If the index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoJoException {
        Task removedTask = tasks.remove(index);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removedTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
    }
}