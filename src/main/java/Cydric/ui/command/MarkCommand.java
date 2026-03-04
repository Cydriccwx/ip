package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.ui.CydricException;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

/**
 * Represents a command to mark a specific task in the list as completed.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the target list index.
     *
     * @param index The zero-based index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the marking operation.
     * Changes the task's status to done, saves the updated list to the hard drive,
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

        tasks.getTask(index).markAsDone();
        storage.save(tasks.getTasks());

        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage(" " + tasks.getTask(index).toString());
    }
}