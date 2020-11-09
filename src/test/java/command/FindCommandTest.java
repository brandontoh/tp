package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import editor.Editor;
import exception.CommandException;
import org.junit.jupiter.api.Test;
import ui.Printer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FindCommandTest {

    @Test
    void execute_validNameAndSubjectAndKeyword_success() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        FindCommandStub FindCommandStub = new FindCommandStub(new Printer(), cheatSheetList);
        FindCommandStub.populateFlagsToDescription("FirstTest", "Java", "tent");
        ArrayList<CheatSheet> matchedContent = FindCommandStub.executeStub();
        assertEquals(matchedContent.size(), 1);
    }

    @Test
    void execute_ValidNameAndSubject_success() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        FindCommandStub FindCommandStub = new FindCommandStub(new Printer(), cheatSheetList);
        FindCommandStub.populateFlagsToDescription("FirstTest", "Java", null);
        ArrayList<CheatSheet> matchedContent = FindCommandStub.executeStub();
        assertEquals(matchedContent.size(), 1);
    }

    @Test
    void execute_validNameAndKeyword_success() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        FindCommandStub FindCommandStub = new FindCommandStub(new Printer(), cheatSheetList);
        FindCommandStub.populateFlagsToDescription("FirstTest", null, "nte");
        ArrayList<CheatSheet> matchedContent = FindCommandStub.executeStub();
        assertEquals(matchedContent.size(), 1);
    }

    @Test
    void execute_validSubjectAndKeyword_success() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        FindCommandStub FindCommandStub = new FindCommandStub(new Printer(), cheatSheetList);
        FindCommandStub.populateFlagsToDescription(null, "Ja", "nte");
        ArrayList<CheatSheet> matchedContent = FindCommandStub.executeStub();
        assertEquals(matchedContent.size(), 1);
    }

    @Test
    void execute_invalidSubject_exceptionThrown() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        FindCommandStub FindCommandStub = new FindCommandStub(new Printer(), cheatSheetList);
        FindCommandStub.populateFlagsToDescription(null, "Python", null);
        assertThrows(CommandException.class,FindCommandStub::executeStub);
    }

    @Test
    void execute_invalidSubjectValidName_exceptionThrown() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        FindCommandStub FindCommandStub = new FindCommandStub(new Printer(), cheatSheetList);
        FindCommandStub.populateFlagsToDescription("FirstTest", "Python", null);
        assertThrows(CommandException.class,FindCommandStub::executeStub);
    }
}
