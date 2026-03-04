package Cydric.tasks;

/**
 * Represents an abstract base task in the Cydric bot.
 * Contains common properties and methods shared by all specific task types (Todo, Deadline, Event).
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a basic Task with the given description.
     * By default, a newly created task is marked as not done.
     *
     * @param description The text detailing what the task is.
     */
    public Task(String description) {
        this.description = description;
        isDone = false; // Set default to not done
    }

    /**
     * Retrieves the visual status icon representing whether the task is complete.
     *
     * @return A string containing a checkmark emoji if done, or a blank space if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "✅" : " "); // Mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the string representation of the task for displaying in the UI.
     *
     * @return A formatted string including the status icon and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Formats the task's data into a specific string structure for saving to the hard drive.
     * Must be implemented by all subclasses to handle their unique variables.
     *
     * @return A string formatted with pipe (|) delimiters for the save file.
     */
    public abstract String toFileFormat();
}