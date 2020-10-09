package UI;

import java.util.Scanner;

public class UI {

    public UI() {

    }

    public void displayWelcomeScreen() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
    }

    public String askForUserInput() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        return userInput;
    }

    public void printExitMessage() {
        System.out.println("Bye Friend");
    }

    public void printCheatSheet() {
        int index = 0;
        String cs = "";
        System.out.println(index + ": " + cs);
    }
}
