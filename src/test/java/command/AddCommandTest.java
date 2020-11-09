package command;

import cheatsheet.CheatSheetList;
import editor.Editor;
import exception.CommandException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.Printer;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AddCommandTest {
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

    @Test
    void execute_nameFilledSubjectNotFilled_success() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription("FirstTest", null);
        addCommandStub.executeStub("Content1");
        Assertions.assertAll(
            () -> assertEquals("FirstTest", cheatSheetList.get(1).getName()),
            () -> assertEquals("Unsorted", cheatSheetList.get(1).getSubject()),
            () -> assertEquals("Content1", cheatSheetList.get(1).getDetails())
        );
    }

    @Test
    void execute_nameNotFilledSubjectFilled_exceptionThrown() {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription(null, "Java");
        assertThrows(CommandException.class, () -> {
            addCommandStub.executeStub("Content1");
        });
    }

    @Test
    void execute_nameNotFilledSubjectNotFilled_exceptionThrown() {
        CheatSheetList cheatSheetList = new CheatSheetList();
        AddCommandStub addCommandStub = new AddCommandStub(new Printer(), cheatSheetList, new Editor());
        addCommandStub.populateFlagsToDescription(null, null);
        assertThrows(CommandException.class, () -> {
            addCommandStub.executeStub("Content1");
        });
    }
}
