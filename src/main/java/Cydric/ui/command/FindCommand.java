package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.tasks.Task;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

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
