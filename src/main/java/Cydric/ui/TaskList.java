package Cydric.ui;

import Cydric.tasks.Task;
import java.util.ArrayList;

/**
 * Represents the memory of Cydric bot.
 * Stores and manages the list of tasks the user has created.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * Used when the application starts for the first time or the save file is missing.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList loaded with previously saved tasks.
     *
     * @param loadedTasks An ArrayList of Tasks retrieved from the hard drive.
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The Task object (Todo, Deadline, or Event) to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list based on its index.
     *
     * @param index The zero-based index of the task to be removed.
     * @return The Task object that was removed from the list.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list without removing it.
     *
     * @param index The zero-based index of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the current total number of tasks in the list.
     *
     * @return The integer size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves the entire raw ArrayList of tasks.
     * Primarily used for passing the list to the Storage class for saving.
     *
     * @return The ArrayList containing all current Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
