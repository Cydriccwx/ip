package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.ui.CydricException;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CydricException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new CydricException("Error: Task number " + (index + 1) + " does not exist.");
        }

        tasks.getTask(index).markAsNotDone();
        storage.save(tasks.getTasks());

        ui.showMessage("Alright! I've marked this task as not done yet:");
        ui.showMessage(" " + tasks.getTask(index).toString());
    }
}
