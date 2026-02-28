package Cydric.command;

import Cydric.tasks.Task;

public class AddCommand extends Command {
    private Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(taskToAdd);
        storage.save(tasks.getTasks());
        ui.showMessage("Got it. I've added this task:\n  " + taskToAdd);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
