package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import editor.Editor;
import exception.CommandException;
import org.junit.jupiter.api.Test;
import ui.Printer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FindCommandTest {

    @Test
    void execute_validNameAndSubjectAndKeyword_success() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        FindCommandStub findCommandStub = new FindCommandStub(new Printer(), cheatSheetList);
        findCommandStub.populateFlagsToDescription("FirstTest", "Java", "tent");
        ArrayList<CheatSheet> matchedContent = findCommandStub.executeStub();
        assertEquals(matchedContent.size(), 1);
    }

    @Test
    void execute_ValidNameAndSubject_success() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        FindCommandStub findCommandStub = new FindCommandStub(new Printer(), cheatSheetList);
        findCommandStub.populateFlagsToDescription("FirstTest", "Java", null);
        ArrayList<CheatSheet> matchedContent = findCommandStub.executeStub();
        assertEquals(matchedContent.size(), 1);
    }

    @Test
    void execute_validNameAndKeyword_success() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        FindCommandStub findCommandStub = new FindCommandStub(new Printer(), cheatSheetList);
        findCommandStub.populateFlagsToDescription("FirstTest", null, "nte");
        ArrayList<CheatSheet> matchedContent = findCommandStub.executeStub();
        assertEquals(matchedContent.size(), 1);
    }

    @Test
    void execute_validSubjectAndKeyword_success() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        FindCommandStub findCommandStub = new FindCommandStub(new Printer(), cheatSheetList);
        findCommandStub.populateFlagsToDescription(null, "Ja", "nte");
        ArrayList<CheatSheet> matchedContent = findCommandStub.executeStub();
        assertEquals(matchedContent.size(), 1);
    }

    @Test
    void execute_invalidSubject_exceptionThrown() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        FindCommandStub findCommandStub = new FindCommandStub(new Printer(), cheatSheetList);
        findCommandStub.populateFlagsToDescription(null, "Python", null);
        assertThrows(CommandException.class, findCommandStub::executeStub);
    }

    @Test
    void execute_invalidSubjectValidName_exceptionThrown() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", "Java");
        addCommandStub.executeStub("Content1");
        FindCommandStub findCommandStub = new FindCommandStub(new Printer(), cheatSheetList);
        findCommandStub.populateFlagsToDescription("FirstTest", "Python", null);
        assertThrows(CommandException.class, findCommandStub::executeStub);
    }
}
