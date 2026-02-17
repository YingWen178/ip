package jojo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jojo.exception.JoJoException;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates a new Event task.
     *
     * @param description The description of the event.
     * @param from The start time of the event (yyyy-MM-dd HHmm).
     * @param to The end time of the event (yyyy-MM-dd HHmm).
     * @throws JoJoException If the date format is invalid or if 'from' is after 'to'.
     */
    public Event(String description, String from, String to) throws JoJoException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new JoJoException("NANI?! Please use yyyy-MM-dd HHmm format for event times"
                    + " (e.g., 2023-12-25 1800).");
        }
        if (this.from.isAfter(this.to) || this.from.isEqual(this.to)) {
            throw new JoJoException("NANI?! The start time must be before the end time.");
        }
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return The string formatted as "[E][Status] Description (from: start to: end)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT)
                + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the string representation for file storage.
     *
     * @return The string formatted as "E | Status | Description | Start | End".
     */
    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + from.format(INPUT_FORMAT) + " | " + to.format(INPUT_FORMAT);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Event other) {
            return super.equals(other) && this.from.equals(other.from) && this.to.equals(other.to);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 31 * (from.hashCode() + to.hashCode());
    }
}
