package Cydric.ui.command;

import Cydric.storage.Storage;
import Cydric.tasks.Task;
import Cydric.ui.TaskList;
import Cydric.ui.Ui;

/**
 * Represents a command to add a new task to the task list.
 * This handles Todos, Deadlines, and Events.
 */
public class AddCommand extends Command {
    private Task taskToAdd;

    /**
     * Constructs an AddCommand with the specific task to be added.
     *
     * @param taskToAdd The Task object (Todo, Deadline, or Event) created by the Parser.
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Executes the addition of the task.
     * Adds the task to the list, saves the updated list to the hard drive,
     * and instructs the UI to print a confirmation message.
     *
     * @param tasks   The current TaskList containing all active tasks.
     * @param ui      The Ui instance responsible for printing to the console.
     * @param storage The Storage instance responsible for saving data to the hard drive.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(taskToAdd);
        storage.save(tasks.getTasks());
        ui.showMessage("Got it. I've added this task:\n  " + taskToAdd);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}