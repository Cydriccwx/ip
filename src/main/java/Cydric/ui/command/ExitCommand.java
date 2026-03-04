package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

/**
 * Represents a command to safely terminate the Cydric bot application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit operation by instructing the UI to print a farewell message.
     *
     * @param tasks   The current TaskList containing all active tasks.
     * @param ui      The Ui instance responsible for printing to the console.
     * @param storage The Storage instance (unused in this command as it does not modify data).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Signals to the main application loop that it should terminate.
     *
     * @return true, indicating the program should exit.
     */
    @Override
    public boolean isExit() {
        return true; // Tells the main loop to stop running
    }
}
