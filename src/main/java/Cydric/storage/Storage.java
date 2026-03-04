package Cydric.storage;

import Cydric.tasks.Task;
import Cydric.tasks.Todo;
import Cydric.tasks.Deadline;
import Cydric.tasks.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of task data to the local hard drive.
 * This ensures that the user's task list persists between application sessions.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The relative or absolute path to the text file where tasks are saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the previously saved tasks from the hard drive.
     * Parses the formatted text file and reconstructs the corresponding Task objects.
     * If the file does not exist, it returns an empty list to start fresh.
     *
     * @return An ArrayList containing the Task objects successfully loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return loadedTasks;
        }

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                try {
                    String[] parts = line.split(" \\| ");
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String desc = parts[2];

                    Task t = null;
                    switch (type) {
                    case "T": t = new Todo(desc); break;
                    case "D": t = new Deadline(desc, parts[3]); break;
                    case "E": t = new Event(desc, parts[3], parts[4]); break;
                    default: throw new Exception("Unknown task type");
                    }
                    if (isDone) t.markAsDone();
                    loadedTasks.add(t);
                } catch (Exception e) {
                    System.out.println(" Warning: Skipping corrupted data line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println(" Error loading tasks: " + e.getMessage());
        }
        return loadedTasks;
    }

    /**
     * Saves the current list of tasks to the hard drive.
     * Overwrites the existing file with the updated task data formatted as text strings.
     * If the target directory does not exist, it creates it automatically.
     *
     * @param tasks The ArrayList of Task objects to be saved.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(" Error saving tasks to hard drive: " + e.getMessage());
        }
    }
}