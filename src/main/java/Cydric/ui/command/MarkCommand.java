package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.ui.CydricException;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CydricException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new CydricException("Error: Task number " + (index + 1) + " does not exist.");
        }

        tasks.getTask(index).markAsDone();
        storage.save(tasks.getTasks());

        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage(" " + tasks.getTask(index).toString());
    }
}