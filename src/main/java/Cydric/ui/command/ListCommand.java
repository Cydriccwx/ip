package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

/**
 * Represents a command to display all currently tracked tasks to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the listing operation.
     * Iterates through the task list and instructs the UI to print each task with its index.
     *
     * @param tasks   The current TaskList containing all active tasks.
     * @param ui      The Ui instance responsible for printing to the console.
     * @param storage The Storage instance (unused in this command as it does not modify data).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.showMessage(" " + (i + 1) + ". " + tasks.getTask(i).toString());
        }
    }
}
