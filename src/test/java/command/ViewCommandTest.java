package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;
import org.junit.jupiter.api.Test;
import parser.Parser;

import javax.swing.text.View;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ViewCommandTest {
    /*
    @Test
    void execute_validInput_success() {
        final String userInput = "/view /i 2";
        CheatSheetList.clear();
        for (int i = 0; i < 10; i++) {
            CheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
        }
        try {
            Parser parser = new Parser();
            Command viewCommand = parser.parser(userInput);
            viewCommand.execute();
        } catch (CommandException e) {
            fail();
        }
    }
    */
//    @Test
//    void execute_noArgument_exceptionThrown() {
//        final String userInput = "/view something";
//        CheatSheetList.clear();
//        for (int i = 0; i < 10; i++) {
//            CheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
//        }
//        try {
//            Parser parser = new Parser();
//            Command viewCommand = parser.parse(userInput);
//            viewCommand.execute();
//            fail();
//        } catch (CommandException e) {
//            assertEquals("Please enter a name or an index", e.getMessage());
//        }
//    }
}