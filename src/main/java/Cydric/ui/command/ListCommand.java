package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.showMessage(" " + (i + 1) + ". " + tasks.getTask(i).toString());
        }
    }
}
