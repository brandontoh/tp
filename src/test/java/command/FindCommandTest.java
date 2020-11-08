package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import editor.Editor;
import exception.CommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.Parser;
import ui.Printer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FindCommandTest {

    @Test
    void execute_nameAndSubjectFilled_success() throws CommandException, IOException, InterruptedException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        Assertions.assertAll(
            () -> assertEquals("FirstTest", cheatSheetList.get(1).getName()),
            () -> assertEquals("Java", cheatSheetList.get(1).getSubject()),
            () -> assertEquals("Content1", cheatSheetList.get(1).getDetails())
        );
    }
}
