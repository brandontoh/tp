package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import editor.Editor;
import exception.CommandException;
import org.junit.jupiter.api.Test;
import parser.CommandFlag;
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

class FindCommandStub extends FinderCommand {
    private CheatSheetList cheatSheetList;
    public static final String invoker = "/find";

    public FindCommandStub(Printer printer, CheatSheetList cheatSheetList) {
        super(printer, cheatSheetList);
        this.cheatSheetList = cheatSheetList;
        flagsToDescriptions.put(CommandFlag.NAME, null);
        flagsToDescriptions.put(CommandFlag.SUBJECT, null);
        flagsToDescriptions.put(CommandFlag.SECTIONKEYWORD, null);
        alternativeArguments.add(CommandFlag.NAME);
        alternativeArguments.add(CommandFlag.SUBJECT);
        alternativeArguments.add(CommandFlag.SECTIONKEYWORD);
    }

    @Override
    public void execute() {
    }

    public void populateFlagsToDescription(String name, String subject, String keyword) {
        flagsToDescriptions.put(CommandFlag.NAME, name);
        flagsToDescriptions.put(CommandFlag.SUBJECT, subject);
        flagsToDescriptions.put(CommandFlag.SECTIONKEYWORD, keyword);
    }

    public ArrayList<CheatSheet> executeStub() throws CommandException {
        ArrayList<CheatSheet> matchedContents = new ArrayList<>();

        String name = flagsToDescriptions.get(CommandFlag.NAME);
        String subject = flagsToDescriptions.get(CommandFlag.SUBJECT);
        String keyword = flagsToDescriptions.get(CommandFlag.SECTIONKEYWORD);

        for (CheatSheet cs : cheatSheetList.getList()) {
            if (checkCheatSheetMatchesWithFields(cs, name, subject, keyword)) {
                matchedContents.add(cs);
            }
        }

        if (matchedContents.isEmpty()) {
            throw new CommandException("No matching content found");
        }

        return matchedContents;
    }
}
