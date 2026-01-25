package jojo.command;

import jojo.storage.Storage;
import jojo.task.Deadline;
import jojo.task.TaskList;
import jojo.ui.Ui;

public class AddDeadlineCommand extends Command {
    private String desc;
    private String by;

    public AddDeadlineCommand(String desc, String by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline d = new Deadline(desc, by);
        tasks.add(d);
        ui.showAddedTask(d, tasks.size());
        storage.save(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AddDeadlineCommand) {
            AddDeadlineCommand other = (AddDeadlineCommand) obj;
            return this.desc.equals(other.desc) && this.by.equals(other.by);
        }
        return false;
    }
}