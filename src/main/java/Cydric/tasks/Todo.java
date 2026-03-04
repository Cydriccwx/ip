package Cydric.tasks;

/**
 * Represents a simple task without any attached date or time constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The text detailing what the todo is.
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Returns the string representation of the Todo task for displaying in the UI.
     *
     * @return A formatted string including the [T] identifier, status icon, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the Todo task's data into a string for saving to the hard drive.
     *
     * @return A string formatted as "T | status | description".
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}