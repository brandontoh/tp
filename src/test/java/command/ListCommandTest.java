package command;


import cheatsheet.CheatSheetList;
import editor.Editor;
import exception.CommandException;
import org.junit.jupiter.api.Test;
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
