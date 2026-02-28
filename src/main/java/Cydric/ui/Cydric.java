package Cydric.ui;

import Cydric.storage.Storage;
import Cydric.tasks.Deadline;
import Cydric.tasks.Event;
import Cydric.tasks.Task;
import Cydric.tasks.Todo;
import Cydric.ui.command.Command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Cydric {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Cydric(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.printIntroduction();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                if (fullCommand.isEmpty()) continue;

                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (CydricException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("Please enter a valid number format.");
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        String filePath = Paths.get("data", "cydric.txt").toString();
        new Cydric(filePath).run();
    }
}