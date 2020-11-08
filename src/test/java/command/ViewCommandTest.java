package command;

import cheatsheet.CheatSheetList;
import editor.Editor;
import exception.CommandException;
import org.junit.jupiter.api.Test;
import ui.Printer;

import static org.junit.jupiter.api.Assertions.*;

class ViewCommandTest {

    @Test
    void execute_validIndex_success() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        ViewCommandStub viewCommandStub = new ViewCommandStub(new Printer(), cheatSheetList);
        viewCommandStub.populateFlagsToDescription(null, "1");
        assertEquals("FirstTestJavaContent1", viewCommandStub.executeStub());
    }

    @Test
    void execute_validName_success() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        ViewCommandStub viewCommandStub = new ViewCommandStub(new Printer(), cheatSheetList);
        viewCommandStub.populateFlagsToDescription("FirstTest", null);
        assertEquals("FirstTestJavaContent1", viewCommandStub.executeStub());
    }

    @Test
    void execute_InvalidName_exceptionThrown() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        ViewCommandStub viewCommandStub = new ViewCommandStub(new Printer(), cheatSheetList);
        viewCommandStub.populateFlagsToDescription("namethatdontexist", null);
        assertThrows(CommandException.class, viewCommandStub::executeStub);
    }

    @Test
    void execute_InvalidIndex_exceptionThrown() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        ViewCommandStub viewCommandStub = new ViewCommandStub(new Printer(), cheatSheetList);
        viewCommandStub.populateFlagsToDescription("FirstTest", "2");
        assertThrows(CommandException.class, viewCommandStub::executeStub);
    }
}