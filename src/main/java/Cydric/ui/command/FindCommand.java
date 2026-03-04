package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.tasks.Task;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

/**
 * Represents a command to search for tasks that contain a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified search keyword.
     *
     * @param keyword The string sequence to search for within the task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the search operation, iterating through the task list and asking the UI
     * to print any tasks whose string representation contains the keyword.
     *
     * @param tasks   The current TaskList containing all active tasks.
     * @param ui      The Ui instance responsible for printing to the console.
     * @param storage The Storage instance (unused in this command as it does not modify data).
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the matching tasks in your list:");

        int matchCount = 0; // Keeps track of the numbering for the printed list

        for (int index = 0; index < tasks.getSize(); index++) {
            Task currentTask = tasks.getTask(index);

            if (currentTask.toString().contains(keyword)) {
                matchCount++;
                ui.showMessage(" " + matchCount + "." + currentTask.toString());
            }
        }

        if (matchCount == 0) {
            ui.showMessage("No matching tasks found for: " + keyword);
        }
    }
}
