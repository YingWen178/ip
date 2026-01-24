package jojo.command;

import jojo.exception.JoJoException;
import jojo.storage.Storage;
import jojo.task.TaskList;
import jojo.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JoJoException;

    public boolean isExit() {
        return false;
    }
}