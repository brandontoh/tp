package command;

import cheatsheet.CheatSheet;
import exception.CommandException;
import sort.SortBySubject;
import sort.SortBySubjectRev;
import sort.SortByName;
import sort.SortByNameRev;
import ui.ConsoleColorsEnum;
import ui.Printer;
import ui.TablePrinter;

import java.util.ArrayList;
import java.util.Scanner;

public class SortFilter {
    final String promptSortConfig = ConsoleColorsEnum.RED_TEXT
        + "Sort filter (1: name ascending, 2: subject ascending, 3: name descending" + ", 4: "
        + "subject descending, ANY OTHER CHAR: EXIT)" + ConsoleColorsEnum.RESET_TEXT;
    Printer printer;
    ArrayList<CheatSheet> cheatSheetArrayList;

    public SortFilter(ArrayList<CheatSheet> cheatSheetArrayList, Printer printer) {
        this.cheatSheetArrayList = cheatSheetArrayList;
        this.printer = printer;
    }

    public void execute(TablePrinter tp) throws CommandException {
        do {
            askForInput();
            tp.execute();
        } while (true);
    }

    public void execute() throws CommandException {
        do {
            askForInput();
            TablePrinter tp = new TablePrinter(printer, cheatSheetArrayList);
            tp.execute();
        } while (true);
    }

    private void askForInput() throws CommandException {
        printer.print(promptSortConfig);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
        case "1":
            cheatSheetArrayList.sort(new SortByName());
            printer.print("Sorted name in ascending order");
            break;
        case "2":
            cheatSheetArrayList.sort(new SortBySubject());
            printer.print("Sorted subject in ascending order");
            break;
        case "3":
            cheatSheetArrayList.sort(new SortByNameRev());
            printer.print("Sorted name in descending order");
            break;
        case "4":
            cheatSheetArrayList.sort(new SortBySubjectRev());
            printer.print("Sorted subject in descending order");
            break;
        default:
            throw new CommandException("Exited list command");
        }
    }
}
