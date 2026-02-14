package Cydric.ui;

import Cydric.commands.Deadline;
import Cydric.commands.Event;
import Cydric.tasks.Task;
import Cydric.commands.Todo;

import java.util.Scanner;
import java.util.ArrayList;

class Cydric {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int fromCommandLength = 6; // Number to offset /from command for substring
    private static int toCommandLength = 4; // Number to offset /to command for substring
    private static int minNumberOfComponents = 2; // Minimum number of components to execute commands

    public static void main(String[] args) {
        printIntroduction();
        commandHandler();
    }

    public static void commandHandler() {
        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine().trim();
            printLine();

            // Split command from the rest of the sentence
            String[] separateParts = input.split(" ", 2);
            String command = separateParts[0].toLowerCase();

            try {
                switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    printLine();
                    return;

                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                    break;

                case "mark":
                    handleMark(separateParts);
                    break;

                case "unmark":
                    handleUnmark(separateParts);
                    break;

                case "todo":
                    if (separateParts.length < minNumberOfComponents  || separateParts[1].trim().isEmpty()) {
                        throw new CydricException("Please enter a description of your todo!");
                    }
                    addTask(new Todo(separateParts[1].trim()));
                    break;

                case "deadline":
                    handleDeadline(separateParts);
                    break;

                case "event":
                    handleEvent(separateParts);
                    break;

                case "delete":
                    handleDelete(separateParts);
                    break;

                default:
                    System.out.println("I have no idea what ur talking about \uD83D\uDDFF " +
                            "\nPlease use the given commands only :/");
                }
            } catch (CydricException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number format.");
            }
            printLine();
        }
    }

    // Helper method to print introduction
    public static void printIntroduction() {
        printLine();
        System.out.println("Hello! I'm the Cydric Bot");
        System.out.println("To use the Cydric Bot, please key in commands: list/todo/deadline/event/mark/unmark/delete");
        System.out.println("eg. todo CS2113 Lecture\neg. deadline CS2113 Lecture /by Friday\neg. event CS2113 IP" +
                " /from week 2 /to week 7");
        System.out.println("To use mark and unmark commands simply type out the command 'list' to view all active " +
                "tasks, then mark/unmark any task you wish to! eg. mark 1 / unmark 1");
        printLine();
    }

    // Helper method to print divider line
    public static void printLine() {
        System.out.println("-".repeat(167));
    }

    // Helper method to print addition of tasks
    private static void addTask(Task taskInput) {
        tasks.add(taskInput);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskInput);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    // Helper method to handle mark
    private static void handleMark(String[] parts) throws CydricException {
        if (parts.length < minNumberOfComponents) { // did not enter task number
            throw new CydricException("Please specify which task you want me to mark!");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= tasks.size()) { // task number out of range
                throw new CydricException("Error: Task number " + parts[1] + " does not exist.");
            }
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + tasks.get(index).toString());
        } catch (NumberFormatException e) {
            throw new CydricException("Error: Please enter a valid number (e.g., 'mark 1')\uD83D\uDE05");
        }
    }

    // Helper method to handle unmark
    private static void handleUnmark(String[] parts) throws CydricException {
        if (parts.length < minNumberOfComponents) {
            throw new CydricException("Please specify which task you want me to unmark!");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new CydricException("Error: Task number " + parts[1] + " does not exist.");
            }
            tasks.get(index).markAsNotDone();
            System.out.println("Alright! I've marked this task as not done yet:");
            System.out.println(" " + tasks.get(index).toString());
        } catch (NumberFormatException e) {
            throw new CydricException("Error: Please enter a valid number (e.g., 'unmark 1')\uD83D\uDE05");
        }
    }

    // Helper method to handle deadline
    private static void handleDeadline(String[] parts) throws CydricException {
        if (parts.length < minNumberOfComponents || parts[1].trim().isEmpty()) {
            throw new CydricException("The description of a deadline cannot be empty!");
        }

        String[] descriptionParts = parts[1].split(" /by ");

        if (descriptionParts.length < minNumberOfComponents) {
            throw new CydricException("You must specify a deadline using '/by', eg.'deadline do work /by 14th Feb'");
        }

        addTask(new Deadline(descriptionParts[0], descriptionParts[1]));
    }

    // Helper method to handle event
    private static void handleEvent(String[] parts) throws CydricException {
        if (parts.length < minNumberOfComponents || parts[1].trim().isEmpty()) {
            throw new CydricException("The description of a event cannot be empty!");
        }

        String description = parts[1].trim();
        int fromIndex = description.indexOf("/from");
        int toIndex = description.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw new CydricException("You must specify '/from' and '/to' for an event\uD83D\uDE05");
        }

        String taskDescription = description.substring(0, fromIndex).trim();
        String startDate = description.substring(fromIndex + fromCommandLength, toIndex).trim();
        String endDate = description.substring(toIndex + toCommandLength).trim();

        if (taskDescription.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            throw new CydricException("Event descriptions cannot be empty!");
        }
        addTask(new Event(taskDescription, startDate, endDate));
    }

    // Helper method to delete tasks
    private static void handleDelete(String[] parts)  throws CydricException {
        if (parts.length < minNumberOfComponents || parts[1].trim().isEmpty()) {
            throw new CydricException("Please specify which task you want to delete!");
        }

        int taskIndex =  Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new CydricException("Task number " +  parts[1] + " does not exist.");
        }

        Task removedTask = tasks.remove(taskIndex);

        System.out.println("Alright! I have removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}