import java.util.Scanner;
import java.util.Arrays;

class Cydric {

    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS]; // List of max 100 tasks
    private static int taskCount = 0; // Counter to track how many tasks we have

    public static void main(String[] args) {
        printIntroduction();

        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine().trim();
            printLine();

            // Split command from the rest of the sentence
            String[] separateParts = input.split(" ", 2);
            String command = separateParts[0].toLowerCase();

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
                    // Only execute command if there is a whole number
                    if (separateParts.length == 2 && isAnInteger(separateParts[1])) {
                        int index = Integer.parseInt(separateParts[1]) - 1;
                        // Check if number is within valid range
                        if (index >= 0 && index < taskCount) {
                            tasks[index].markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("  " + tasks[index].toString());
                        } else {
                            System.out.println("Error: Task number " + separateParts[1] + " does not exist.");
                        }
                    } else {
                        System.out.println("Error: Please enter a valid number (e.g., 'mark 1')\uD83D\uDE05");
                    }
                    break;

                case "unmark":
                    if (separateParts.length == 2 && isAnInteger(separateParts[1])) {
                        int index = Integer.parseInt(separateParts[1]) - 1;
                        // Check if number is within valid range
                        if (index >= 0 && index < taskCount) {
                            tasks[index].markAsNotDone();
                            System.out.println("Alright! I've marked this task as not done yet:");
                            System.out.println("  " + tasks[index].toString());
                        } else  {
                            System.out.println("Error: Task number " + separateParts[1] + " does not exist.");
                        }
                    } else {
                        System.out.println("Error: Please enter a valid number (e.g., 'mark 1')\uD83D\uDE05");
                    }
                    break;

                case "todo":
                    if (input.length() > 5) {
                        String taskDescription = input.substring(5).trim();
                        addTask(new Todo(taskDescription));
                    }
                    break;

                case "deadline":
                    if (separateParts.length > 1) {
                        String taskDescription = separateParts[1];
                        String[] descriptionParts = taskDescription.split(" /by ");
                        if (descriptionParts.length == 2) {
                            addTask(new Deadline(descriptionParts[0], descriptionParts[1]));
                        } else {
                            System.out.println("Please add a description of the task or a deadline!");
                        }
                    }
                    break;

                case "event":
                    if(separateParts.length > 1) {
                        String description = separateParts[1];
                        int fromIndex = description.indexOf("/from");
                        int toIndex = description.indexOf("/to");

                        if (fromIndex != -1 && toIndex != -1) {
                            String taskDescription = description.substring(0, fromIndex).trim();
                            String startDate = description.substring(fromIndex + 6, toIndex).trim();
                            String endDate = description.substring(toIndex + 4).trim();
                            addTask(new Event(taskDescription, startDate, endDate));
                        }
                    }
                    break;

                default:
                    System.out.println("I have no idea what ur talking about \uD83D\uDDFF");
            }
            printLine();
        }
    }

    // Helper method to print introduction
    public static void printIntroduction() {
        printLine();
        System.out.println("Hello! I'm Cydric\nWhat can I do for you my G?");
        printLine();
    }

    // Helper method to print divider line
    public static void printLine() {
        System.out.println("-".repeat(67));
    }

    // Helper method to check if a string is an integer
    private static boolean isAnInteger(String number) {
        if (number == null) {
            return false;
        }

        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Helper method to print addition of tasks
    private static void addTask(Task t) {
        tasks[taskCount] = t;
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

}