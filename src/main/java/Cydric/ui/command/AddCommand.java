package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.tasks.Task;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

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