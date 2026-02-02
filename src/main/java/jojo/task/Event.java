package jojo.task;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates a new Event task.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return The string formatted as "[E][Status] Description (from: start to: end)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the string representation for file storage.
     *
     * @return The string formatted as "E | Status | Description | Start | End".
     */
    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + from + " | " + to;
    }
}
