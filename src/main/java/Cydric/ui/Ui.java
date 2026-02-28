package Cydric.ui;

import java.util.Scanner;

public class Ui {
    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine().trim();
    }

    // Helper method to print introduction
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

    // Helper method to print divider line
    public static void printLine() {
        System.out.println("-".repeat(167));
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
