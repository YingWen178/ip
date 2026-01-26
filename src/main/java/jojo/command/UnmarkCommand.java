package jojo.command;

import jojo.exception.JoJoException;
import jojo.storage.Storage;
import jojo.task.TaskList;
import jojo.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoJoException {
        tasks.unmark(index);
        System.out.println(" OK, I've marked this task as not done yet:\n   " + tasks.get(index));
        storage.save(tasks);
    }
}
