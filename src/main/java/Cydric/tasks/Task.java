package Cydric.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false; // Set default to not done
    }

    public String getStatusIcon() {
        return (isDone ? "✅" : " "); // Mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String toFileFormat();
}