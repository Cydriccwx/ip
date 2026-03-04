package Cydric.tasks;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected String eventStart;
    protected String eventEnd;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The text detailing what the event is.
     * @param eventStart  The starting date/time of the event.
     * @param eventEnd    The ending date/time of the event.
     */
    public Event(String description, String eventStart, String eventEnd){
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    /**
     * Returns the string representation of the Event task for displaying in the UI.
     *
     * @return A formatted string including the [E] identifier, status icon, description, and time frame.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + eventStart + " To: " + eventEnd + ")";
    }

    /**
     * Formats the Event task's data into a string for saving to the hard drive.
     *
     * @return A string formatted as "E | status | description | start time | end time".
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + eventStart + " | " + eventEnd;
    }
}
