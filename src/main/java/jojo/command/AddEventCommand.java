package jojo.command;

import jojo.storage.Storage;
import jojo.task.Event;
import jojo.task.TaskList;
import jojo.ui.Ui;

/**
 * Represents a command to add a new Event task.
 */
public class AddEventCommand extends Command {
    private String desc;
    private String from;
    private String to;

    /**
     * Creates a command to add an Event task.
     *
     * @param desc The description of the event.
     * @param from The start time/date of the event.
     * @param to   The end time/date of the event.
     */
    public AddEventCommand(String desc, String from, String to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command by adding the Event task to the list,
     * displaying a success message, and saving the updated list to storage.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event e = new Event(desc, from, to);
        tasks.add(e);
        ui.showAddedTask(e, tasks.size());
        storage.save(tasks);
    }

    /**
     * Checks if this command is equal to another object.
     * Two AddEventCommands are equal if they have the same description, start time, and end time.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AddEventCommand) {
            AddEventCommand other = (AddEventCommand) obj;
            return this.desc.equals(other.desc)
                    && this.from.equals(other.from)
                    && this.to.equals(other.to);
        }
        return false;
    }
}