package jojo.command;

import jojo.storage.Storage;
import jojo.task.Deadline;
import jojo.task.TaskList;
import jojo.ui.Ui;

/**
 * Represents a command to add a new Deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Creates a command to add a Deadline task.
     *
     * @param description The description of the deadline task.
     * @param by   The date by which the task must be completed (yyyy-mm-dd).
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command by adding the Deadline task to the list,
     * displaying a success message, and saving the updated list to storage.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline d = new Deadline(description, by);
        tasks.add(d);
        storage.save(tasks);
        return ui.showAddedTask(d, tasks.size());
    }

    /**
     * Checks if this command is equal to another object.
     * Two AddDeadlineCommands are equal if they have the same description and date.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
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
