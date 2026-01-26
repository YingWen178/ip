package jojo.command;

import jojo.exception.JoJoException;
import jojo.storage.Storage;
import jojo.task.TaskList;
import jojo.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoJoException {
        tasks.mark(index);
        System.out.println(" Nice! I've marked this task as done:\n   " + tasks.get(index));
        storage.save(tasks);
    }
}