package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.tasks.Task;
import Cydric.ui.CydricException;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CydricException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new CydricException("Task number " + (index + 1) + " does not exist.");
        }

        Task removedTask = tasks.deleteTask(index);
        storage.save(tasks.getTasks());

        ui.showMessage("Alright! I have removed this task:");
        ui.showMessage("  " + removedTask);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
