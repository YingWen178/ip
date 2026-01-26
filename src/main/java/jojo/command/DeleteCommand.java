package jojo.command;

import jojo.exception.JoJoException;
import jojo.storage.Storage;
import jojo.task.Task;
import jojo.task.TaskList;
import jojo.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoJoException {
        Task removedTask = tasks.remove(index);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removedTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
    }
}