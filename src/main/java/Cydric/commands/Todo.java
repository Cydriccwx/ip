package Cydric.commands;

import Cydric.tasks.Task;

public class Todo extends Task {
    protected boolean isDone;

    public Todo(String description){
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
