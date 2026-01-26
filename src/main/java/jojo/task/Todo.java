package jojo.task;

/**
 * Represents a task without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Creates a new Todo task.
     *
     * @param description The description of the task.
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return The string formatted as "[T][Status] Description".
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation for file storage.
     *
     * @return The string formatted as "T | Status | Description".
     */
    @Override
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }
}
