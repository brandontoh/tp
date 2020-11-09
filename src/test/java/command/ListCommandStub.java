package command;

import cheatsheet.CheatSheetList;

import exception.CommandException;

import sort.SortByName;
import ui.Printer;
import ui.TablePrinter;


public class ListCommandStub extends Command {
    public static final String invoker = "/list";
    private CheatSheetList cheatSheetList;

    public ListCommandStub(Printer printer, CheatSheetList cheatSheetList) {
        super(printer);
        this.cheatSheetList = cheatSheetList;
    }

    @Override
    public void execute() throws CommandException {
    }

    public CheatSheetList executeStub() throws CommandException {
        cheatSheetList.getList().sort(new SortByName());

        if (cheatSheetList.getSize() == 0) {
            throw new CommandException("You don't have any cheat sheet. Use the /add command to create a new one");
        }
        return cheatSheetList;
    }
}
