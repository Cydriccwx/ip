package Cydric.ui;

import java.util.Scanner;

/**
 * This class is responsible for reading user input and printing messages, errors, and UI elements to the console.
 */
public class Ui {
    private Scanner in;

    /**
     * Constructs a new Ui instance and initializes the Scanner to read from standard input.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads the next line of input typed by the user.
     *
     * @return The raw string command entered by the user, with leading and trailing whitespace removed.
     */
    public String readCommand() {
        return in.nextLine().trim();
    }

    /**
     * Prints the initial welcome message and instructions on how to use the bot.
     * Displays available commands and formatting examples.
     */
    public static void printIntroduction() {
        printLine();
        System.out.println("Hello! I'm the Cydric Bot");
        System.out.println("To use the Cydric Bot, please key in commands: list/todo/deadline/event/mark/unmark/delete");
        System.out.println("eg. todo CS2113 Lecture\neg. deadline CS2113 Lecture /by Friday\neg. event CS2113 IP" +
                " /from week 2 /to week 7");
        System.out.println("To use mark/unmark/delete commands simply type out the command 'list' to view all active " +
                "tasks, then mark/unmark/delete any task you wish to! eg. mark 1 / delete 1");
        printLine();
    }

    /**
     * Prints a divider line to separate different blocks of text in the console.
     */
    public static void printLine() {
        System.out.println("-".repeat(167));
    }

    /**
     * Prints an error message formatted specifically to alert the user of a problem.
     *
     * @param message The specific error explanation to be displayed.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Prints a standard system message or confirmation to the user.
     *
     * @param message The text to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}