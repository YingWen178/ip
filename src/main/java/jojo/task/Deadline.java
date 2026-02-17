package jojo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jojo.exception.JoJoException;

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
     * @throws JoJoException If the date format is invalid.
     */
    public Deadline(String description, String by) throws JoJoException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new JoJoException("NANI?! Please use yyyy-mm-dd format for the date (e.g., 2023-12-25).");
        }
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
