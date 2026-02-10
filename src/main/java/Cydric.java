import java.util.Scanner;
import java.util.Arrays;

class Cydric {

    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS]; // List of max 100 tasks
    private static int taskCount = 0; // Counter to track how many tasks we have

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
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks[i]);
                    }
                    break;

                case "mark":
                    handleMark(separateParts);
                    break;

                case "unmark":
                    handleUnmark(separateParts);
                    break;

                case "todo":
                    if (separateParts.length < 2  || separateParts[1].trim().isEmpty()) {
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
        System.out.println("To use the Cydric Bot, please key in commands: list/todo/deadline/event/mark/unmark");
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
    private static void addTask(Task t) {
        tasks[taskCount] = t;
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    // Helper method to handle mark
    private static void handleMark(String[] parts) throws CydricException {
        if (parts.length < 2) { // did not enter task number
            throw new CydricException("Please specify which task you want me to mark!");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= taskCount) { // task number out of range
                throw new CydricException("Error: Task number " + parts[1] + " does not exist.");
            }
            tasks[index].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + tasks[index].toString());
        } catch (NumberFormatException e) {
            throw new CydricException("Error: Please enter a valid number (e.g., 'mark 1')\uD83D\uDE05");
        }
    }

    // Helper method to handle unmark
    private static void handleUnmark(String[] parts) throws CydricException {
        if (parts.length < 2) {
            throw new CydricException("Please specify which task you want me to unmark!");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= taskCount) {
                throw new CydricException("Error: Task number " + parts[1] + " does not exist.");
            }
            tasks[index].markAsNotDone();
            System.out.println("Alright! I've marked this task as not done yet:");
            System.out.println(" " + tasks[index].toString());
        } catch (NumberFormatException e) {
            throw new CydricException("Error: Please enter a valid number (e.g., 'unmark 1')\uD83D\uDE05");
        }
    }

    // Helper method to handle deadline
    private static void handleDeadline(String[] parts) throws CydricException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new CydricException("The description of a deadline cannot be empty!");
        }

        String[] descriptionParts = parts[1].split(" /by ");

        if (descriptionParts.length < 2) {
            throw new CydricException("You must specify a deadline using '/by', eg.'deadline do work /by 14th Feb'");
        }

        addTask(new Deadline(descriptionParts[0], descriptionParts[1]));
    }

    // Helper method to handle event
    private static void handleEvent(String[] parts) throws CydricException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new CydricException("The description of a event cannot be empty!");
        }

        String description = parts[1].trim();
        int fromIndex = description.indexOf("/from");
        int toIndex = description.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw new CydricException("You must specify '/from' and '/to' for an event\uD83D\uDE05");
        }

        String taskDescription = description.substring(0, fromIndex).trim();
        String startDate = description.substring(fromIndex + 6, toIndex).trim();
        String endDate = description.substring(toIndex + 4).trim();

        if (taskDescription.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            throw new CydricException("Event descriptions cannot be empty!");
        }
        addTask(new Event(taskDescription, startDate, endDate));
    }
}