package jojo.command;

import jojo.storage.Storage;
import jojo.task.Event;
import jojo.task.TaskList;
import jojo.ui.Ui;

public class AddEventCommand extends Command {
    private String desc;
    private String from;
    private String to;

    public AddEventCommand(String desc, String from, String to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event e = new Event(desc, from, to);
        tasks.add(e);
        ui.showAddedTask(e, tasks.size());
        storage.save(tasks);
    }
}