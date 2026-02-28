package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true; // Tells the main loop to stop running
    }
}
