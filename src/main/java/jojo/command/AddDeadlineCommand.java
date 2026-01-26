package jojo.command;

import jojo.storage.Storage;
import jojo.task.Deadline;
import jojo.task.TaskList;
import jojo.ui.Ui;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline d = new Deadline(description, by);
        tasks.add(d);
        ui.showAddedTask(d, tasks.size());
        storage.save(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AddDeadlineCommand other) {
            return this.description.equals(other.description) && this.by.equals(other.by);
        }
        return false;
    }
}