package jojo.command;

import jojo.storage.Storage;
import jojo.task.Event;
import jojo.task.TaskList;
import jojo.ui.Ui;

public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event e = new Event(description, from, to);
        tasks.add(e);
        ui.showAddedTask(e, tasks.size());
        storage.save(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AddEventCommand other) {
            return this.description.equals(other.description)
                    && this.from.equals(other.from)
                    && this.to.equals(other.to);
        }
        return false;
    }
}