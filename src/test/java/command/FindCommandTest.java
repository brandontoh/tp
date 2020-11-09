package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;
import org.junit.jupiter.api.Test;
import parser.CommandFlag;
import parser.Parser;
import stubs.EditorStub;
import ui.Printer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FindCommandTest {

    @Test
    void execute_validName_success() throws CommandException {
        CheatSheetList cheatSheetList = new CheatSheetList();
        EditorStub editorStub = new EditorStub();

        AddCommand addCommand = new AddCommand(new Printer(), cheatSheetList, editorStub);
        HashMap<CommandFlag, String> addCommandMap = new HashMap<>();
        addCommandMap.put(CommandFlag.NAME, "Test1");
        addCommandMap.put(CommandFlag.SUBJECT, "Java1");
        addCommand.setFlagsToDescriptionsMap(addCommandMap);
        editorStub.writeContent("Content1");
        addCommand.execute();

        FindCommand findCommand = new FindCommand(new Printer(), cheatSheetList);
        HashMap<CommandFlag, String> findCommandMap = new HashMap<>();
        findCommandMap.put(CommandFlag.NAME, "Test1");
        findCommand.setFlagsToDescriptionsMap(findCommandMap);
        findCommand.execute();

        String data = "Hello, World!\r\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    /*
    @Test
    void execute_validInput_success() {
        final String userInput = "/find /k Details";
        CheatSheetList.clear();
        for (int i = 0; i < 10; i++) {
            CheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
        }
        try {
            Parser parser = new Parser();
            FindCommand findCommand = new FindCommand(parser);
            findCommand.execute();
        } catch (CommandException e) {
            fail();
        }
    }
    */

//    @Test
//    void execute_noArgument_exceptionThrown() {
//        final String userInput = "/find";
//        CheatSheetList.clear();
//        for (int i = 0; i < 10; i++) {
//            CheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
//        }
//        try {
//            Parser parser = new Parser();
//            Command findCommand = parser.parse(userInput);
//            findCommand.execute();
//            fail();
//        } catch (CommandException e) {
//            assertEquals("Please enter at least an argument", e.getMessage());
//        }
//    }
}
