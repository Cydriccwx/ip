package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.tasks.Task;
import Cydric.ui.CydricException;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

/**
 * Represents a command to delete a specific task from the list based on its index.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the target list index.
     *
     * @param index The zero-based index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the deletion of the task.
     * Removes the task from the list, saves the updated list to the hard drive,
     * and instructs the UI to print a confirmation message.
     *
     * @param tasks   The current TaskList containing all active tasks.
     * @param ui      The Ui instance responsible for printing to the console.
     * @param storage The Storage instance responsible for saving data to the hard drive.
     * @throws CydricException If the provided index is out of bounds of the current list.
     */
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
