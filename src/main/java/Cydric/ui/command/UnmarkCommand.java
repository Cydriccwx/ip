package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.ui.CydricException;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

/**
 * Represents a command to unmark a specific task in the list, setting its status to incomplete.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the target list index.
     *
     * @param index The zero-based index of the task to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmarking operation.
     * Changes the task's status to incomplete, saves the updated list to the hard drive,
     * and instructs the UI to print a success message.
     *
     * @param tasks   The current TaskList containing all active tasks.
     * @param ui      The Ui instance responsible for printing to the console.
     * @param storage The Storage instance responsible for saving data to the hard drive.
     * @throws CydricException If the provided index is out of bounds of the current list.
     */
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
