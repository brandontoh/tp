package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;
import org.junit.jupiter.api.Test;
import parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FindCommandTest {
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
