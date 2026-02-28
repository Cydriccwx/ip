package Cydric.ui;

import Cydric.tasks.Deadline;
import Cydric.tasks.Event;
import Cydric.tasks.Todo;
import Cydric.ui.command.*;

public class Parser {
    private static int fromCommandLength = 6; // Number to offset /from command for substring
    private static int toCommandLength = 4; // Number to offset /to command for substring
    private static int minNumberOfComponents = 2; // Minimum number of components to execute commands

    public static Command parse(String fullCommand) throws CydricException {
        // Split command from the rest of the sentence
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            if (parts.length < minNumberOfComponents) {
                throw new CydricException("Please specify which task you want me to mark!");
            }
            return new MarkCommand(Integer.parseInt(parts[1]) - 1);

        case "unmark":
            if (parts.length < minNumberOfComponents) {
                throw new CydricException("Please specify which task you want me to unmark!");
            }
            return new UnmarkCommand(Integer.parseInt(parts[1]) - 1);

        case "todo":
            if (parts.length < minNumberOfComponents || parts[1].trim().isEmpty()) {
                throw new CydricException("Please enter a description of your todo!");
            }
            return new AddCommand(new Todo(parts[1].trim()));

        case "deadline":
            if (parts.length < minNumberOfComponents || parts[1].trim().isEmpty()) {
                throw new CydricException("The description of a deadline cannot be empty!");
            }
            String[] descParts = parts[1].split(" /by ");
            if (descParts.length < minNumberOfComponents) {
                throw new CydricException("You must specify a deadline using '/by'");
            }
            return new AddCommand(new Deadline(descParts[0], descParts[1]));

        case "event":
            if (parts.length < minNumberOfComponents || parts[1].trim().isEmpty()) {
                throw new CydricException("The description of a event cannot be empty!");
            }
            String desc = parts[1].trim();
            int fromIdx = desc.indexOf("/from");
            int toIdx = desc.indexOf("/to");
            if (fromIdx == -1 || toIdx == -1) {
                throw new CydricException("You must specify '/from' and '/to' for an event\uD83D\uDE05");
            }
            String taskDesc = desc.substring(0, fromIdx).trim();
            String start = desc.substring(fromIdx + 6, toIdx).trim();
            String end = desc.substring(toIdx + 4).trim();
            return new AddCommand(new Event(taskDesc, start, end));

        case "delete":
            if (parts.length < minNumberOfComponents || parts[1].trim().isEmpty()) {
                throw new CydricException("Please specify which task you want to delete!");
            }
            return new DeleteCommand(Integer.parseInt(parts[1]) - 1);

        default:
            throw new CydricException("I have no idea what ur talking about \uD83D\uDDFF \nPlease use the given commands only :/");
        }
    }
}
