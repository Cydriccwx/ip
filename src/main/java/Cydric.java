import java.util.Scanner;
import java.util.Arrays;

class Cydric {
    public static void main(String[] args) {
        // Store Task objects
        Task[] tasks = new Task[100];

        // Counter to track how many tasks we have
        int taskCount = 0;

        printLine();
        System.out.println("Hello! I'm Cydric\nWhat can I do for you?");
        printLine();

        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine();
            printLine();

            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (input.equals("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
            } else if (input.startsWith("mark")) {
                String[] parts = input.split(" "); // Split "mark number" into "mark" and "number"
                int index = Integer.parseInt(parts[1]) - 1; // Convert number to index (number - 1)

                tasks[index].markAsDone();

                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[index].toString());
            } else if (input.startsWith("unmark")) {
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;

                tasks[index].markAsNotDone();

                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[index].toString());
            } else {
                // Add the input to our list
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println(" added: " + input);
            }
            printLine();
        }
    }

    //Helper method to print divider line
    public static void printLine() {
        System.out.println("-------------------------------------------");
    }
}