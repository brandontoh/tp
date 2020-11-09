package command;


import cheatsheet.CheatSheetList;
import editor.Editor;
import exception.CommandException;
import org.junit.jupiter.api.Test;
import sort.SortByName;
import ui.Printer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {

    @Test
    void execute_success() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        for (int i = 0; i < 10; i++) {
            AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
            addCommandStub.populateFlagsToDescription("Test" + i, "Java" + i);
            addCommandStub.executeStub("Content" + i);
        }
        ListCommandStub listCommandStub = new ListCommandStub(new Printer(), cheatSheetList);
        assertEquals(10, listCommandStub.executeStub().getSize());
    }
}

class ListCommandStub extends Command {
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
