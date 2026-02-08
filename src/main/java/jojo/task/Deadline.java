package jojo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a new Deadline task.
     *
     * @param description The task description.
     * @param by The deadline date in yyyy-mm-dd format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return The string formatted as "[D][Status] Description (by: Date)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the string representation for file storage.
     *
     * @return The string formatted as "D | Status | Description | Date".
     */
    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() + " | " + by;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Deadline other) {
            return super.equals(other) && this.by.equals(other.by);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 31 * by.hashCode();
    }
}
