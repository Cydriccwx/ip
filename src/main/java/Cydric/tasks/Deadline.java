package Cydric.tasks;

/**
 * Represents a task that needs to be completed before a specific date or time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task with a description and a due date/time.
     *
     * @param description The text detailing what the task is.
     * @param by          The deadline by which the task must be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline task for displaying in the UI.
     *
     * @return A formatted string including the [D] identifier, status icon, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Formats the Deadline task's data into a string for saving to the hard drive.
     *
     * @return A string formatted as "D | status | description | deadline".
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
