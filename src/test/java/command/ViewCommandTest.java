package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import editor.Editor;
import exception.CommandException;
import org.junit.jupiter.api.Test;
import parser.CommandFlag;
import ui.Printer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

class ViewCommandStub extends FinderCommand {

    public ViewCommandStub(Printer printer, CheatSheetList cheatSheetList) {
        super(printer, cheatSheetList);

        flagsToDescriptions.put(CommandFlag.NAME, null);
        flagsToDescriptions.put(CommandFlag.INDEX, null);
        alternativeArguments.add(CommandFlag.NAME);
        alternativeArguments.add(CommandFlag.INDEX);
    }

    @Override
    public void execute() {
    }

    public void populateFlagsToDescription(String name, String index) {
        flagsToDescriptions.put(CommandFlag.NAME, name);
        flagsToDescriptions.put(CommandFlag.INDEX, index);
    }

    public String executeStub() throws CommandException {
        try {
            CheatSheet desiredCheatSheet = getCheatSheetFromNameOrIndex();
            return desiredCheatSheet.getName() + desiredCheatSheet.getSubject() + desiredCheatSheet.getDetails();
        } catch (IndexOutOfBoundsException | NumberFormatException | CommandException e) {
            throw new CommandException("Please enter a valid name or/and index");
        }
    }
}