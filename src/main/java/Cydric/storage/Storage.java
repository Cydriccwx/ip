package Cydric.storage;

import Cydric.tasks.Task;
import Cydric.commands.Todo;
import Cydric.commands.Deadline;
import Cydric.commands.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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